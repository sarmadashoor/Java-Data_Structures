package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedArrayPriorityQueue <E> implements PriorityQueue<E> {
    private E[] storage;
    private int maxSize, currentSize, modCounter;
    
    public UnorderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }
    
    public UnorderedArrayPriorityQueue(int maxSize) {
        storage = (E[]) new Object[maxSize];
        this.maxSize = maxSize;
        currentSize = 0;
        modCounter = 0;
    }
    
    public boolean insert(E object) {
        if(isFull())
            return false;
        storage[currentSize++] = object;
        modCounter++;
        return true;
    }

    public E remove() {
        if(isEmpty())
            return null;
        modCounter++;
        ObjectIndexPair<E> removeObjectIndexPair = getHighestPriorityObject();
        int removeIndex = removeObjectIndexPair.getIndex();
        E removeObject = removeObjectIndexPair.getObject();
        for(int i = removeIndex; i < currentSize-1; i++)
            storage[i] = storage[i+1];
        currentSize--;
        return removeObject;
    }

    public E peek() {
        if(isEmpty())
            return null;
        return storage[getHighestPriorityObject().getIndex()];
    }

    public int size() {
        return currentSize;
    }

    public boolean contains(E object) {
        for(int i = 0; i < currentSize; i++)
            if(((Comparable<E>)object).compareTo(storage[i]) == 0)
                return true;
        return false;
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
    
    private ObjectIndexPair<E> getHighestPriorityObject() {
        int highIndex = 0;
        E highObject = storage[0];
        for(int i = 1; i < currentSize; i++) {
            if(((Comparable<E>)highObject).compareTo(storage[i]) > 0) {
                highObject = storage[i];
                highIndex = i;
            }
        }
        return new ObjectIndexPair(highIndex, highObject);
    }
    
    private class ObjectIndexPair<E> {
        private int index;
        private E object;
        
        public ObjectIndexPair(int i, E o) {
            index = i;
            object = o;
        }
        
        public int getIndex() {
            return index;
        }
        
        public E getObject() {
            return object;
        }
    }
}
