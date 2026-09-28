import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    private static ColaPrioridad colaPendientes = new ColaPrioridad();
    private static ListaEnlazadaSimple listaResueltos = new ListaEnlazadaSimple();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("     SISTEMA DE GESTIÓN DE TICKETS      ");
            System.out.println("========================================");
            System.out.println("1. Menú de Usuario");
            System.out.println("2. Menú de Administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        menuUsuario();
                        break;
                    case 2:
                        menuAdministrador();
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error: Por favor ingrese un número entero.");
            }
        } while (opcion != 3);
    }

    private static void menuUsuario() {
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ DE USUARIO ---");
            System.out.println("1. Crear un Ticket");
            System.out.println("2. Buscar Ticket Resuelto por ID");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        crearTicket();
                        break;
                    case 2:
                        buscarTicket();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada de datos.");
            }
        } while (opcion != 3);
    }

    private static void menuAdministrador() {
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ DE ADMINISTRADOR ---");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        verTicketFrente();
                        break;
                    case 2:
                        resolverTicketFrente();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada de datos.");
            }
        } while (opcion != 3);
    }

    private static void crearTicket() {
        System.out.print("Ingrese su nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la descripción del problema: ");
        String descripcion = scanner.nextLine();

        System.out.println("Seleccione el nivel de prioridad:");
        System.out.println("1. Alta");
        System.out.println("2. Media");
        System.out.println("3. Baja");
        System.out.print("Opción (1-3): ");
        int prioridad = Integer.parseInt(scanner.nextLine());

        Ticket nuevoTicket = new Ticket(descripcion, nombre, prioridad);
        colaPendientes.insertar(nuevoTicket);

        System.out.println("\n¡Ticket creado exitosamente!");
        System.out.println("Su ID de Ticket asignado es: " + nuevoTicket.getId());
    }

    private static void buscarTicket() {
        System.out.print("Ingrese el ID del ticket a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Ticket resuelto = listaResueltos.buscarPorId(id);

        if (resuelto != null) {
            System.out.println("\nTicket resuelto encontrado:");
            System.out.println(resuelto);
        } else {
            System.out.println("\nEl ticket con ID " + id + " se encuentra pendiente de solución.");
        }
    }

    private static void verTicketFrente() {
        Ticket frente = colaPendientes.verFrente();
        if (frente != null) {
            System.out.println("\nTicket al frente de la cola de prioridad:");
            System.out.println(frente);
        } else {
            System.out.println("\nNo hay tickets pendientes en la cola.");
        }
    }

    private static void resolverTicketFrente() {
        if (colaPendientes.estaVacia()) {
            System.out.println("\nNo hay tickets pendientes para resolver.");
            return;
        }

        Ticket ticketAResolver = colaPendientes.desencolar();
        ticketAResolver.setFechaResolucion(LocalDateTime.now());
        listaResueltos.insertarFin(ticketAResolver);

        System.out.println("\n¡El ticket ha sido resuelto exitosamente y transferido a la lista de resueltos!");
        System.out.println(ticketAResolver);
    }
}
