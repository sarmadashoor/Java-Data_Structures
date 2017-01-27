/*  LinkedListDS.java
 
 
 
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDS<E> implements ListADT<E> {
    
    private Node<E> head,tail;
    private int currentSize = 0;
    
    public void addFirst(E obj) {
        Node<E> node = new Node(obj);
        if(head == null)
        {
            head = tail = node;
        }
        else
        {
            node.next = head;
            head = node;
        }
        currentSize++;
    }

    public void addLast(E obj) {
        Node<E> node = new Node(obj);
        if(head == null)
        {
            head = tail = node;
        }
        else
        {
            tail.next = node;
            tail = node;
        }
        currentSize++;
    }

    public E removeFirst() {
        if(head == null)
            return null;
        currentSize--;
        E removed = head.data;
        if(head == tail)
            head = tail = null;
        else
            head = head.next;
        return removed;
    }

    public E removeLast() {
        Node<E> current = head, previous = null;
        while(current != null && current.next != null)
        {
            previous = current;
            current = current.next;
        }
        if(current == null)
            return null;
        if(current == head)
            return removeFirst();
        tail = previous;
        tail.next = null;
        return current.data;
    }

    public E peekFirst() {
        return head.data;
    }

    public E peekLast() {
        return tail.data;
    }

    public E find(E obj) {
        Node<E> node = head;
        while(node != null && ((Comparable<E>)obj).compareTo(node.data) != 0)
            node = node.next;
        if(node != null)
            return node.data;
        return null;
    }

    public boolean remove(E obj) {
        Node<E> current = head, previous = null;
        while(current != null && ((Comparable<E>)obj).compareTo(current.data) != 0)
        {
            previous = current;
            current = current.next;
        }
        if(current == null)
            return false;
        if(current == head)
            removeFirst();
        else if(current == tail)
        {
            tail = previous;
            tail.next = null;  
        }
        else
        {
            previous.next = current.next;
            currentSize--;
        }
                    
        return true;
    }

    public void makeEmpty() {
        head = tail = null;
        currentSize = 0;
    }

    public boolean contains(E obj) {
        Node<E> node = head;
        while(node != null && ((Comparable<E>)obj).compareTo(node.data) != 0)
            node = node.next;
        return node != null;
    }

    public boolean isEmpty() {
        if(currentSize == 0)
            return true;
        return false;
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return currentSize;
    }

    public Iterator iterator() {
        return new IteratorHelper();
    }
    
    protected class IteratorHelper implements Iterator<E>
    {
        Node<E> current = head;
        public boolean hasNext() {
            return current != null; 
        }

        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Node<E> previous = current;
            current = current.next;
            return previous.data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
        
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
