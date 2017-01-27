package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedList<E> implements Iterable{
    private Node<E> head, tail;
    private int currentSize, modCounter;
    
    public UnorderedList() {
        head = tail = null;
        currentSize = modCounter = 0;
    }
    
    public void addFirst(E object) {
        Node<E> n = new Node(object);
        if(isEmpty()) {
            head = tail = n;
        } else {
            n.next = head;
            head = n;
        }
        currentSize++;
        modCounter++;
    }
    
    public void addLast(E object) {
        Node<E> n = new Node(object);
        if(isEmpty()) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        currentSize++;
        modCounter++;
    }
    
    public E removeFirst() {
        if(isEmpty())
            return null;
        E data = head.data;
        head = head.next;
        if(head == null)
            tail = null;
        currentSize--;
        modCounter++;
        return data;
    }
    
    public E removeLast() {
        if(isEmpty())
            return null;
        E data = tail.data;
        Node<E> current = head;
        Node<E> previous = null;
        if(current.next == null) 
            return removeFirst();
        while(current.next != null) {
            previous = current;
            current = current.next;
        }
        tail = previous;
        tail.next = null;
        currentSize--;
        modCounter++;
        return data;
    }
    
    public E remove(E object) {
        if(isEmpty())
            return null;
        Node<E> current = head;
        Node<E> previous = null;
        if(((Comparable<E>)object).compareTo(current.data) == 0)
            return removeFirst();
        currentSize--;
        modCounter++;
        previous = current;
        current = current.next;
        while(current.next != null) {
            if(((Comparable<E>)object).compareTo(current.data) == 0) {
                previous.next = current.next;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        if(((Comparable<E>)object).compareTo(current.data) == 0)
            return removeLast();
        currentSize++;
        modCounter--;
        return null;
    }
    
    public E removePriority() {
        if(isEmpty())
            return null;
        
        Node<E> current = head.next;
        Node<E> previous = head;
        Node<E> highest = head;
        Node<E> prevHighest = null;
        while(current != null) {
            if(((Comparable<E>)highest.data).compareTo(current.data) >= 0) {
                prevHighest = previous;
                highest = current;
            }
            previous = current;
            current = current.next;
        }
        if(highest == head)
            return removeFirst();
        if(highest == tail)
            return removeLast();
        prevHighest.next = highest.next;
        currentSize--;
        modCounter++;
        return highest.data;
    }
    
    public E get(E object){
        Node<E> current = head;
        while(current != null) {
            if(((Comparable<E>)object).compareTo(current.data) == 0)
                return current.data;
            current = current.next;
        }
        return null;
    }
    
    public E getPriority() {
        if(isEmpty())
            return null;
        Node<E> current = head;
        Node<E> highest = head;
        while(current != null) {
            if(((Comparable<E>)highest.data).compareTo(current.data) >= 0)
                highest = current;
            current = current.next;
        }
        return highest.data;
    }
    
    public boolean contains(E object){
        return get(object) != null;
    }
    
    public int size() {
        return currentSize;
    }
    
    public boolean isFull() {
        return false;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void clear() {
        head = tail = null;
        modCounter = currentSize = 0;
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
            if(concurrentMod != modCounter)
                throw new ConcurrentModificationException();
            return currentNode != null;
        }

        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
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
