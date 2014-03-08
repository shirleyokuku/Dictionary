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
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public V get(K key) throws NoSuchElementException {
        return retrieveEntry(root, key).getValue();
    }

    @Override
    public void put(K key, V value) {
        root = insertElem(root, key, value);
        size++;
    }

    @Override
    public void remove(K key) throws NoSuchElementException {
        root = deleteElem(root, key);
        size--;
    }

    @Override
    public void clear() {
            // TODO
        size = 0;
    }

    @Override
    public Iterator<DictionaryEntry<K, V>> iterator() {
        return null; // TODO
    }

    private BinarySearchTreeEntry<K, V> retrieveEntry(BinarySearchTreeEntry<K, V> node, K searchKey) {

        if (node != null) {

            K key = node.getKey();
            if (key.compareTo(searchKey) == 0) {
                return node;
            } else if (searchKey.compareTo(key) < 0) {
                return retrieveEntry(node.getLeft(), searchKey);
            } else {
                return retrieveEntry(node.getRight(), searchKey);
            }

        } else {
            throw new NoSuchElementException(); // Element doesn't exist!
        }

    }

    private BinarySearchTreeEntry<K,V> insertElem(BinarySearchTreeEntry<K, V> node, K key, V newValue) {

        // Pre: newElem does not exist in the tree

        if (node == null) { // If node is a leaf - create newNode and make node refer to this

            BinarySearchTreeEntry<K, V> newNode = new BinarySearchTreeEntry<K, V>(key, newValue);
            node = newNode;
            newNode.setLeft(null); newNode.setRight(null); // newNode refers to null

        } else if (key.compareTo(node.getKey()) == 0) { // If key == nodeKey, update value
            node.setValue(newValue);
        } else if (key.compareTo(node.getKey()) < 0) {  // If key < nodeKey, insert to left
            node.setLeft(insertElem(node.getLeft(), key, newValue));
        } else {                                        // Otherwise to the right
            node.setRight(insertElem(node.getRight(), key, newValue));
        }

        return node;

    }

    private BinarySearchTreeEntry<K, V> deleteElem(BinarySearchTreeEntry<K, V> node, K key) throws NoSuchElementException {

        if (node == null) {
            throw new NoSuchElementException("Cannot delete null node");
        } else if (key.equals(node.getKey())) {
            node = deleteNode(node);
        } else if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(deleteElem(node.getLeft(), key));
        } else {
            node.setRight(deleteElem(node.getRight(), key));
        }

        return node;
    }

    private BinarySearchTreeEntry<K, V> deleteNode(BinarySearchTreeEntry<K, V> node) {

        if (node.getLeft() == null && node.getRight() == null) { // If node is a leaf
            return null;
        } else {

            if (node.getRight() == null) { // Both can't be null. If right is null, left CAN'T be null
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else {
                BinarySearchTreeEntry<K, V> replacementNode = findLeftMost(node.getRight());
                BinarySearchTreeEntry<K, V> newRight = deleteLeftMost(node.getRight());
                replacementNode.setRight(newRight);
                replacementNode.setLeft(node.getLeft());
                return replacementNode;
            }

        }

    }

    private BinarySearchTreeEntry<K, V> findLeftMost(BinarySearchTreeEntry<K, V> node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return findLeftMost(node.getLeft());
        }
    }

    private BinarySearchTreeEntry<K, V> deleteLeftMost(BinarySearchTreeEntry<K, V> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        } else {
            node.setLeft((deleteLeftMost(node.getLeft())));
        }
        return node;
    }

    private boolean search(BinarySearchTreeEntry<K, V> node, K searchKey) {

        //POTENTIALLY WRONG - CHECK IF CLASS DOESN'T PASS TEST CASE
        if (node.getLeft() == null && node.getRight() == null) { // If tree empty, stop!
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


}
