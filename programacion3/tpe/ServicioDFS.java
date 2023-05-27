package programacion3.tpe;

import java.util.*;

import programacion3.tp3.Grafo;

public class ServicioDFS {

    private Grafo<?> grafo;
    private Map<Integer, String> colores;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
    }

    private Iterator<Integer> pintarVertices() {
        Iterator<Integer> iterator = this.grafo.obtenerVertices();
        while (iterator.hasNext()) {
            int vertice = iterator.next();
            this.colores.put(vertice, "BLANCO");
        }
        return this.colores.keySet().iterator();
    }

    public List<Integer> dfsForest() {
        // lista de visitados
        List<Integer> visitados = new ArrayList<>();
        Iterator<Integer> iterator = pintarVertices();
        while (iterator.hasNext()) {
            Integer vertice = iterator.next();
            if (this.colores.get(vertice).equals("BLANCO")) {
                visitados.addAll(dfs(vertice));
            }
        }
        return visitados;
    }

    /**
     * Complejidad: O(N + |V|), donde N es la cantidad de vertices y V es la cantidad de arcoos
     * "realizar lo siguiente" para realizar DFS.
     */
    // método auxiliar para hacer DFS recursivo
    private List<Integer> dfs(int vertice) {
        List<Integer> visitados = new ArrayList<>();
        this.colores.put(vertice, "AMARILLO");
        visitados.add(vertice);
        // vértices adyacentes al actual
        Iterator<Integer> iterator = this.grafo.obtenerAdyacentes(vertice);
        while (iterator.hasNext()) {
            int adj = iterator.next();
            if (this.colores.get(adj).equals("BLANCO")) { // si no ha sido visitado aún
                visitados.addAll(dfs(adj)); // DFS desde ese vértice adyacente
            } else if (this.colores.get(adj).equals("AMARILLO")) { // arco back (grafo dirigido ciclico)
                System.out.println("hay ciclo");
            }
        }
        return visitados;
    }
}