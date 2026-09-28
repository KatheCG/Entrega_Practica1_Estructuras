/**
 * Lista enlazada simple que guarda los tickets que ya fueron resueltos.
 */

public class ListaEnlazadaSimple {

    private NodoLista primero;

    public ListaEnlazadaSimple() {
        this.primero = null;
    }
    public boolean estaVacia() {
        return primero == null;
    }
    public void insertarFin(Ticket ticket) {
        NodoLista nuevo = new NodoLista(ticket);

        if (estaVacia()) {
            primero = nuevo;
            return;
        }
        NodoLista temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nuevo);
    }

    public Ticket buscarPorId(int idBuscado) {
        if (estaVacia()) {
            return null;
        }
        NodoLista temp = primero;
        while (temp != null) {
            if (temp.getTicket().getId() == idBuscado) {
                return temp.getTicket();
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    // Nodo interno para la lista enlazada
    private class NodoLista {
        private Ticket ticket;
        private NodoLista siguiente;

        public NodoLista(Ticket ticket) {
            this.ticket = ticket;
            this.siguiente = null;
        }

        public Ticket getTicket() {
            return ticket;
        }
        public NodoLista getSiguiente() {
            return siguiente;
        }
        public void setSiguiente(NodoLista siguiente) {
            this.siguiente = siguiente;
        }
    }
}
