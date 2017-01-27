package data_structures;

import java.util.Iterator;

public class UnorderedListPriorityQueue<E> implements PriorityQueue<E> {
    UnorderedList<E> list;
    
    public UnorderedListPriorityQueue() {
        list = new UnorderedList();
    }

    public boolean insert(E object) {
        list.addFirst(object);
        return true;
    }

    public E remove() {
        return list.removePriority();
    }

    public E peek() {
        return list.getPriority();
    }

    public int size() {
        return list.size();
    }

    public boolean contains(E object) {
        return list.contains(object);
    }

    public Iterator<E> iterator() {
        return list.iterator();
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isFull() {
        return list.isFull();
    }
}
