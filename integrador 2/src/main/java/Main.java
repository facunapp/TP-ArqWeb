import dto.ReporteCarreraDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.impl.CarreraRepositoryImpl;
import utils.CargarCSV;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IntegradorTP2");
        EntityManager em = emf.createEntityManager();

        // carga los datos solo si la base está vacía
        long count = em.createQuery("SELECT COUNT(c) FROM Carrera c", Long.class).getSingleResult();
        if (count == 0) {
            CargarCSV.cargar(em);
        }

        CarreraRepositoryImpl carreraRepo = new CarreraRepositoryImpl(em);
        // Punto 3: reporte anual de carreras (inscriptos y egresados por año)
        List<ReporteCarreraDTO> reporte = carreraRepo.generarReporteAnual();

        System.out.println("=== Reporte de Carreras ===");
        for (ReporteCarreraDTO dto : reporte) {
            System.out.println("Carrera: " + dto.getNombreCarrera()
                + " | Año: " + dto.getAnio()
                + " | Inscriptos: " + dto.getCantidadInscriptos()
                + " | Egresados: " + dto.getCantidadEgresados());
        }

        em.close();
        emf.close();
    }
}
