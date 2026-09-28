/**
 * Implementación de una Cola de Prioridad para tickets pendientes.
 * Los tickets se ordenan de acuerdo a su nivel de prioridad (1 es la mayor).
 */
public class ColaPrioridad {

    private NodoCola frente;

    public ColaPrioridad() {
        this.frente = null;
    }
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Inserción ordenada por prioridad.
     * Cuanto menor sea el número de prioridad, más al frente quedará el ticket.
     */
    public void insertar(Ticket ticket) {
        NodoCola nuevo = new NodoCola(ticket);
        // Caso 1: La cola está vacía o el nuevo ticket tiene mayor prioridad que el frente actual
        if (estaVacia() || ticket.getPrioridad() < frente.getTicket().getPrioridad()) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
            return;
        }
        // Caso 2: Recorrer para encontrar la posición correcta en la cola
        NodoCola actual = frente;
        while (actual.getSiguiente() != null &&
                actual.getSiguiente().getTicket().getPrioridad() <= ticket.getPrioridad()) {
            actual = actual.getSiguiente();
        }
        // Enganchar el nuevo nodo entre los nodos correspondientes
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
    }

    public Ticket verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getTicket();
    }
    public Ticket desencolar() {
        if (estaVacia()) {
            return null;
        }
        Ticket ticketAtendido = frente.getTicket();
        frente = frente.getSiguiente();
        return ticketAtendido;
    }

    // Nodo interno para la cola
    private class NodoCola {
        private Ticket ticket;
        private NodoCola siguiente;

        public NodoCola(Ticket ticket) {
            this.ticket = ticket;
            this.siguiente = null;
        }

        public Ticket getTicket() {
            return ticket;
        }
        public NodoCola getSiguiente() {
            return siguiente;
        }
        public void setSiguiente(NodoCola siguiente) {
            this.siguiente = siguiente;
        }
    }
}
