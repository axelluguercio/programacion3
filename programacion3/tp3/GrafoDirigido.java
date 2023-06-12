package programacion3.tp3;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    private Map<Integer, List<Arco<T>>> adyacencia;
    private int cantArcos; // Nuevo atributo para llevar el registro de la cantidad de arcos

    public GrafoDirigido() {
        this.adyacencia = new HashMap<>();
        this.cantArcos = 0; // inicia con 0 arcos
    }

    /**
     * Complejidad: O(1), La operación put en el mapa adyacencia tiene una complejidad de inserción de O(1) en promedio
     * Agrega un vertice dado como parametro
     */
    @Override
    public void agregarVertice(int verticeId) {
        if (!this.adyacencia.containsKey(verticeId)) { adyacencia.put(verticeId, new ArrayList<Arco<T>>()); }
    }

    /**
     * Complejidad: O(N) donde N se refiere a la cantidad de arcos que se encuentran en la lista de adyacencia del vértice de origen
     * "realizar lo siguiente" para borrar un vertice.
     */
    @Override
    public void borrarVertice(int verticeId) {
        if (this.adyacencia.containsKey(verticeId)) { adyacencia.remove(verticeId); }

        // borro los arcos salientes
        Iterator<Arco<T>> iterador = this.obtenerArcos();
        while (iterador.hasNext()) {
            Arco<T> arco = iterador.next();
            int origen = arco.getVerticeOrigen();
            if (arco.getVerticeDestino() == verticeId) { this.borrarArco(origen, verticeId); }
        }
    }

    /**
     *  la complejidad puede considerarse lineal O(N), donde N es el número de arcos existentes en el grafo.
     * "realizar lo siguiente" para agregar un arco.
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        // Obtener la lista de adyacencia del vértice de origen
        List<Arco<T>> adyacenciasOrigen = this.adyacencia.computeIfAbsent(verticeId1, k -> new ArrayList<>());
        // Agregar el arco a la lista de adyacencia del vértice de origen
        adyacenciasOrigen.add(new Arco<T>(verticeId1, verticeId2, etiqueta));

        this.cantArcos++; // Incrementar el contador de arcos
    }

    /**
     * Complejidad: O(N), itera sobre la lista de arcos, y elimina el verticeid2 como destino
     * "realizar lo siguiente" para borrar un arco.
     */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        // Buscamos el vértice origen en el mapa
        List<Arco<T>> arcosSalientes = this.adyacencia.get(verticeId1);
        if (arcosSalientes != null) {
            // Buscamos el arco que tenga como destino el vérticeId2
            Iterator<Arco<T>> iter = arcosSalientes.iterator();
            while (iter.hasNext()) {
                Arco<T> arco = iter.next();
                if (arco.getVerticeDestino() == verticeId2) {
                    // Eliminamos el arco de la lista de arcos salientes
                    iter.remove();
                    this.cantArcos--; // Decrementar el contador de arcos
                    return;
                }
            }
        }
    }

    /**
     * Complejidad: O(1), la operación containsKey en el mapa adyacencia tiene una complejidad de búsqueda de O(1) en promedio
     * Saber si existe un vertice en el grafo
     */
    @Override
    public boolean contieneVertice(int verticeId) {
        return this.adyacencia.containsKey(verticeId);
    }

    /**
     * Complejidad: O(N), donde N se refiere a la cantidad de arcos que se encuentran en la lista de adyacencia del vértice de origen
     * "realizar lo siguiente" para verificar si existe un arco entre dos vertices dados.
     */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        List<Arco<T>> arcosSalientes =  this.adyacencia.get(verticeId1);
        // si no esta vacio, iteramos buscando el vertice origen
        if (arcosSalientes != null) {
            // Buscamos el arco que tenga como destino el vérticeId2
            Iterator<Arco<T>> iter = arcosSalientes.iterator();
            while (iter.hasNext()) {
                Arco<T> arco = iter.next();
                if (arco.getVerticeDestino() == verticeId2)
                    return true;
            }
        }
        return false;
    }

    /**
     * Complejidad: O(N), donde N se refiere a la cantidad de arcos que se encuentran en la lista de adyacencia del vértice de origen
     * "realizar lo siguiente" para obtener un arco entre dos vertices.
     */
    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        List<Arco<T>> arcosSalientes =  this.adyacencia.get(verticeId1);
        // si no esta vacio, iteramos buscando el vertice origen
        if (arcosSalientes != null) {
            // Buscamos el arco que tenga como destino el vérticeId2
            Iterator<Arco<T>> iter = arcosSalientes.iterator();
            while (iter.hasNext()) {
                Arco<T> arco = iter.next();
                if (arco.getVerticeDestino() == verticeId2)
                    return arco;
            }
        }
        return new Arco<T>(verticeId1, verticeId2, null);
    }

    /**
     * Complejidad: O(1), La operación size en el mapa adyacencia tiene una complejidad de O(1) ya que mantiene un contador interno
     * Devuelve la cantidad de vertices en el arco
     */
    @Override
    public int cantidadVertices() {
        return this.adyacencia.size();
    }

    /**
     * Complejidad: O(1), al llevar un atributo es lineal
     * "realizar lo siguiente" para retornar la cantidad exactas de arcos en el grafo.
     */
    @Override
    public int cantidadArcos() {
        /* antes de la observacion
        int cantArcos = 0;
        //iterating over keys only
        for (Integer id : adyacencia.keySet()) {
            List<Arco<T>> arcosSalientes =  this.adyacencia.get(id);
            if (arcosSalientes != null) {
                // contamos la cantidad de arcos
                for (Arco<T> arco : arcosSalientes) {
                    cantArcos++;
                }
            }
        }
        return cantArcos;
         */
        return this.cantArcos; // Devolver la cantidad de arcos almacenada en el atributo
    }

    /**
     * Complejidad: O(N), donde N es el número de vértices en el grafo.
     * devuelve los vertices en un iterador
     */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return this.adyacencia.keySet().iterator();
    }

    /**
     * Complejidad: O(N), itera sobre la lista de arcos salientes
     * "realizar lo siguiente" para obtener adyacentes de un vertice dado.
     */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        List<Arco<T>> arcosSalientes =  this.adyacencia.get(verticeId);
        // si esta vacio, devolver un iterador vacio
        if (arcosSalientes == null) { return Collections.emptyIterator(); }
        else {
            // Creamos un iterador que recorra los arcos salientes y retorne el id del vértice destino de cada arco
            return new IteratorAdj<T>(arcosSalientes);
        }
    }

    /**
     * Complejidad: O(N*M), Si el grafo tiene N vértices y M arcos, y el método es llamado, el iterador debe recorrer todos los arcos para agregarlos a una lista y luego devolver un iterador para esa lista.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        List<Arco<T>> arcos = new ArrayList<>();
        for (List<Arco<T>> arcosSalientes : adyacencia.values()) {
            arcos.addAll(arcosSalientes);
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        List<Arco<T>> arcosSalientes =  this.adyacencia.get(verticeId);

        // si no esta vacio, devolve el iterador
        if (arcosSalientes == null) { return Collections.emptyIterator(); }

        // Creamos un iterador que recorra los arcos salientes
        else { return arcosSalientes.iterator(); }
    }

}
