package repository;

import entities.Estudiante;

import java.util.List;

public interface EstudianteRepository {

    void guardar(Estudiante estudiante);
    Estudiante buscarPorLu(int lu);
    List<Estudiante> obtenerTodosPorCriterio(); // Criterio predefinido (ej: por apellido)
    List<Estudiante> buscarPorGenero(String genero);
    List<Estudiante> buscarPorCarreraYCiudad(String nombreCarrera, String ciudad);
}
