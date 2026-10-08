package integrador.service;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import integrador.repository.EstudianteCarreraRepository;
import org.springframework.stereotype.Service;

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
}