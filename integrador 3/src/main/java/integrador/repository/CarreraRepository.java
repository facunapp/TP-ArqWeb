package repository;

import dto.ReporteCarreraDTO;
import entities.Carrera;

import java.util.List;

public interface CarreraRepository {

    void guardar(Carrera carrera);
    List<Carrera> obtenerCarrerasConInscriptos();
    List<ReporteCarreraDTO> generarReporteAnual();
}
