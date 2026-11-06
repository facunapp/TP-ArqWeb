package integrador.repository;
import entities.Carrera;
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


    // Reporte anual resuelto en la base: inscriptos y egresados por carrera y año
    @Query(value = """
        SELECT carrera, anio, SUM(inscriptos), SUM(egresados)
        FROM (
            SELECT c.carrera AS carrera, ec.inscripcion AS anio, COUNT(*) AS inscriptos, 0 AS egresados
            FROM estudiante_carrera ec JOIN carrera c ON ec.id_carrera = c.id_carrera
            GROUP BY c.carrera, ec.inscripcion
            UNION ALL
            SELECT c.carrera AS carrera, ec.graduacion AS anio, 0 AS inscriptos, COUNT(*) AS egresados
            FROM estudiante_carrera ec JOIN carrera c ON ec.id_carrera = c.id_carrera
            WHERE ec.graduacion > 0
            GROUP BY c.carrera, ec.graduacion
        ) AS combined
        GROUP BY carrera, anio
        ORDER BY carrera ASC, anio ASC
        """, nativeQuery = true)
    List<Object[]> getReporteAnual();


    // Carreras con estudiantes inscriptos, ordenadas por cantidad de inscriptos
    @Query("SELECT c FROM EstudianteCarrera ec JOIN ec.carrera c GROUP BY c ORDER BY COUNT(ec) DESC")
    List<Carrera> getCarrerasOrdenadasPorInscriptos();
}
