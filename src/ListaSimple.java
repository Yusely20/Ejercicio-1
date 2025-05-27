import javax.swing.*;

public class ListaSimple {

    private Nodo cabeza;

    public void agregarNodo(DefensorEternia defensor) {
        Nodo nuevoNodo = new Nodo(defensor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }

        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.getId() == defensor.getId()) {
                JOptionPane.showMessageDialog(null, "Este Id ya está ocupado, revisa los disponibles en la tabla de abajo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            actual = actual.siguiente;
        }
        actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevoNodo;
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}
