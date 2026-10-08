package integrador.repository;

import entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraRepository
extends JpaRepository<Carrera, Integer> {
}