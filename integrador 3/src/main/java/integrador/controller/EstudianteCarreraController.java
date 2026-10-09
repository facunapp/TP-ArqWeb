package integrador.controller;

import dto.ReporteCarreraDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import integrador.repository.CarreraRepository;
import integrador.repository.EstudianteRepository;
import integrador.service.EstudianteCarreraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class EstudianteCarreraController {

private final EstudianteCarreraService EstudianteCarreraService;
private final EstudianteRepository estudianteRepository;
private final CarreraRepository carreraRepository;

public EstudianteCarreraController(
EstudianteCarreraService matriculaService,
EstudianteRepository estudianteRepository,
CarreraRepository carreraRepository) {

this.EstudianteCarreraService = matriculaService;
this.estudianteRepository = estudianteRepository;
this.carreraRepository = carreraRepository;
}

@PostMapping("/{dni}/{idCarrera}")
public EstudianteCarrera matricular(
@PathVariable int dni,
@PathVariable int idCarrera,
@RequestParam int anio) {

Estudiante estudiante = estudianteRepository.findById(dni)
.orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

Carrera carrera = carreraRepository.findById(idCarrera)
.orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

return EstudianteCarreraService.matricular(
estudiante,
carrera,
anio
);
}


    @GetMapping("/estudiantes")
    public ResponseEntity<List<Estudiante>> getEstudiantesPorCarreraYCiudad(
            @RequestParam Long idCarrera,
            @RequestParam String ciudad) {

        List<Estudiante> estudiantes = EstudianteCarreraService.getEstudiantesPorCarreraYCiudad(idCarrera, ciudad);
        return ResponseEntity.ok(estudiantes);
    }

    // Punto h) Generar el reporte de carreras (inscriptos y egresados por año)
    // Ejemplo de llamada: GET /estudiantes-carreras/reporte
    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteCarreraDTO>> getReporteCarreras() {
        List<ReporteCarreraDTO> reporte = EstudianteCarreraService.getReporteCarreras();
        return ResponseEntity.ok(reporte);
    }
}