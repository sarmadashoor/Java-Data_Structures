/*  ListPriorityQueue.java
 
 
 
 */
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListPriorityQueue<E> implements PriorityQueue<E> {

    private Node<E> head, tail;
    private int currentSize;
    private int maxSize;
    private long entryNumber;

    public ListPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public ListPriorityQueue(int size) {
        currentSize = 0;
        entryNumber = 0;
        maxSize = size;
    }

    public boolean insert(E object) {
        if (isFull())
            return false;
        Node<E> current = head;
        Node<E> previous = null;
        Node<E> newNode = new Node<E>(object);
        while (current != null) {
            if (((Comparable<E>) object).compareTo(current.data) < 0) {
                if (previous == null) {
                    newNode.next = head;
                    head = newNode;
                } else {
                    newNode.next = previous.next;
                    previous.next = newNode;
                }
                currentSize++;
                entryNumber++;
                return true;
            }

            previous = current;
            current = current.next;
        }
        if (previous == null) 
            head = newNode;
        else 
            tail.next = newNode;
        
        tail = newNode;
        currentSize++;
        entryNumber++;
        return true;
    }

    public E remove() {
        if (head == null) 
            return null;
        currentSize--;
        entryNumber++;
        E removed = head.data;
        if (head == tail) 
            head = tail = null;
        else 
            head = head.next;
        
        return removed;
    }

    public E peek() {
        return head.data;
    }

    public int size() {
        return currentSize;
    }

    public boolean contains(E object) {
        if (isEmpty())
            return false;
        Node<E> current = head;
        while (current != null) {
            if (((Comparable<E>) object).compareTo(current.data) == 0) 
                return true;
            current = current.next;
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    protected class IteratorHelper implements Iterator<E> {
        Node<E> current = head;
        private long startSize = entryNumber;
        
        public boolean hasNext() {
            if (startSize != entryNumber) 
                throw new ConcurrentModificationException("Modification of data detected during iteration.");
            return current != null;
        }
        
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Node<E> previous = current;
            current = current.next;
            return previous.data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public void clear() {
        head = tail = null;
        currentSize = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        return currentSize >= maxSize;
    }

    protected class Node<T> {
        T data;
        Node<T> next;
        public Node(T obj) {
            data = obj;
            next = null;
        }
    }
}
