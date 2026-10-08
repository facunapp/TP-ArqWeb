package integrador.service;

import entities.Estudiante;
import integrador.repository.EstudianteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // 2.a
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // 2.c
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "apellido")
        );
    }

    // 2.d
    public Estudiante buscarPorLu(int lu) {
        return estudianteRepository.findByLu(lu);
    }
}
