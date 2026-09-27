package repository;

import entities.Estudiante;

import java.util.List;

public interface EstudianteRepositoy {

    void guardar(Estudiante estudiante);
    Estudiante buscarPorLu(int lu);
    List<Estudiante> obtenerTodosPorCriterio(); // Criterio predefinido (ej: por apellido)
    List<Estudiante> buscarPorGenero(String genero);
}
