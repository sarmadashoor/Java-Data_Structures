package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedList<E> implements Iterable {

    private Node<E> head, tail;
    private int currentSize, modCounter;

    public OrderedList() {
        head = tail = null;
        currentSize = modCounter = 0;
    }

    public boolean add(E object) {
        Node<E> newNode = new Node(object);
        
        if (isEmpty()) {
            head = tail = newNode;
            currentSize++;
            modCounter++;
            return true;
        }
        currentSize++;
        modCounter++;
        Node<E> current = head;
        Node<E> previous = null;
        if (((Comparable<E>) object).compareTo(current.data) < 0) {
            head = newNode;
            newNode.next = current;
            return true;
        }
        while (current != null) {
            if (((Comparable<E>) object).compareTo(current.data) < 0) {
                previous.next = newNode;
                newNode.next = current;
                return true;
            }
            previous = current;
            current = current.next;
        }
        tail.next = newNode;
        tail = newNode;
        return true;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        currentSize--;
        modCounter++;
        return data;
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E data = tail.data;
        Node<E> current = head;
        Node<E> previous = null;
        if (current.next == null) {
            head = null;
        }
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        tail = previous;
        currentSize--;
        modCounter++;
        return data;
    }

    public E remove(E object) {
        if (isEmpty()) {
            return null;
        }
        Node<E> current = head;
        Node<E> previous = null;
        if (((Comparable<E>) object).compareTo(current.data) == 0) {
            return removeFirst();
        }
        currentSize--;
        modCounter++;
        previous = current;
        current = current.next;
        while (current.next != null) {
            if (((Comparable<E>) object).compareTo(current.data) == 0) {
                previous.next = current.next;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        if (((Comparable<E>) object).compareTo(current.data) == 0) {
            return removeLast();
        }
        currentSize++;
        modCounter--;
        return null;
    }

    public E getFirst() {
        if(isEmpty())
            return null;
        return head.data;
    }

    public E getLast() {
        if(isEmpty())
            return null;
        return tail.data;
    }

    public boolean contains(E object) {
        return get(object) != null;
    }

    public void clear() {
        head = tail = null;
        currentSize = modCounter = 0;
    }

    public E get(E object) {
        Node<E> current = head;
        while (current != null) {
            if (((Comparable<E>) object).compareTo(current.data) == 0) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        return currentSize;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E> {

        Node<E> currentNode;
        Node<E> previousNode;
        int concurrentMod;

        public IteratorHelper() {
            currentNode = head;
            previousNode = null;
            concurrentMod = modCounter;
        }

        public boolean hasNext() {
            if (concurrentMod != modCounter) {
                throw new ConcurrentModificationException();
            }
            return currentNode != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
            return previousNode.data;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private class Node<T> {

        Node<T> next;
        T data;

        public Node(T data) {
            next = null;
            this.data = data;
        }
    }
}
