package integrador.controller;

import dto.CarreraDTO;
import dto.EstudianteDTO;
import dto.ReporteCarreraDTO;
import entities.Carrera;
import entities.Estudiante;
import integrador.repository.CarreraRepository;
import integrador.repository.EstudianteRepository;
import integrador.service.EstudianteCarreraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class EstudianteCarreraController {

    private final EstudianteCarreraService estudianteCarreraService;
    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;

    public EstudianteCarreraController(
            EstudianteCarreraService estudianteCarreraService,
            EstudianteRepository estudianteRepository,
            CarreraRepository carreraRepository) {
        this.estudianteCarreraService = estudianteCarreraService;
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    // 2.b - Matricular un estudiante
    @PostMapping("/{dni}/{idCarrera}")
public ResponseEntity<MatriculaDTO> matricular(
        @PathVariable int dni,
        @PathVariable int idCarrera,
        @RequestParam int anio) {

    Estudiante estudiante = estudianteRepository.findById(dni)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    Carrera carrera = carreraRepository.findById(idCarrera)
            .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

    MatriculaDTO matriculaDTO = estudianteCarreraService.matricular(estudiante, carrera, anio);
    
    return ResponseEntity.ok(matriculaDTO);
}


    // 2.g - Estudiantes por carrera y ciudad (Devuelve DTOs)
    @GetMapping("/estudiantes")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesPorCarreraYCiudad(
            @RequestParam Long idCarrera,
            @RequestParam String ciudad) {
        List<EstudianteDTO> estudiantes = estudianteCarreraService.getEstudiantesPorCarreraYCiudad(idCarrera, ciudad);
        return ResponseEntity.ok(estudiantes);
    }

    // 2.f - Carreras ordenadas por inscriptos (Devuelve CarreraDTOs)
    @GetMapping("/carreras/ordenadas-por-inscriptos")
    public ResponseEntity<List<CarreraDTO>> getCarrerasOrdenadasPorInscriptos() {
        return ResponseEntity.ok(estudianteCarreraService.getCarrerasOrdenadasPorInscriptos());
    }

    // 2.h / 3 - Reporte de carreras (Devuelve ReporteCarreraDTO)
    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteCarreraDTO>> getReporteCarreras() {
        List<ReporteCarreraDTO> reporte = estudianteCarreraService.getReporteCarreras();
        return ResponseEntity.ok(reporte);
    }
}
