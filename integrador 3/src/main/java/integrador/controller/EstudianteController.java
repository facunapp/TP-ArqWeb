package integrador.controller;

import dto.EstudianteDTO;
import entities.Estudiante;
import integrador.service.EstudianteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // 2.a - Dar de alta (devuelve DTO)
    @PostMapping
    public EstudianteDTO guardar(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    // 2.c - Todos los estudiantes (devuelve DTOs)
    @GetMapping
    public List<EstudianteDTO> obtenerTodos() {
        return estudianteService.obtenerTodos();
    }

    // 2.d - Buscar por LU (devuelve DTO)
    @GetMapping("/lu/{lu}")
    public EstudianteDTO buscarPorLu(@PathVariable int lu) {
        return estudianteService.buscarPorLu(lu);
    }

    // 2.e - Buscar por género (devuelve DTOs)
    @GetMapping("/genero/{genero}")
    public List<EstudianteDTO> obtenerPorGenero(@PathVariable String genero) {
        return estudianteService.obtenerPorGenero(genero);
    }
}
