public class Recursividad {
    private String[] poderes = {"Fuerza Titanes","Espada de Poder","Estrategia Bélica","Magia Ancestral","Tecnología Avanzada"};

    public StringBuilder contar(ListaSimple listaSimple){
        StringBuilder sb = new StringBuilder();

        for (String habilidad : poderes) {
            int conteo = recursividad(listaSimple.getCabeza(), habilidad);
            sb.append(habilidad)
                    .append(": ")
                    .append(conteo)
                    .append("\n");
        }
        return sb;
    }

    public int recursividad(Nodo actual, String habilidad) {
        if (actual == null) {
            return 0;
        } else if (actual.dato.getHabilidadEspecial().equals(habilidad)) {
            return 1 + recursividad(actual.siguiente, habilidad);
        } else {
            return recursividad(actual.siguiente, habilidad);
        }
    }
}
