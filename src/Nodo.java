public class Nodo {
    public Nodo siguiente;
    public DefensorEternia dato;

    Nodo(DefensorEternia dato){
        this.dato = dato;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}
