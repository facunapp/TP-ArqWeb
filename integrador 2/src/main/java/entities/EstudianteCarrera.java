package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante_carrera")
public class EstudianteCarrera {

    @EmbeddedId
    private EstudianteCarreraId id = new EstudianteCarreraId();

    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    private int inscripcion;
    private int graduacion; // 0 si no se graduó

    public EstudianteCarrera() {}

    public EstudianteCarrera(Estudiante estudiante, Carrera carrera, int inscripcion, int graduacion) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.id = new EstudianteCarreraId(estudiante.getDni(), carrera.getIdCarrera());
    }

    public EstudianteCarreraId getId() { 
        return id; 
    }
    public void setId(EstudianteCarreraId id) { 
        this.id = id; 
    }

    public Estudiante getEstudiante() { 
        return estudiante; 
    }
    public void setEstudiante(Estudiante estudiante) { 
        this.estudiante = estudiante; 
    }

    public Carrera getCarrera() {
         return carrera; 
        }
    public void setCarrera(Carrera carrera) { 
        this.carrera = carrera; 
    }

    public int getInscripcion() { 
        return inscripcion; 
    }
    public void setInscripcion(int inscripcion) { 
        this.inscripcion = inscripcion; 
    }

    public int getGraduacion() { 
        return graduacion; 
    }
    public void setGraduacion(int graduacion) { 
        this.graduacion = graduacion; 
    }

    // Opcional: método para calcular la antigüedad al vuelo si se necesita en la lógica
    public int getAntiguedad(int anioActual) {
        return anioActual - inscripcion;
    }
}