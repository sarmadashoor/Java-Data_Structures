package data_structures;

import java.util.Iterator;

public class OrderedListPriorityQueue<E> implements PriorityQueue<E> {
    OrderedList<E> list;
    
    public OrderedListPriorityQueue() {
        list = new OrderedList();
    }

    public boolean insert(E object) {
        return list.add(object);
    }

    public E remove() {
        return list.removeFirst();
    }

    public E peek() {
        return list.getFirst();
    }

    public int size() {
        return list.size();
    }

    public boolean contains(E object) {
        return list.contains(object);
    }

    public Iterator iterator() {
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
