import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa la entidad Ticket en el sistema.
 * Contiene el atributo estático 'cantidad' para generar ID's autoincrementables.
 */
public class Ticket {

    // Atributo estático único para el autoincremento de ID's
    private static int cantidad = 0;

    // Atributos de la entidad
    private int id;
    private String descripcion;
    private String nombreCompleto;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;
    private int prioridad; // 1: Alta, 2: Media, 3: Baja

    // Constructor
    public Ticket(String descripcion, String nombreCompleto, int prioridad) {
        cantidad++; // Incrementa el contador estático global
        this.id = cantidad; // Asigna el ID consecutivo único
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = LocalDateTime.now(); // Captura fecha y hora actual
        this.fechaResolucion = null; // Por defecto inicia en null
        this.prioridad = prioridad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }
    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    // Método amigable
    private String formatearFecha(LocalDateTime fecha) {
        if (fecha == null) return "null";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fecha.format(formato);
    }

    @Override
    public String toString() {
        String textoPrioridad = (prioridad == 1) ? "Alta" : (prioridad == 2) ? "Media" : "Baja";
        return "----------------------------------------\n" +
                "ID Ticket: " + id + "\n" +
                "Usuario: " + nombreCompleto + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Prioridad: " + textoPrioridad + "\n" +
                "Fecha Creación: " + formatearFecha(fechaCreacion) + "\n" +
                "Fecha Resolución: " + formatearFecha(fechaResolucion) + "\n" +
                "----------------------------------------";
    }
}
