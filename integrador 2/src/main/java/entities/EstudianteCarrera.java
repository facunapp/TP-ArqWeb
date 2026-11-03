package entities;

import jakarta.persistence.*;
import java.time.Year;

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

    private int inscripcion;
    private int graduacion; // 0 si no se graduó

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

    // la antiguedad se calcula, no se almacena
    public int getAntiguedad() {
        return Year.now().getValue() - inscripcion;
    }
}
