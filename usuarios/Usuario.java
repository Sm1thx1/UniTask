package usuarios;

import datos.GestionDatos;

public abstract class Usuario {
    private final String nombre;
    private final String apellido;
    private final String documento;

    public Usuario(String nombre, String apellido, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public abstract void mostrarMenu(GestionDatos gestionDatos);
}
