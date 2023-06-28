package programacion3.tpe;

import programacion3.tp3.GrafoDirigido;
import programacion3.tp3.Arco;

import java.util.*;

public class ServicioGreedy {
    private GrafoDirigido<Integer> grafo;
    private List<Arco<Integer>> bestSolution;
    private int totalDistance;

    private long metric; // Variable para almacenar el tiempo de inicio

    public ServicioGreedy(GrafoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.bestSolution = new ArrayList<>();
        this.totalDistance = 0;
        this.metric = 0;
    }

    public void findBestSolution() {
        Set<Integer> visited = new HashSet<>();
        // arma una lista de arcos ordenados por su etiqueta usando la interfaz Comparator de Integer (metodo para el criterio Greedy)
        PriorityQueue<Arco<Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Arco::getEtiqueta));

        Integer startStation = grafo.obtenerVertices().next();
        visited.add(startStation);

        while (visited.size() < grafo.cantidadVertices()) {
            // incrementa el contador de ciclos
            this.metric++;
            for (Integer station : visited) {
                Iterator<Integer> adjacentIterator = grafo.obtenerAdyacentes(station);
                while (adjacentIterator.hasNext()) {
                    // incrementa el contador de ciclos
                    this.metric++;
                    Integer adjacentStation = adjacentIterator.next();
                    if (!visited.contains(adjacentStation)) {
                        Arco<Integer> arco = grafo.obtenerArco(station, adjacentStation);
                        priorityQueue.add(arco);
                    }
                }
            }

            Arco<Integer> minArco = priorityQueue.poll();

            if (minArco != null) {
                Integer nextStation = minArco.getVerticeDestino();

                if (!visited.contains(nextStation)) {
                    visited.add(nextStation);
                    bestSolution.add(minArco);
                    totalDistance += minArco.getEtiqueta();
                }
            }
        }
    }

    public String formatResult() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Greedy\n");
        for (int i = 0; i < bestSolution.size(); i++) {
            Arco<Integer> arco = bestSolution.get(i);
            int origen = arco.getVerticeOrigen();
            int destino = arco.getVerticeDestino();
            resultado.append("E" + origen + "-E" + destino);

            // Agregar coma si no es el último arco
            if (i < bestSolution.size() - 1) {
                resultado.append(",");
            }
        }
        resultado.append("\n");
        resultado.append(totalDistance).append(" kms\n");
        resultado.append(metric).append(" cantidad de loops").append("\n");

        return resultado.toString();
    }

}
