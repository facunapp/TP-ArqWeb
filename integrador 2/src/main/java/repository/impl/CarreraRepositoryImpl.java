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

    //metodo ej 2.f
    @Override
    public List<Carrera> obtenerCarrerasConInscriptos() {
        String jpql = "SELECT ec.carrera " +
                "FROM EstudianteCarrera ec " +
                "GROUP BY ec.carrera " +
                "ORDER BY COUNT(ec.estudiante) DESC";

        return em.createQuery(jpql, Carrera.class).getResultList();
    }

    @Override
    public List<ReporteCarreraDTO> generarReporteAnual() {
        // Una sola query nativa que une inscriptos y egresados por carrera y año
        String sql =
            "SELECT carrera, anio, SUM(inscriptos), SUM(egresados) " +
            "FROM ( " +
            "    SELECT c.carrera AS carrera, ec.inscripcion AS anio, COUNT(*) AS inscriptos, 0 AS egresados " +
            "    FROM estudiante_carrera ec JOIN carrera c ON ec.id_carrera = c.id_carrera " +
            "    GROUP BY c.carrera, ec.inscripcion " +
            "    UNION ALL " +
            "    SELECT c.carrera AS carrera, ec.graduacion AS anio, 0 AS inscriptos, COUNT(*) AS egresados " +
            "    FROM estudiante_carrera ec JOIN carrera c ON ec.id_carrera = c.id_carrera " +
            "    WHERE ec.graduacion > 0 " +
            "    GROUP BY c.carrera, ec.graduacion " +
            ") AS combined " +
            "GROUP BY carrera, anio " +
            "ORDER BY carrera ASC, anio ASC";

        List<Object[]> filas = em.createNativeQuery(sql).getResultList();

        List<ReporteCarreraDTO> resultado = new ArrayList<>();
        for (Object[] fila : filas) {
            String nombre = (String) fila[0];
            int anio = ((Number) fila[1]).intValue();
            long inscriptos = ((Number) fila[2]).longValue();
            long egresados = ((Number) fila[3]).longValue();
            resultado.add(new ReporteCarreraDTO(nombre, anio, inscriptos, egresados));
        }
        return resultado;
    }
}
