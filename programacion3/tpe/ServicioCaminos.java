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
        // declara camino solucion
        List<List<Integer>> caminos = new ArrayList<>();
        List<List<Integer>> cola = new LinkedList<>();

        List<Integer> caminoInicial = new ArrayList<>();
        caminoInicial.add(this.origen);
        cola.add(caminoInicial);

        while (!cola.isEmpty()) {
            List<Integer> caminoActual = cola.remove(0);
            int ultimoNodo = caminoActual.get(caminoActual.size() - 1);

            if (ultimoNodo == this.destino && caminoActual.size() - 1 <= this.lim) {
                // si el ultimo nodo es igual a destino y el camino no supera el limite lo agrega como solucion
                caminos.add(caminoActual);
            }



            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(ultimoNodo); // obtiene adyacentes
            while (adyacentes.hasNext()) {
                int nodoAdyacente = adyacentes.next();
                List<Integer> nuevoCamino = new ArrayList<>(caminoActual);
                nuevoCamino.add(nodoAdyacente);
                cola.add(nuevoCamino);
            }
        }

        return caminos;
    }


}