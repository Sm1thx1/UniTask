package usuarios;

import datos.GestionDatos;
import datos.Actividad;

import java.util.Map;
import java.util.Scanner;

public class Estudiante extends Usuario {
    private final Scanner scanner = new Scanner(System.in);

    public Estudiante(String nombre, String apellido, String documento) {
        super(nombre, apellido, documento);
    }

    @Override
    public void mostrarMenu(GestionDatos gestionDatos) {
        while (true) {
            System.out.println("------ Menú Estudiante ------");
            System.out.println("1. Ver actividades");
            System.out.println("2. Ver notas");
            System.out.println("3. Salir");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    verActividades(gestionDatos);
                    break;
                case "2":
                    verNotas(gestionDatos);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void verActividades(GestionDatos gestionDatos) {
        System.out.println("Actividades disponibles:");
        for (Actividad actividad : gestionDatos.obtenerActividades()) {
            System.out.println(actividad);
        }
    }

    private void verNotas(GestionDatos gestionDatos) {
        System.out.println("Ingresa el nombre de la actividad:");
        String actividadNombre = scanner.nextLine();

        Map<String, Integer> notas = gestionDatos.obtenerNotas(actividadNombre);
        if (notas == null || !notas.containsKey(getNombre())) {
            System.out.println("No tienes notas asignadas para esta actividad.");
            return;
        }

        System.out.println("Tu nota para " + actividadNombre + " es: " + notas.get(getNombre()));
    }
}
