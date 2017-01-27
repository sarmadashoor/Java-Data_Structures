/*  BinaryHeapPriorityQueue.java

 */
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryHeapPriorityQueue<E> implements PriorityQueue<E> {

    private Wrapper<E>[] arr;
    private int currentSize;
    private long entryNumber;

    public BinaryHeapPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public BinaryHeapPriorityQueue(int size) {
        entryNumber = 0;
        currentSize = 0;
        arr = new Wrapper[size];
    }

    public boolean insert(E object) {
        if (isFull())
            return false;
        
        Wrapper<E> newElement = new Wrapper(object);
        int spot = currentSize++;
        while(spot > 0 && arr[(spot-1)/2].compareTo(newElement) > 0)
        {
            int parentIndex = (spot-1)/2;
            arr[spot] = arr[parentIndex];
            spot = parentIndex;
        }
        arr[spot] = newElement;
        return true;
    }

    public E remove() {
        E tmp = arr[0].data;
        
        Wrapper<E> endElement = arr[--currentSize];
        int spot = 0;
        int leftIndex = 1;
        int rightIndex = 2;
        while(leftIndex < currentSize) {
            if(rightIndex < currentSize) {
                if(arr[leftIndex].compareTo(arr[rightIndex]) < 0) {
                    if(endElement.compareTo(arr[leftIndex]) > 0) {
                        arr[spot] = arr[leftIndex];
                        spot = leftIndex;
                    }
                    else
                        break;
                }
                else {
                    if(endElement.compareTo(arr[rightIndex]) > 0) {
                        arr[spot] = arr[rightIndex];
                        spot = rightIndex;
                    }
                    else
                        break;
                }
            }
            else {
                if(endElement.compareTo(arr[leftIndex]) > 0) {
                    arr[spot] = arr[leftIndex];
                    spot = leftIndex;
                }
                else
                    break;
            }
            leftIndex = spot * 2 + 1;
            rightIndex = leftIndex + 1;
        }

        arr[spot] = endElement;
        entryNumber++;
        return tmp;
    }

    public E peek() {
        return arr[0].data;
    }

    public int size() {
        return currentSize;
    }

    public boolean contains(E object) {
        for(Wrapper<E> w : arr)
            if(((Comparable<E>)object).compareTo(w.data) == 0)
                return true;
        return false;
    }

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    protected class IteratorHelper implements Iterator<E> {
        private int current = 0;
        private long startSize = entryNumber;

        public boolean hasNext() {
            if (startSize != entryNumber) 
                throw new ConcurrentModificationException("Modification of data detected during iteration.");
            return current < currentSize;
        }

        public E next() {
            if (!hasNext()) 
                throw new NoSuchElementException();
            return arr[current++].data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public void clear() {
        currentSize = 0;
        entryNumber = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize >= arr.length;
    }

    protected class Wrapper<T> implements Comparable<Wrapper<T>> {
        T data;
        long sequenceNumber;

        public Wrapper(T obj) {
            data = obj;
            sequenceNumber = entryNumber++;
        }

        public int compareTo(Wrapper<T> obj) {
            int tmp = ((Comparable<T>) data).compareTo(obj.data);
            if (tmp == 0) 
                return (int) (sequenceNumber - obj.sequenceNumber);
            return tmp;
        }

        public String toString() {
            return "" + data;
        }
    }
}
