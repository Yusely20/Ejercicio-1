import java.util.ArrayList;
public class Burbuja {
    public ArrayList<Nodo> bubbleSort(ListaSimple listaSimple, String region) {
        Nodo actual = listaSimple.getCabeza();
        ArrayList<Nodo> list = new ArrayList<>();
        while (actual != null) {
            if (!actual.dato.getRegion().equals(region)){
                list.add(actual);
            }
            actual = actual.siguiente;
        }

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).dato.getNivelPoder() > list.get(j + 1).dato.getNivelPoder()) {
                    Nodo temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }
}
