package programacion3.tp3;

import programacion3.tpe.*;

public class app {

    public static void main (String[] args) {
        GrafoDirigido grafo = new GrafoDirigido<>();

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);
        grafo.agregarVertice(8);


        grafo.agregarArco(1, 2, null);
        grafo.agregarArco(2, 3, null);
        grafo.agregarArco(3, 4,null);
        grafo.agregarArco(4, 5,null);
        grafo.agregarArco(1, 6,null);
        grafo.agregarArco(6, 3,null);
        grafo.agregarArco(6, 7,null);
        grafo.agregarArco(4, 8,null);

        System.out.println("Cantidad de vertices " + grafo.cantidadVertices());
        System.out.println("Cantidad de arcos " + grafo.cantidadArcos());

        // DFS
        ServicioDFS dfs = new ServicioDFS(grafo);

        // BFS
        ServicioBFS bfs = new ServicioBFS(grafo);

        // Servicio Camino (no se puede pasar 2 veces por el mismo arco)
        ServicioCaminos camino = new ServicioCaminos(grafo, 1, 4, 4);

        System.out.println(dfs.dfsForest());
        System.out.println(bfs.bfsForest());
        System.out.println(camino.caminos());

    }
}
