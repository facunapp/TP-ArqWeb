package integrador.service;

import dto.EstudianteDTO;
import entities.Estudiante;
import integrador.repository.EstudianteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // 2.a - Guarda y devuelve el DTO
    public EstudianteDTO guardar(Estudiante estudiante) {
        Estudiante saved = estudianteRepository.save(estudiante);
        return new EstudianteDTO(saved);
    }

    // 2.c - Mapea la lista de entidades a lista de DTOs
    public List<EstudianteDTO> obtenerTodos() {
        return estudianteRepository.findAll(Sort.by(Sort.Direction.ASC, "apellido"))
                .stream()
                .map(EstudianteDTO::new)
                .collect(Collectors.toList());
    }

    // 2.d
    public EstudianteDTO buscarPorLu(int lu) {
        Estudiante e = estudianteRepository.findByLu(lu);
        return e != null ? new EstudianteDTO(e) : null;
    }

    // 2.e
    public List<EstudianteDTO> obtenerPorGenero(String genero) {
        return estudianteRepository.findByGenero(genero)
                .stream()
                .map(EstudianteDTO::new)
                .collect(Collectors.toList());
    }
}