import java.util.Scanner;
import datos.GestionDatos;
import menu.Menu;

public class Main {
    public static void main(String[] args) {
        GestionDatos gestionDatos = new GestionDatos();
        gestionDatos.cargarDatos(); // Carga los datos desde el archivo CSV

        Scanner scanner = new Scanner(System.in); // Scanner único para toda la aplicación
        Menu menu = new Menu(gestionDatos);
        menu.mostrarMenuPrincipal(); // Muestra el menú principal

        gestionDatos.guardarDatos(); // Guarda los datos al salir
        scanner.close(); // Cierra el scanner al finalizar
        System.out.println("Datos guardados. Saliendo del programa...");
    }
}
