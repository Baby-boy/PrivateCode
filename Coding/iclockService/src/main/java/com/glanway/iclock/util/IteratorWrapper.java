package com.glanway.iclock.util;

import java.util.Iterator;

/**
 * @author vacoor
 */
public abstract class IteratorWrapper<E> implements Iterator<E> {
    private final Iterator<E> iterator;

    public IteratorWrapper(Iterator<E> iterator) {
        if (iterator == null) {
            throw new IllegalArgumentException();
        }
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
