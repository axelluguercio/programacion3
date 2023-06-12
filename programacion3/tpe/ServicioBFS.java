package programacion3.tpe;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import programacion3.tp3.Grafo;

public class ServicioBFS {

    private Grafo<?> grafo;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> bfsForest() {
        List<Integer> visitados = new ArrayList<>();
        Iterator<Integer> iter = this.grafo.obtenerVertices();

        while (iter.hasNext()) {
            Integer vertice = iter.next();
            if (!visitados.contains(vertice)) {
                bfs(vertice, visitados);
            }
        }
        return visitados;
    }

    /**
     * Complejidad: O(N**2), donde N es la cantidad de vertices que tiene el grafo
     * "realizar lo siguiente" para realizar bfs.
     */
    private void bfs(int v, List<Integer> visitados) {
        List<Integer> nodosEnCola = new LinkedList<>();
        nodosEnCola.add(v);
        visitados.add(v);

        while (!nodosEnCola.isEmpty()) {
            int nodoActual = nodosEnCola.remove(0); // saca del principio
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(nodoActual);
            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                if (!visitados.contains(adyacente)) {
                    visitados.add(adyacente);
                    nodosEnCola.add(adyacente); // agrega al final
                }
            }
        }
    }

}