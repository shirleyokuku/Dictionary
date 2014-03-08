package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Binary search tree based implementation of the Dictionary
 * interface. The nodes of the tree are ordered by an associated key-attribute
 * key of type K, such that every node's left subtree contains only nodes 
 * whose key-attributes are less than key, and every node's right subtree
 * contains only nodes whose key-attributes are greater than key. A
 * linear order is defined on keys through the Comparable interface.
 * Duplicate keys are not permitted.
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {

    private BinarySearchTreeEntry<K, V> root;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;         // TODO
    }

    @Override
    public V get(K key) throws NoSuchElementException {
        return null;          // TODO
    }

    @Override
    public void put(K key, V value) {
                              // TODO
    }

    @Override
    public void remove(K key) throws NoSuchElementException {
                              // TODO
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<DictionaryEntry<K, V>> iterator() {
        return null;
    }

    // TODO

    public boolean search(BinarySearchTreeEntry<K, V> node, K searchKey) {

        if (node.getKey() == null) { // If leaf - stop!
            return false;
        } else if (searchKey == node.getKey()) { // Is it in the current node?
            return true;
        } else if (searchKey.compareTo(node.getKey()) < 0) { // The left?
            return search(node.getLeft(), searchKey);
        } else {                                             // Or the right?
            return search(node.getRight(), searchKey);
        }

    }

    public boolean contains(K searchKey) {
        return search(root, searchKey);
    }

    public BinarySearchTree<K, V> createEmptyTree() {
        return new BinarySearchTree<K, V>();
    }

    public BinarySearchTreeEntry<K, V> getRoot() {
        return root;
    }

    public BinarySearchTreeEntry<K, V> getLeftTree() {
        return null;
    }

    public BinarySearchTreeEntry<K, V> getRightTree() {
        return null;
    }


}
