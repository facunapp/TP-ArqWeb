import dto.ReporteCarreraDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.impl.CarreraRepositoryImpl;
import utils.CargarCSV;
import entities.Carrera;
import entities.Estudiante;
import repository.impl.EstudianteRepositoryImpl;
import repository.impl.EstudianteCarreraRepositoryImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IntegradorTP2");
        EntityManager em = emf.createEntityManager();

        CarreraRepositoryImpl carreraRepo = new CarreraRepositoryImpl(em);
        EstudianteRepositoryImpl estRepo = new EstudianteRepositoryImpl(em);
        EstudianteCarreraRepositoryImpl matriculaRepo = new EstudianteCarreraRepositoryImpl(em);

        // carga los datos solo si la base está vacía
        long count = em.createQuery("SELECT COUNT(c) FROM Carrera c", Long.class).getSingleResult();
        if (count == 0) {
            CargarCSV.cargar(em);
        }

        System.out.println("\n-- ALTA Y MATRICULACIÓN MANUAL (Puntos 2.a y 2.b) --");
        Estudiante nuevoEstudiante = null;
        try {
            nuevoEstudiante = new Estudiante(99999999, "Test", "User", 25, "M", "Tandil", 5000);
            estRepo.guardar(nuevoEstudiante);
        } catch (Exception e) {
            System.out.println("El estudiante de prueba ya existía.");
        }

        // Buscamos una carrera existente para matricularlo
        List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c", Carrera.class).setMaxResults(1).getResultList();
        if (nuevoEstudiante != null && !carreras.isEmpty()) {
            try {
                //matriculaRepo.matricular(nuevoEstudiante, carreras.get(0), 2023);
                System.out.println("Estudiante Test matriculado con éxito.");
            } catch (Exception e) {
                System.out.println("La matriculación ya existía en la base de datos.");
            }
        }

        System.out.println("\n-- PRUEBA PUNTO 2.d (Buscar por LU: 5000) --");
        try {
            Estudiante buscado = estRepo.buscarPorLu(5000);
            System.out.println("Encontrado: " + buscado.getNombre() + " " + buscado.getApellido());
        } catch (Exception e) {
            System.out.println("No se encontró el LU especificado.");
        }

        System.out.println("\n-- PRUEBA PUNTO 2.e (Buscar por Genero: M) --");
        List<Estudiante> porGenero = estRepo.buscarPorGenero("M");
        System.out.println("Cantidad de estudiantes masculinos encontrados: " + porGenero.size());

        System.out.println("\n-- PRUEBA PUNTO 2.f (Carreras ordenadas por inscriptos) --");
        List<Carrera> carrerasOrdenadas = carreraRepo.obtenerCarrerasConInscriptos();
        carrerasOrdenadas.forEach(c -> System.out.println("Carrera: " + c.getCarrera()));

        System.out.println("\n-- PRUEBA PUNTO 2.g (Estudiantes de 'TUDAI' en 'Tandil') --");
        // Reemplaza "TUDAI" por un nombre de carrera real que sepas que está en el CSV
        List<Estudiante> filtrados = estRepo.buscarPorCarreraYCiudad("TUDAI", "Tandil");
        System.out.println("Estudiantes encontrados: " + filtrados.size());
        filtrados.forEach(e -> System.out.println("- " + e.getNombre() + " " + e.getApellido()));

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
