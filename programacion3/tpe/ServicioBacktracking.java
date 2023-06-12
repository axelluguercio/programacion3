package programacion3.tpe;
import programacion3.tp3.GrafoDirigido;
import programacion3.tp3.Arco;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioBacktracking {
    private GrafoDirigido<Integer> grafo;
    private int minTotalDistance;
    private List<Integer> bestSolution;
    private long executionTime; // Variable para almacenar el tiempo de inicio

    public ServicioBacktracking(GrafoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.minTotalDistance = Integer.MAX_VALUE;
        this.bestSolution = new ArrayList<>();
        this.executionTime = 0;
    }

    public void findBestSolution() {
        long startTime = System.nanoTime(); // Guardar el tiempo de inicio
        List<Integer> currentSolution = new ArrayList<>();

        for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();) {
            Integer station = it.next();
            currentSolution.add(station);
            int currentDistance = 0;
            backtracking(station, currentSolution, currentDistance);
            currentSolution.remove(station);
        }

        long endTime = System.nanoTime(); // Guardar el tiempo de finalización
        this.executionTime = endTime - startTime; // Calcular el tiempo de ejecución en nanosegundos
    }

    private void backtracking(int currentStation, List<Integer> currentSolution, int currentDistance) {
        if (allStationsVisited(currentSolution)) {
            if (currentDistance < minTotalDistance) {
                minTotalDistance = currentDistance;
                bestSolution = new ArrayList<>(currentSolution);
            }
            return;
        }

        Iterator<Integer> iterador = grafo.obtenerAdyacentes(currentStation);
        while (iterador.hasNext()) {
            int nextStation = iterador.next();
            if (!currentSolution.contains(nextStation)) {
                currentSolution.add(nextStation);

                int distance = getDistanceBetweenStations(currentStation, nextStation);
                backtracking(nextStation, currentSolution, currentDistance + distance);

                currentSolution.remove(currentSolution.size() - 1);
            }
        }
    }

    private boolean allStationsVisited(List<Integer> solution) {
        int totalStations = grafo.cantidadVertices();
        return solution.size() == totalStations;
    }

    private int getDistanceBetweenStations(int station1, int station2) {
        Arco<Integer> arco = grafo.obtenerArco(station1, station2);
        if (arco != null) {
            return arco.getEtiqueta();
        }
        return Integer.MAX_VALUE; // Asignar un valor alto si las estaciones no están conectadas
    }
    public String formatResult() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Backtracking\n");
        int totalKilometers = 0;

        for (int i = 0; i < bestSolution.size() - 1; i++) {
            int estacion1 = bestSolution.get(i);
            int estacion2 = bestSolution.get(i + 1);
            int distance = getDistanceBetweenStations(estacion1, estacion2);
            resultado.append("E").append(estacion1).append("-E").append(estacion2);

            // Agregar coma si no es el último arco
            if (i < bestSolution.size() - 2) {
                resultado.append(",");
            }

            totalKilometers += distance;
        }
        resultado.append("\n");
        resultado.append(totalKilometers).append(" kms").append("\n");
            resultado.append(executionTime).append(" tiempo en ns").append("\n");

        return resultado.toString();
    }
}
