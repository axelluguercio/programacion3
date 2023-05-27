package programacion3.tp3;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    private Map<Integer, List<Arco<T>>> adyacencia;

    public GrafoDirigido() {
        this.adyacencia = new HashMap<>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (!this.adyacencia.containsKey(verticeId)) { adyacencia.put(verticeId, new ArrayList<Arco<T>>()); }
    }

    /**
     * Complejidad: O(N) donde N es la cantidad de arcos que contiene el vertice como destino, a borrar
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
     * Complejidad: O(1), linear ya que no itera sobre la lista de arcos, si no que en ausencia del vertice devuelve una array vacio
     * "realizar lo siguiente" para agregar un arco.
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        // Obtener la lista de adyacencia del vértice de origen
        List<Arco<T>> adyacenciasOrigen = this.adyacencia.computeIfAbsent(verticeId1, k -> new ArrayList<>());
        // Agregar el arco a la lista de adyacencia del vértice de origen
        adyacenciasOrigen.add(new Arco<T>(verticeId1, verticeId2, etiqueta));
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
                    return;
                }
            }
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.adyacencia.containsKey(verticeId);
    }

    /**
     * Complejidad: O(N), itera sobre la lista de arcos
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
     * Complejidad: O(N), itera sobre la lista de arcos
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

    @Override
    public int cantidadVertices() {
        return this.adyacencia.size();
    }

    /**
     * Complejidad: O(N**2), cuadratica, ya que itera 2 veces, una vez sobre las claves del map, y otra por cada arco de lista retornante
     * "realizar lo siguiente" para retornar la cantidad exactas de arcos en el grafo.
     */
    @Override
    public int cantidadArcos() {
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
    }

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
