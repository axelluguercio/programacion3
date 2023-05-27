package programacion3.tpe;

import java.util.*;

import programacion3.tp3.Grafo;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;

    // Servicio caminos
    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
    }

    public List<List<Integer>> caminos() {
        List<List<Integer>> caminos = new ArrayList<>();
        List<List<Integer>> cola = new LinkedList<>();
        List<Integer> caminoInicial = new ArrayList<>();

        caminoInicial.add(this.origen);
        cola.add(caminoInicial);

        // mientras la pila no este vacia
        while (!cola.isEmpty()) {
            List<Integer> caminoActual = cola.remove(0);
            int ultimoNodo = caminoActual.get(caminoActual.size() - 1);

            if (ultimoNodo == this.destino) {
                caminos.add(caminoActual);
                continue;
            }

            if (caminoActual.size() >= this.lim) { // si cumple el limite
                continue;
            }

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(ultimoNodo); // obtiene adyacentes
            while (adyacentes.hasNext()) { // itera
                int nodoAdyacente = adyacentes.next();
                if (!caminoActual.contains(nodoAdyacente)) { // si el camino no fue explorado lo agrega como nuevo camino
                    List<Integer> nuevoCamino = new ArrayList<>(caminoActual);
                    nuevoCamino.add(nodoAdyacente);
                    cola.add(nuevoCamino);
                }
            }
        }
        return caminos;
    }
}