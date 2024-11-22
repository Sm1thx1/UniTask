package datos;

import java.io.*;
import java.util.*;

public class GestionDatos {
    public static class Usuario {
        public String nombre;
        public String apellido;
        public String documento;
        public String contraseña;
        public int tipoCuenta;
        public String codigoAula;

        public Usuario(String nombre, String apellido, String documento, String contraseña, int tipoCuenta,
                String codigoAula) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.documento = documento;
            this.contraseña = contraseña;
            this.tipoCuenta = tipoCuenta;
            this.codigoAula = codigoAula;
        }
    }

    private final Map<String, Usuario> usuarios = new HashMap<>();
    private final List<Actividad> actividades = new ArrayList<>();
    private final Map<String, Map<String, Integer>> notas = new HashMap<>();

    public void cargarDatos() {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                String nombre = campos[0];
                String apellido = campos[1];
                String documento = campos[2];
                String contraseña = campos[3];
                int tipoCuenta = Integer.parseInt(campos[4]);
                String codigoAula = campos[5];

                Usuario usuario = new Usuario(nombre, apellido, documento, contraseña, tipoCuenta, codigoAula);
                usuarios.put(nombre.toLowerCase(), usuario);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    public void guardarDatos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.csv"))) {
            for (Usuario usuario : usuarios.values()) {
                bw.write(String.join(",", usuario.nombre, usuario.apellido, usuario.documento,
                        usuario.contraseña, String.valueOf(usuario.tipoCuenta), usuario.codigoAula));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public Usuario obtenerUsuario(String nombre) {
        return usuarios.get(nombre.toLowerCase());
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.nombre.toLowerCase()))
            return false;
        usuarios.put(usuario.nombre.toLowerCase(), usuario);
        return true;
    }

    public boolean validarCredenciales(String nombre, String contraseña) {
        Usuario usuario = usuarios.get(nombre.toLowerCase());
        return usuario != null && usuario.contraseña.equals(contraseña);
    }

    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    public List<Actividad> obtenerActividades() {
        return actividades;
    }

    public void asignarNota(String actividadNombre, String alumnoNombre, int nota) {
        notas.computeIfAbsent(actividadNombre, k -> new HashMap<>()).put(alumnoNombre, nota);
    }

    public Map<String, Integer> obtenerNotas(String actividadNombre) {
        return notas.getOrDefault(actividadNombre, new HashMap<>());
    }

    public Usuario obtenerUsuarioPorCodigoAula(String codigoAula) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.codigoAula.equals(codigoAula)) {
                return usuario;
            }
        }
        return null;
    }
}
