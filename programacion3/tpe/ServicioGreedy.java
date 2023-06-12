package programacion3.tpe;

import programacion3.tp3.GrafoDirigido;
import programacion3.tp3.Arco;

import java.util.*;

public class ServicioGreedy {
    private GrafoDirigido<Integer> grafo;
    private List<Arco<Integer>> bestSolution;
    private int totalDistance;

    private long executionTime; // Variable para almacenar el tiempo de inicio

    public ServicioGreedy(GrafoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.bestSolution = new ArrayList<>();
        this.totalDistance = 0;
        this.executionTime = 0;
    }

    public void findBestSolution() {
        long startTime = System.nanoTime(); // Guardar el tiempo de inicio
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Arco<Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Arco::getEtiqueta));

        Integer startStation = grafo.obtenerVertices().next();
        visited.add(startStation);

        while (visited.size() < grafo.cantidadVertices()) {
            Iterator<Integer> iterator = visited.iterator();
            while (iterator.hasNext()) {
                Integer station = iterator.next();
                Iterator<Integer> adjacentIterator = grafo.obtenerAdyacentes(station);
                while (adjacentIterator.hasNext()) {
                    Integer adjacentStation = adjacentIterator.next();
                    if (!visited.contains(adjacentStation)) {
                        Arco<Integer> arco = grafo.obtenerArco(station, adjacentStation);
                        priorityQueue.add(arco);
                    }
                }
            }

            Arco<Integer> minArco = priorityQueue.poll();
            Integer nextStation = minArco.getVerticeDestino();
            if (!visited.contains(nextStation)) {
                visited.add(nextStation);
                bestSolution.add(minArco);
                totalDistance += minArco.getEtiqueta();
            }
        }

        long endTime = System.nanoTime(); // Guardar el tiempo de finalización
        this.executionTime = endTime - startTime; // Calcular el tiempo de ejecución en nanosegundos
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
        resultado.append(executionTime).append(" tiempo en ns").append("\n");

        return resultado.toString();
    }

}
