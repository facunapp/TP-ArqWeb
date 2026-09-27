package repository;

import entities.Carrera;
import entities.Estudiante;

import java.util.List;

public interface EstudianteCarreraRepository {

    void matricular(Estudiante estudiante, Carrera carrera, int anioInscripcion);
    List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(int idCarrera, String ciudad);
}
