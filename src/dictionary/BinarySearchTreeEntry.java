package dictionary;

// Implementation class representing nodes of the binary search tree.
public class BinarySearchTreeEntry<K, V> implements DictionaryEntry<K, V> {

    private K key;
    private V value;
    private BinarySearchTreeEntry<K, V> left;
    private BinarySearchTreeEntry<K, V> right;

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    //TODO

    public void setValue(V value) {
        this.value = value;
    }

    public void setLeft(BinarySearchTreeEntry<K, V> left) {
        this.left = left;
    }

    public void setRight(BinarySearchTreeEntry<K, V> right) {
        this.right = right;
    }

    public BinarySearchTreeEntry<K, V> getLeft() {
        return left;
    }

    public BinarySearchTreeEntry<K, V> getRight() {
        return right;
    }






}
