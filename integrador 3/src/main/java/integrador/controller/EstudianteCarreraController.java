package integrador.controller;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import integrador.repository.CarreraRepository;
import integrador.repository.EstudianteRepository;
import integrador.service.EstudianteCarreraService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class EstudianteCarreraController {

private final EstudianteCarreraService matriculaService;
private final EstudianteRepository estudianteRepository;
private final CarreraRepository carreraRepository;

public EstudianteCarreraController(
EstudianteCarreraService matriculaService,
EstudianteRepository estudianteRepository,
CarreraRepository carreraRepository) {

this.matriculaService = matriculaService;
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

return matriculaService.matricular(
estudiante,
carrera,
anio
);
}
}