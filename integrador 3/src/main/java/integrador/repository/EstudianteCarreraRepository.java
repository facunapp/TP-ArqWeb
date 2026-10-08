package integrador.repository;

import entities.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteCarreraRepository
extends JpaRepository<EstudianteCarrera, Integer> {
}