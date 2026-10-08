package integrador.controller;

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

    // 2.a - Dar de alta
    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    // 2.c - Todos los estudiantes
    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudianteService.obtenerTodos();
    }

    // 2.d - Buscar por LU
    @GetMapping("/lu/{lu}")
    public Estudiante buscarPorLu(@PathVariable int lu) {
        return estudianteService.buscarPorLu(lu);
    }
}
