/*  Hashtable.java
 
 */
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hashtable<K,V> implements DictionaryADT<K,V> {

    private int maxSize, tableSize, currentSize;
    private long modCounter;
    private ListADT<DictionaryNode<K,V>>[] list;
    
    public Hashtable(int size) {
        maxSize = size;
        tableSize = (int)(size*1.3f);
        currentSize = 0;
        modCounter = 0;
        list = new LinkedListDS[tableSize];
        for(int i = 0; i < tableSize; i++) {
            list[i] = new LinkedListDS<DictionaryNode<K,V>>();
        }
    }
    
    public boolean contains(K key) {
        int hash = (key.hashCode()&0x7fffffff)%tableSize;
        return list[hash].contains(new DictionaryNode<K,V>(key,null));
    }

    public boolean insert(K key, V value) {
        if(isFull())
            return false;
        int hash = (key.hashCode()&0x7fffffff)%tableSize;
        if(list[hash].contains(new DictionaryNode<K,V>(key, null)))
            return false;
        
        list[hash].addLast(new DictionaryNode<K,V>(key, value));
        currentSize++;
        modCounter++;
        return true;
    }

    public boolean remove(K key) {
        int hash = (key.hashCode()&0x7fffffff)%tableSize;
        if(list[hash].remove(new DictionaryNode<K,V>(key,null))) {
            currentSize--;
            modCounter++;
            return true;
        }
        return false;
    }

    public V getValue(K key) {
        int hash = (key.hashCode()&0x7fffffff)%tableSize;
        DictionaryNode<K,V> tmp = list[hash].find(new DictionaryNode<K,V>(key,null));
        if(tmp == null)
            return null;
        return tmp.value;
    }

    public K getKey(V value) {
        for(int i = 0; i < tableSize; i++)
            for(DictionaryNode n : list[i])
                if(((Comparable<V>)value).compareTo((V)n.value) == 0)
                    return (K) n.key;
        return null;
    }

    public int size() {
        return currentSize;
    }

    public boolean isFull() {
        return currentSize >= maxSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void clear() {
        currentSize = 0;
        modCounter = 0;
        for(int i = 0; i < tableSize; i++) {
            list[i].makeEmpty();
        }
    }

    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    public Iterator<V> values() {
        return new ValueIteratorHelper();
    }
    
    protected abstract class IteratorHelper<E> implements Iterator<E> {
        protected DictionaryNode<K,V>[] nodes;
        protected int index;
        protected long modCheck;
        
        public IteratorHelper() {
            nodes = new DictionaryNode[currentSize];
            index = 0;
            int j = 0;
            modCheck = modCounter;
            for(int i = 0; i < tableSize; i++)
                for(DictionaryNode n : list[i])
                    nodes[j++] = n;
            
            nodes = (DictionaryNode<K,V>[]) ObjectSorter.quickSort(nodes);
        }
        
        public boolean hasNext() {
            if(modCheck != modCounter)
                throw new ConcurrentModificationException();
            return index < currentSize;
        }

        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    protected class KeyIteratorHelper<K> extends IteratorHelper<K> {
        public KeyIteratorHelper() {
            super();
        }
        
        public K next() {
            if (!hasNext()) 
                throw new NoSuchElementException();
            return (K) nodes[index++].key;
        }
    }
    
    protected class ValueIteratorHelper<V> extends IteratorHelper<V> {
        public ValueIteratorHelper() {
            super();
        }
        
        public V next() {
            if (!hasNext()) 
                throw new NoSuchElementException();
            return (V) nodes[index++].value;
        }
    }

    protected class DictionaryNode<K, V> implements Comparable<DictionaryNode<K,V>> {
        K key;
        V value;

        public DictionaryNode(K k, V v) {
            key = k;
            value = v;
        }

        public int compareTo(DictionaryNode<K, V> node) {
            return ((Comparable<K>)key).compareTo((K)node.key);
        }
    }
    
    protected static class ObjectSorter {
        public static Object[] quickSort(Object[] array) {
            return quickSort(array,0,array.length-1);
        }
        
        private static Object[] quickSort(Object[] array, int leftIndex, int rightIndex) {
            if(leftIndex < rightIndex) {
                int pivot = partition(array, leftIndex, rightIndex);
                quickSort(array, leftIndex, pivot-1);
                quickSort(array, pivot+1, rightIndex);
            }
            return array;
        }
        
        private static int partition(Object[] array, int leftIndex, int rightIndex) {
            int pivot = rightIndex;
            Object pivotItem = array[pivot];
            
            while(leftIndex < rightIndex)
            {
                while(((Comparable)array[leftIndex]).compareTo(array[pivot]) <= 0 && leftIndex < rightIndex)
                    leftIndex++;
                while(((Comparable)array[rightIndex]).compareTo(array[pivot]) >= 0 && leftIndex < rightIndex)
                    rightIndex--;
                if(leftIndex < rightIndex) {
                    Object tmp = array[leftIndex];
                    array[leftIndex] = array[rightIndex];
                    array[rightIndex] = tmp;
                } 
            }
            array[pivot] = array[leftIndex];
            array[leftIndex] = pivotItem;
            return leftIndex;
        }
    }
}
