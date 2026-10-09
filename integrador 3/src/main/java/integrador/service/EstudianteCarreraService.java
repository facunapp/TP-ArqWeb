package integrador.service;

import dto.ReporteCarreraDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import integrador.repository.EstudianteCarreraRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class EstudianteCarreraService {

private final EstudianteCarreraRepository repository;

public EstudianteCarreraService(EstudianteCarreraRepository repository) {
this.repository = repository;
}

public EstudianteCarrera matricular(
Estudiante estudiante,
Carrera carrera,
int anioInscripcion) {

EstudianteCarrera matricula = new EstudianteCarrera();

matricula.setEstudiante(estudiante);
matricula.setCarrera(carrera);
matricula.setInscripcion(anioInscripcion);
matricula.setGraduacion(0);
matricula.setAntiguedad(0);

return repository.save(matricula);
}

    public List<Estudiante> getEstudiantesPorCarreraYCiudad(Long idCarrera, String ciudad) {
        // validar que se haya ingresado una ciudad
        if (ciudad == null || ciudad.isBlank()) {
            throw new IllegalArgumentException("La ciudad es obligatoria");
        }

        // repository ejecuta la sentencia
        return this.repository.findEstudiantesByCarreraYCiudad(idCarrera, ciudad);
    }


    public List<ReporteCarreraDTO> getReporteCarreras() {
        // TreeMap<Carrera, TreeMap<Año, DTO>> mantiene el orden alfabético y cronológico automáticamente
        Map<String, Map<Integer, ReporteCarreraDTO>> mapaReporte = new TreeMap<>();

        // 1. Procesar inscriptos
        for (Object[] fila : repository.getInscriptosPorAnio()) {
            String carrera = (String) fila[0];
            int anio = (int) fila[1];
            long inscriptos = (long) fila[2];

            mapaReporte.putIfAbsent(carrera, new TreeMap<>());
            mapaReporte.get(carrera).put(anio, new ReporteCarreraDTO(carrera, anio, inscriptos, 0));
        }

        // 2. Mezclar egresados
        for (Object[] fila : repository.getEgresadosPorAnio()) {
            String carrera = (String) fila[0];
            int anio = (int) fila[1];
            long egresados = (long) fila[2];

            mapaReporte.putIfAbsent(carrera, new TreeMap<>());
            Map<Integer, ReporteCarreraDTO> reportesAnio = mapaReporte.get(carrera);

            if (reportesAnio.containsKey(anio)) {
                reportesAnio.get(anio).setCantidadEgresados(egresados);
            } else {
                reportesAnio.put(anio, new ReporteCarreraDTO(carrera, anio, 0, egresados));
            }
        }

        // 3. Aplanar en una lista ordenada
        List<ReporteCarreraDTO> resultado = new ArrayList<>();
        for (Map<Integer, ReporteCarreraDTO> porAnio : mapaReporte.values()) {
            resultado.addAll(porAnio.values());
        }
        return resultado;
    }
}