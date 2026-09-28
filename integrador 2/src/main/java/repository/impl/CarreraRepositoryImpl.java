package repository.impl;

import dto.ReporteCarreraDTO;
import entities.Carrera;
import jakarta.persistence.EntityManager;
import repository.CarreraRepository;

import java.util.*;

public class CarreraRepositoryImpl implements CarreraRepository {

    private EntityManager em;

    public CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void guardar(Carrera carrera) {
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    @Override
    public List<Carrera> obtenerCarrerasConInscriptos() {
        return em.createQuery(
            "SELECT DISTINCT ec.carrera FROM EstudianteCarrera ec ORDER BY ec.carrera.carrera ASC",
            Carrera.class
        ).getResultList();
    }

    @Override
    public List<ReporteCarreraDTO> generarReporteAnual() {

        // inscriptos agrupados por carrera y año de inscripcion
        List<Object[]> inscriptos = em.createQuery(
            "SELECT ec.carrera.carrera, ec.inscripcion, COUNT(ec) " +
            "FROM EstudianteCarrera ec " +
            "GROUP BY ec.carrera.carrera, ec.inscripcion",
            Object[].class
        ).getResultList();

        // egresados agrupados por carrera y año de graduacion (excluye los que no se graduaron)
        List<Object[]> egresados = em.createQuery(
            "SELECT ec.carrera.carrera, ec.graduacion, COUNT(ec) " +
            "FROM EstudianteCarrera ec " +
            "WHERE ec.graduacion <> 0 " +
            "GROUP BY ec.carrera.carrera, ec.graduacion",
            Object[].class
        ).getResultList();

        // se usa un mapa con clave "carrera-anio" para combinar los dos resultados
        HashMap<String, ReporteCarreraDTO> mapa = new HashMap<>();

        for (Object[] row : inscriptos) {
            String carrera = (String) row[0];
            int anio = (Integer) row[1];
            long cantidad = (Long) row[2];
            String clave = carrera + "-" + anio;
            mapa.put(clave, new ReporteCarreraDTO(carrera, anio, cantidad, 0));
        }

        for (Object[] row : egresados) {
            String carrera = (String) row[0];
            int anio = (Integer) row[1];
            long cantidad = (Long) row[2];
            String clave = carrera + "-" + anio;
            if (mapa.containsKey(clave)) {
                ReporteCarreraDTO dto = mapa.get(clave);
                mapa.put(clave, new ReporteCarreraDTO(carrera, anio, dto.getCantidadInscriptos(), cantidad));
            } else {
                mapa.put(clave, new ReporteCarreraDTO(carrera, anio, 0, cantidad));
            }
        }

        // ordenar alfabeticamente por carrera y cronologicamente por año
        List<ReporteCarreraDTO> resultado = new ArrayList<>(mapa.values());
        resultado.sort(Comparator.comparing(ReporteCarreraDTO::getNombreCarrera)
                .thenComparingInt(ReporteCarreraDTO::getAnio));

        return resultado;
    }
}
