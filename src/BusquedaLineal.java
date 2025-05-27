public class BusquedaLineal {
    public DefensorEternia buscarLineal(ListaSimple listaSimple, int idBuscado) {
        Nodo actual = listaSimple.getCabeza();
        while (actual != null) {
            if (actual.dato.getId() == idBuscado) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}
