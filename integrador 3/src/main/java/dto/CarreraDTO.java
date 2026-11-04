package dto;

public class CarreraDTO {
    private int idCarrera;
    private String carrera;
    private int duracion;

    public CarreraDTO(int idCarrera, String carrera, int duracion) {
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.duracion = duracion;
    }

    public CarreraDTO(entities.Carrera c) {
        this.idCarrera = c.getIdCarrera();
        this.carrera = c.getCarrera();
        this.duracion = c.getDuracion();
    }

    public int getIdCarrera() { return idCarrera; }
    public String getCarrera() { return carrera; }
    public int getDuracion() { return duracion; }
}
