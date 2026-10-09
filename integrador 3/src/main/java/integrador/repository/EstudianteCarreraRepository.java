package integrador.repository;

import entities.Estudiante;
import entities.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteCarreraRepository
extends JpaRepository<EstudianteCarrera, Integer> {

    @Query("SELECT e FROM EstudianteCarrera ec JOIN ec.estudiante e JOIN ec.carrera c WHERE c.idCarrera = :idCarrera AND e.ciudad = :ciudad")
    List<Estudiante> findEstudiantesByCarreraYCiudad(@Param("idCarrera") Long idCarrera, @Param("ciudad") String ciudad);


    // Inscriptos por carrera y año
    @Query("SELECT c.carrera, ec.inscripcion, COUNT(ec) " +
            "FROM EstudianteCarrera ec JOIN ec.carrera c " +
            "GROUP BY c.carrera, ec.inscripcion " +
            "ORDER BY c.carrera ASC, ec.inscripcion ASC")
    List<Object[]> getInscriptosPorAnio();

    // Egresados por carrera y año (filtrando graduacion > 0)
    @Query("SELECT c.carrera, ec.graduacion, COUNT(ec) " +
            "FROM EstudianteCarrera ec JOIN ec.carrera c " +
            "WHERE ec.graduacion > 0 " +
            "GROUP BY c.carrera, ec.graduacion " +
            "ORDER BY c.carrera ASC, ec.graduacion ASC")
    List<Object[]> getEgresadosPorAnio();
}
