package integrador.service;

import entities.Estudiante;
import integrador.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {

private final EstudianteRepository estudianteRepository;

public EstudianteService(EstudianteRepository estudianteRepository) {
this.estudianteRepository = estudianteRepository;
}

public Estudiante guardar(Estudiante estudiante) {
return estudianteRepository.save(estudiante);
}
}