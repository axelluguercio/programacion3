package programacion3.tpe;

import programacion3.tp3.Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ServicioBFSLevel {

    private Grafo<?> grafo;

    public ServicioBFSLevel(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<List<Integer>> bfsForest() {
        List<List<Integer>> niveles = new ArrayList<>(); // Lista de listas para almacenar los nodos por niveles
        List<Integer> visitados = new ArrayList<>();
        Iterator<Integer> iter = this.grafo.obtenerVertices();

        while (iter.hasNext()) {
            Integer vertice = iter.next();
            if (!visitados.contains(vertice)) {
                List<Integer> nivelActual = new ArrayList<>(); // Lista para almacenar los nodos del nivel actual
                bfs(vertice, visitados, nivelActual); // Pasar la lista del nivel actual al método BFS
                niveles.add(nivelActual); // Agregar la lista del nivel actual a la lista de niveles
            }
        }
        return niveles;
    }

    private void bfs(int v, List<Integer> visitados, List<Integer> nivelActual) {
        List<Integer> nodosEnCola = new LinkedList<>();
        nodosEnCola.add(v);
        visitados.add(v);

        while (!nodosEnCola.isEmpty()) {
            int nodoActual = nodosEnCola.remove(0);
            nivelActual.add(nodoActual); // Agregar el nodo actual al nivel actual

            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(nodoActual);

            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                if (!visitados.contains(adyacente)) {
                    visitados.add(adyacente);
                    nodosEnCola.add(adyacente);
                }
            }
        }
    }
}
