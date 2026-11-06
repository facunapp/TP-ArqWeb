package dto;

public class EstudianteDTO {
    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private int lu;

    public EstudianteDTO(int dni, String nombre, String apellido, int edad, String genero, String ciudad, int lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }

    // Constructor que recibe la entidad para mapear fácilmente si es necesario
    public EstudianteDTO(entities.Estudiante e) {
        this.dni = e.getDni();
        this.nombre = e.getNombre();
        this.apellido = e.getApellido();
        this.edad = e.getEdad();
        this.genero = e.getGenero();
        this.ciudad = e.getCiudad();
        this.lu = e.getLu();
    }

    public int getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getGenero() { return genero; }
    public String getCiudad() { return ciudad; }
    public int getLu() { return lu; }
}
