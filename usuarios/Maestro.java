package usuarios;

import datos.GestionDatos;
import datos.Actividad;

import java.util.Scanner;

public class Maestro extends Usuario {
    private final Scanner scanner = new Scanner(System.in);

    public Maestro(String nombre, String apellido, String documento) {
        super(nombre, apellido, documento);
    }

    @Override
    public void mostrarMenu(GestionDatos gestionDatos) {
        while (true) {
            System.out.println("------ Menú Maestro ------");
            System.out.println("1. Agregar actividad");
            System.out.println("2. Asignar notas");
            System.out.println("3. Ver actividades");
            System.out.println("4. Salir");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    agregarActividad(gestionDatos);
                    break;
                case "2":
                    asignarNotas(gestionDatos);
                    break;
                case "3":
                    verActividades(gestionDatos);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void agregarActividad(GestionDatos gestionDatos) {
        System.out.println("Ingresa el nombre de la actividad:");
        String nombre = scanner.nextLine();
        System.out.println("Ingresa la descripción de la actividad:");
        String descripcion = scanner.nextLine();

        Actividad actividad = new Actividad(nombre, descripcion);
        gestionDatos.agregarActividad(actividad);
        System.out.println("Actividad agregada exitosamente.");
    }

    private void asignarNotas(GestionDatos gestionDatos) {
        System.out.println("Ingresa el nombre de la actividad:");
        String actividadNombre = scanner.nextLine();

        Actividad actividad = gestionDatos.obtenerActividades().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(actividadNombre))
                .findFirst()
                .orElse(null);

        if (actividad == null) {
            System.out.println("La actividad no existe.");
            return;
        }

        System.out.println("Ingresa el nombre del estudiante:");
        String estudianteNombre = scanner.nextLine();

        System.out.println("Ingresa la nota:");
        int nota;
        try {
            nota = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nota no válida.");
            return;
        }

        gestionDatos.asignarNota(actividad.getNombre(), estudianteNombre, nota);
        System.out.println("Nota asignada exitosamente.");
    }

    private void verActividades(GestionDatos gestionDatos) {
        System.out.println("Actividades disponibles:");
        for (Actividad actividad : gestionDatos.obtenerActividades()) {
            System.out.println(actividad);
        }
    }
}
