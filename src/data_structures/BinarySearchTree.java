/*  BinarySearchTree.java
 
 
 
 */
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class BinarySearchTree<K,V> implements DictionaryADT<K,V> {
    
    private Node<K,V> root;
    private int currentSize;
    private long modCounter;
    
    public BinarySearchTree() {
        root = null;
        currentSize = 0;
        modCounter = 0;
    }
    
    public boolean contains(K key) {
        if(root == null)
            return false;
        Node<K,V> current = root;
        
        while(((Comparable<K>)current.key).compareTo((K)key) != 0) {
            if(((Comparable<K>)key).compareTo((K)current.key) < 0)
                current = current.leftChild;
            else
                current = current.rightChild;
            
            if(current == null)
                return false;
        }
        
        return true;
    }

    public boolean insert(K key, V value) {
        if(root == null) {
            root = new Node<K,V>(key, value);
            currentSize++;
            modCounter++;
            return true;
        }
        
        Node<K,V> current = root;
        
        while(((Comparable<K>)key).compareTo((K)current.key) != 0) {
            if(((Comparable<K>)key).compareTo((K)current.key) < 0) {
                if(current.leftChild == null) {
                    current.leftChild = new Node<K,V>(key, value);
                    currentSize++;
                    modCounter++;
                    return true;
                }
                current = current.leftChild;
            }
            else {
                if(current.rightChild == null) {
                    current.rightChild = new Node<K,V>(key, value);
                    currentSize++;
                    modCounter++;
                    return true;
                }
                current = current.rightChild;
            }
        }
        return false;
    }

    public boolean remove(K key) {
        if(root == null)
            return false;
        
        Node<K,V> current = root;
        Node<K,V> previous = null;
        boolean left = false;
        while(((Comparable<K>)key).compareTo((K)current.key) != 0) {
            if(((Comparable<K>)key).compareTo((K)current.key) < 0) {
                previous = current;
                current = current.leftChild;
                left = true;
            } 
            else {
                previous = current;
                current = current.rightChild;
                left = false;
            }
            if(current == null)
                return false;
        }
        

        if(current.leftChild == null && current.rightChild == null) {
            if(previous == null) {
                root = null;
            }
            else {
                if(left)
                    previous.leftChild = null;
                else
                    previous.rightChild = null;
            }
        }
        else if(current.leftChild == null && current.rightChild != null) {
            if(previous == null) {
                root = current.rightChild;
            }
            else {
                if(left)
                    previous.leftChild = current.rightChild;
                else
                    previous.rightChild = current.rightChild;
            }
        }
        else if(current.leftChild != null && current.rightChild == null) {
            if(previous == null) {
                root = current.leftChild;
            }
            else {
                if(left)
                    previous.leftChild = current.leftChild;
                else
                    previous.rightChild = current.leftChild;
            }
        }
        else {
            Node<K,V> tmp = current.rightChild;
            Node<K,V> tmpPrev = current;
            boolean right = true;
            while(tmp.leftChild != null) {
                tmpPrev = tmp;
                tmp = tmp.leftChild;
                right = false;
            }
                
            if(previous == null) {
                root.key = tmp.key;
                root.value = tmp.value;
            }
            else {
                current.key = tmp.key;
                current.value = tmp.value;
            }
            if(tmp.rightChild != null) {
                if(!right)
                    tmpPrev.leftChild = tmp.rightChild;
                else
                    tmpPrev.rightChild = tmp.rightChild;
            }
            else
            {
                if(!right)
                    tmpPrev.leftChild = null;
                else
                    tmpPrev.rightChild = null;
            }

        }
        
        currentSize--;
        modCounter++;
        return true;
    }

    public V getValue(K key) {
        if(root == null)
            return null;
        Node<K,V> current = root;
        
        while(((Comparable<K>)current.key).compareTo((K)key) != 0) {
            if(((Comparable<K>)key).compareTo((K)current.key) < 0)
                current = current.leftChild;
            else
                current = current.rightChild;
            
            if(current == null)
                return null;
        }
        
        return current.value;
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
        return currentSize;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
        currentSize = 0;
        modCounter = 0;
    }

    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    public Iterator<V> values() {
        return new ValueIteratorHelper();
    }
    
    protected abstract class IteratorHelper<E> implements Iterator<E> {
        protected Node<K,V>[] nodes;
        protected int index;
        protected long modCheck;
        
        public IteratorHelper() {
            nodes = new Node[currentSize];
            index = 0;
            modCheck = modCounter;
            inOrderFillArray(root);
            index = 0;
        }
        
        private void inOrderFillArray(Node<K,V> node) {
            if(node == null) return;
            
            inOrderFillArray(node.leftChild);
            nodes[index++] = node;
            inOrderFillArray(node.rightChild);
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
            return (K) nodes[index++].key;
        }
    }
    
    protected class ValueIteratorHelper<V> extends IteratorHelper<V> {
        public ValueIteratorHelper() {
            super();
        }
        
        public V next() {
            return (V) nodes[index++].value;
        }
    }
    
    protected class Node<K,V> {
        public K key;
        public V value;
        
        public Node<K,V> leftChild;
        public Node<K,V> rightChild;
        
        public Node(K k, V v) {
            key = k;
            value = v;
            leftChild = rightChild = null;
        }
    }
}
