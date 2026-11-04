package entities;

import java.io.Serializable;
import java.util.Objects;

public class EstudianteCarreraId implements Serializable {

    private int estudiante;
    private int carrera;

    public EstudianteCarreraId() {}

    public EstudianteCarreraId(int estudiante, int carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstudianteCarreraId)) return false;
        EstudianteCarreraId that = (EstudianteCarreraId) o;
        return estudiante == that.estudiante && carrera == that.carrera;
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudiante, carrera);
    }
}