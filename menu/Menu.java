package menu;

import datos.GestionDatos;
import usuarios.Estudiante;
import usuarios.Maestro;
import usuarios.Padre;

import java.util.Scanner;

public class Menu {
    private final GestionDatos gestionDatos;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(GestionDatos gestionDatos) {
        this.gestionDatos = gestionDatos;
    }

    public void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("----------------------------");
            System.out.println("---- Bienvenido a UniTask ----");
            System.out.println("----------------------------");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Salir");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    iniciarSesion();
                    break;
                case "2":
                    crearCuenta();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void iniciarSesion() {
        System.out.println("Ingresa tu nombre de usuario:");
        String nombre = scanner.nextLine().toLowerCase();
        System.out.println("Ingresa tu contraseña:");
        String contraseña = scanner.nextLine();

        if (gestionDatos.validarCredenciales(nombre, contraseña)) {
            System.out.println("Inicio de sesión exitoso.");
            GestionDatos.Usuario usuario = gestionDatos.obtenerUsuario(nombre);
            mostrarMenuPorTipo(usuario);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private void mostrarMenuPorTipo(GestionDatos.Usuario usuario) {
        switch (usuario.tipoCuenta) {
            case 1:
                new Estudiante(usuario.nombre, usuario.apellido, usuario.documento).mostrarMenu(gestionDatos);
                break;
            case 2:
                new Maestro(usuario.nombre, usuario.apellido, usuario.documento).mostrarMenu(gestionDatos);
                break;
            case 3:
                new Padre(usuario.nombre, usuario.apellido, usuario.documento).mostrarMenu(gestionDatos);
                break;
            default:
                System.out.println("Tipo de cuenta no válido.");
                break;
        }
    }

    private void crearCuenta() {
        System.out.println("Ingresa tu nombre:");
        String nombre = scanner.nextLine().toLowerCase();
        if (gestionDatos.obtenerUsuario(nombre) != null) {
            System.out.println("Este nombre de usuario ya está en uso.");
            return;
        }

        System.out.println("Ingresa tu apellido:");
        String apellido = scanner.nextLine();
        System.out.println("Ingresa tu número de documento:");
        String documento = scanner.nextLine();
        System.out.println("Ingresa una contraseña:");
        String contraseña = scanner.nextLine();
        int tipoCuenta;
        while (true) {
            System.out.println("Ingresa el tipo de cuenta (1: estudiante, 2: maestro, 3: padre):");
            try {
                tipoCuenta = Integer.parseInt(scanner.nextLine());
                if (tipoCuenta >= 1 && tipoCuenta <= 3)
                    break;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Tipo de cuenta no válido.");
        }

        String codigoAula = "";
        if (tipoCuenta == 2) {
            System.out.println("Ingresa un código de aula:");
            codigoAula = scanner.nextLine();
        } else {
            while (true) {
                System.out.println("Ingresa el código de aula:");
                codigoAula = scanner.nextLine();
                if (gestionDatos.obtenerUsuarioPorCodigoAula(codigoAula) != null)
                    break;
                System.out.println("Código de aula no válido.");
            }
        }

        GestionDatos.Usuario nuevoUsuario = new GestionDatos.Usuario(nombre, apellido, documento, contraseña,
                tipoCuenta, codigoAula);
        if (gestionDatos.agregarUsuario(nuevoUsuario)) {
            System.out.println("Cuenta creada exitosamente.");
        } else {
            System.out.println("Hubo un error al crear la cuenta.");
        }
    }
}
