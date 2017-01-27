/*  BalancedTree.java
 
 
 
 */
package data_structures;

import java.util.Iterator;
import java.util.TreeMap;

public class BalancedTree<K,V> implements DictionaryADT<K,V> {

    private TreeMap<K,V> tree;
    
    public BalancedTree() {
        tree = new TreeMap();
    }
    
    public boolean contains(K key) {
        return tree.containsKey(key);
    }

    public boolean insert(K key, V value) {
        tree.put(key, value);
        return true;
    }

    public boolean remove(K key) {
        return tree.remove(key) != null;
    }

    public V getValue(K key) {
        return tree.get(key);
    }

    public K getKey(V value) {
        K key = null;
        Iterator<V> values = values();
        Iterator<K> keys = keys();
        while(values.hasNext()) {
            if(((Comparable<V>)values.next()).compareTo(value) == 0) {
                key = keys.next();
                break;
            }
            keys.next();
        }
        return key;
    }

    public int size() {
        return tree.size();
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public void clear() {
        tree.clear();
    }

    public Iterator<K> keys() {
        return tree.keySet().iterator();
    }

    public Iterator<V> values() {
        return tree.values().iterator();
    }
}
