package dto;

import entities.EstudianteCarrera;

public class MatriculaDTO {
    private int dniEstudiante;
    private String nombreEstudiante;
    private int idCarrera;
    private String nombreCarrera;
    private int inscripcion;
    private int graduacion;

    public MatriculaDTO(EstudianteCarrera ec) {
        this.dniEstudiante = ec.getEstudiante().getDni();
        this.nombreEstudiante = ec.getEstudiante().getNombre() + " " + ec.getEstudiante().getApellido();
        this.idCarrera = ec.getCarrera().getIdCarrera();
        this.nombreCarrera = ec.getCarrera().getCarrera();
        this.inscripcion = ec.getInscripcion();
        this.graduacion = ec.getGraduacion();
    }

    public int getDniEstudiante() { return dniEstudiante; }
    public String getNombreEstudiante() { return nombreEstudiante; }
    public int getIdCarrera() { return idCarrera; }
    public String getNombreCarrera() { return nombreCarrera; }
    public int getInscripcion() { return inscripcion; }
    public int getGraduacion() { return graduacion; }
}