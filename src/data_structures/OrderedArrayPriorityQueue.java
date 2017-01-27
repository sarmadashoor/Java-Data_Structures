package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<E> implements PriorityQueue<E> {
    private E[] storage;
    private int maxSize, currentSize, modCounter;
    
    public OrderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }
    
    public OrderedArrayPriorityQueue(int maxSize) {
        storage = (E[]) new Object[maxSize];
        this.maxSize = maxSize;
        currentSize = 0;
        modCounter = 0;
    }
    
    public boolean insert(E object) {
        if(isFull())
            return false;
        int insertionPoint = binSearch(object, 0, currentSize-1, true);
        for(int i = currentSize; i > insertionPoint; i--)
            storage[i] = storage[i-1];
        storage[insertionPoint] = object;
        currentSize++;
        modCounter++;
        return true;
    }

    public E remove() {
        if(isEmpty())
            return null;
        modCounter++;
        return storage[--currentSize];
    }

    public E peek() {
        if(isEmpty())
            return null;
        return storage[currentSize-1];
    }

    public int size() {
        return currentSize;
    }

    public boolean contains(E object) {
        if(binSearch(object, 0, currentSize-1, false) == -1)
            return false;
        return true;
    }

    public void clear() {
        currentSize = modCounter = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }
    
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }
    
    private class IteratorHelper implements Iterator<E>{
        int iterIndex;
        int concurrentMod;
        public IteratorHelper() {
            iterIndex = 0;
            concurrentMod = modCounter;
        }

        public boolean hasNext() {
            if(concurrentMod != modCounter)
                throw new ConcurrentModificationException();
            return iterIndex < currentSize;
        }

        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return storage[iterIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    private int binSearch(E object, int lo, int hi, boolean insert) {
        if(hi < lo)
            return (insert ? lo : -1);
        int mid = (lo+hi)/2;
        if(!insert && ((Comparable<E>)object).compareTo(storage[mid]) == 0)
            return mid;
        if(((Comparable<E>)object).compareTo(storage[mid]) > 0) 
            return binSearch(object, lo, mid-1, insert);
        return binSearch(object, mid+1, hi, insert);
    }
}
