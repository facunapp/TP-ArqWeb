package integrador.service;
import dto.CarreraDTO;
import dto.EstudianteDTO;
import dto.MatriculaDTO;
import dto.ReporteCarreraDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import integrador.repository.EstudianteCarreraRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EstudianteCarreraService {
private final EstudianteCarreraRepository repository;

public EstudianteCarreraService(EstudianteCarreraRepository repository) {
    this.repository = repository;
}

public MatriculaDTO matricular(Estudiante estudiante, Carrera carrera, int anioInscripcion) {
    EstudianteCarrera matricula = new EstudianteCarrera(estudiante, carrera, anioInscripcion, 0);
    EstudianteCarrera saved = repository.save(matricula);
    return new MatriculaDTO(saved);
}
public List<EstudianteDTO> getEstudiantesPorCarreraYCiudad(Long idCarrera, String ciudad) {
    if (ciudad == null || ciudad.isBlank()) {
        throw new IllegalArgumentException("La ciudad es obligatoria");
    }
    return this.repository.findEstudiantesByCarreraYCiudad(idCarrera, ciudad)
            .stream()
            .map(EstudianteDTO::new)
            .collect(Collectors.toList());
}

public List<ReporteCarreraDTO> getReporteCarreras() {
    return repository.getReporteAnual().stream()
            .map(f -> new ReporteCarreraDTO(
                    (String) f[0],
                    ((Number) f[1]).intValue(),
                    ((Number) f[2]).longValue(),
                    ((Number) f[3]).longValue()))
            .collect(Collectors.toList());
}

public List<CarreraDTO> getCarrerasOrdenadasPorInscriptos() {
    return repository.getCarrerasOrdenadasPorInscriptos()
            .stream()
            .map(CarreraDTO::new)
            .collect(Collectors.toList());
}
}
