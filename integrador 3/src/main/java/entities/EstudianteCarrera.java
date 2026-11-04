package entities;

import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante_carrera")
@IdClass(EstudianteCarreraId.class)
public class EstudianteCarrera {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "dni")
    private Estudiante estudiante;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    private Carrera carrera;

    @Column(nullable = false)
    private int inscripcion;
    
    private int graduacion; // 0 si no se graduó, o el año si se graduó

    public EstudianteCarrera() {}

    public EstudianteCarrera(Estudiante estudiante, Carrera carrera, int inscripcion, int graduacion) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }

    public int getInscripcion() { return inscripcion; }
    public void setInscripcion(int inscripcion) { this.inscripcion = inscripcion; }

    public int getGraduacion() { return graduacion; }
    public void setGraduacion(int graduacion) { this.graduacion = graduacion; }

    // La antigüedad se calcula dinámicamente, no se almacena en la BD
    public int getAntiguedad() {
        return Year.now().getValue() - inscripcion;
    }
}