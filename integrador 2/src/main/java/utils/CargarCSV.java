package utils;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import jakarta.persistence.EntityManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStreamReader;
import java.io.Reader;

public class CargarCSV {

    public static void cargar(EntityManager em) throws Exception {
        em.getTransaction().begin();

        Reader readerCarreras = new InputStreamReader(
                CargarCSV.class.getClassLoader().getResourceAsStream("carreras.csv"));
        CSVParser parserCarreras = CSVFormat.DEFAULT.withHeader().parse(readerCarreras);

        for (CSVRecord row : parserCarreras) {
            Carrera c = new Carrera(
                Integer.parseInt(row.get("id_carrera")),
                row.get("carrera"),
                Integer.parseInt(row.get("duracion"))
            );
            em.persist(c);
        }
        parserCarreras.close();

        Reader readerEstudiantes = new InputStreamReader(
                CargarCSV.class.getClassLoader().getResourceAsStream("estudiantes.csv"));
        CSVParser parserEstudiantes = CSVFormat.DEFAULT.withHeader().parse(readerEstudiantes);

        for (CSVRecord row : parserEstudiantes) {
            Estudiante e = new Estudiante(
                Integer.parseInt(row.get("DNI")),
                row.get("nombre"),
                row.get("apellido"),
                Integer.parseInt(row.get("edad")),
                row.get("genero"),
                row.get("ciudad"),
                Integer.parseInt(row.get("LU"))
            );
            em.persist(e);
        }
        parserEstudiantes.close();

        Reader readerEC = new InputStreamReader(
                CargarCSV.class.getClassLoader().getResourceAsStream("estudianteCarrera.csv"));
        CSVParser parserEC = CSVFormat.DEFAULT.withHeader().parse(readerEC);

        for (CSVRecord row : parserEC) {
            Estudiante est = em.find(Estudiante.class, Integer.parseInt(row.get("id_estudiante")));
            Carrera car = em.find(Carrera.class, Integer.parseInt(row.get("id_carrera")));
            EstudianteCarrera ec = new EstudianteCarrera(
                Integer.parseInt(row.get("id")),
                est,
                car,
                Integer.parseInt(row.get("inscripcion")),
                Integer.parseInt(row.get("graduacion")),
                Integer.parseInt(row.get("antiguedad"))
            );
            em.persist(ec);
        }
        parserEC.close();

        em.getTransaction().commit();
        System.out.println("Datos cargados correctamente.");
    }
}
