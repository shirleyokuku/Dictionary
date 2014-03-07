package dictionary;


public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {

    private K key;
    private V value;
    private OrderedLinkedListEntry<K, V> next;

    public OrderedLinkedListEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key in this entry
     */
    public K getKey() {
        return key;
    }

    /**
     * @return the value in this entry
     */
    public V getValue() {
        return value;
    }

    public OrderedLinkedListEntry<K, V> getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(OrderedLinkedListEntry<K, V> next) {
        this.next = next;
    }

}
