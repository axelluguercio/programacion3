package programacion3.tpe;

import programacion3.tp3.GrafoDirigido;
import programacion3.tp3.Arco;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String path = "/Users/axel/IdeaProjects/programacion3/programacion3/src/programacion3/tpe/datasets/dataset2.txt";
        CSVReader reader = new CSVReader(path);
        reader.read();

        // Obtiene el grafo resultante
        GrafoDirigido<Integer> grafo = reader.getGrafo();

        // Crea el servicio para backtracking pasandole el grafo
        ServicioBacktracking servicioBacktracking = new ServicioBacktracking(grafo);
        servicioBacktracking.findBestSolution();
        System.out.println(servicioBacktracking.formatResult());

        // Crea el servicio greedy utilizando el algoritmo de Dijstra pasandole el grafo
        ServicioGreedy servicioGreedy = new ServicioGreedy(grafo);
        servicioGreedy.findBestSolution();
        System.out.println(servicioGreedy.formatResult());


    }

}