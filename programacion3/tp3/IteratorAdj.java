package programacion3.tp3;

import java.util.*;

public class IteratorAdj<T> implements Iterator<Integer> {
    private final Iterator<Arco<T>> arcosIterator;

    public IteratorAdj(List<Arco<T>> arcosSalientes) {
        this.arcosIterator = arcosSalientes.iterator();
    }

    @Override
    public boolean hasNext() {
        return arcosIterator.hasNext();
    }

    @Override
    public Integer next() {
        return this.arcosIterator.next().getVerticeDestino();
    }
}
