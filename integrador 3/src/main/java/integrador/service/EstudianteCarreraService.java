package integrador.service;
import dto.CarreraDTO;
import dto.EstudianteDTO;
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
import java.util.stream.Collectors;
@Service
public class EstudianteCarreraService {
private final EstudianteCarreraRepository repository;

public EstudianteCarreraService(EstudianteCarreraRepository repository) {
    this.repository = repository;
}

public MatriculaDTO matricular(Estudiante estudiante, Carrera carrera, int anioInscripcion) {
    EstudianteCarrera matricula = new EstudianteCarrera(estudiante, carrera, anioInscripcion, 0);
    EstudianteCarrera saved = repository.save (http://repository.save/)(matricula);
    return new MatriculaDTO(saved);
}
public List\<EstudianteDTO> getEstudiantesPorCarreraYCiudad(Long idCarrera, String ciudad) {
    if (ciudad == null || ciudad.isBlank()) {
        throw new IllegalArgumentException("La ciudad es obligatoria");
    }
    return this.repository.findEstudiantesByCarreraYCiudad(idCarrera, ciudad)
            .stream()
            .map(EstudianteDTO::new)
            .collect(Collectors.toList());
}

public List\<ReporteCarreraDTO> getReporteCarreras() {
    Map\<String, Map<Integer, ReporteCarreraDTO>> mapaReporte = new TreeMap<>();

    for (Object[] fila : repository.getInscriptosPorAnio()) {
        String carrera = (String) fila[0];
        int anio = (int) fila[1];
        long inscriptos = (long) fila[2];

        mapaReporte.putIfAbsent(carrera, new TreeMap<>());
        mapaReporte.get(carrera).put(anio, new ReporteCarreraDTO(carrera, anio, inscriptos, 0));
    }

    for (Object[] fila : repository.getEgresadosPorAnio()) {
        String carrera = (String) fila[0];
        int anio = (int) fila[1];
        long egresados = (long) fila[2];

        mapaReporte.putIfAbsent(carrera, new TreeMap<>());
        Map\<Integer, ReporteCarreraDTO> reportesAnio = mapaReporte.get(carrera);

        if (reportesAnio.containsKey(anio)) {
            reportesAnio.get(anio).setCantidadEgresados(egresados);
        } else {
            reportesAnio.put(anio, new ReporteCarreraDTO(carrera, anio, 0, egresados));
        }
    }

    List\<ReporteCarreraDTO> resultado = new ArrayList<>();
    for (Map\<Integer, ReporteCarreraDTO> porAnio : mapaReporte.values()) {
        resultado.addAll(porAnio.values());
    }
    return resultado;
}

public List\<CarreraDTO> getCarrerasOrdenadasPorInscriptos() {
    return repository.getCarrerasOrdenadasPorInscriptos()
            .stream()
            .map(CarreraDTO::new)
            .collect(Collectors.toList());
}
}
