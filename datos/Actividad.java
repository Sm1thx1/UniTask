package datos;

public class Actividad {
    private String nombre;
    private String descripcion;

    public Actividad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Actividad: " + nombre + " - " + descripcion;
    }
}
