package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Ordered linked list based implementation of the Dictionary
 * interface. The nodes of the list are ordered in ascending order by
 * the key-attribute of type K. Duplicate keys are not permitted.
 */
public class OrderedLinkedList<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {


    // Keys have a compareTo(K other)
    // Need to implement Iterator<DictionaryEntry <K, V>> - returns an iterator of DictionaryEntry

    private int size;

    /**
     *
     * @return the number of key-value associations stored in this dictionary
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return true if and only if this dictionary is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the value associated with the key. There can be at most one value
     * associated with each key.
     *
     * @param key
     *            The key to look for inside the dictionary
     * @return the value associated with the key
     * @throws NoSuchElementException
     *             if given key does not exist in the dictionary
     */
    public V get(K key) throws NoSuchElementException {

        V value;

        try {

        } catch (NoSuchElementException e) {

        }

        return (V) value;
    }

    /**
     * Creates a dictionary entry associating the given key and value. If the
     * dictionary previously contained an entry for this key, the old value is
     * replaced with the given value.
     *
     * @param key
     *            The key to associate the value with
     * @param value
     *            The value to be associated with the key
     */
    public void put(K key, V value);

    /**
     * Removes the entry for the key from the dictionary if it is present.
     *
     * @param key
     *            The key to remove from the dictionary
     * @throws NoSuchElementException
     *             if the key is not in the dictionary
     */
    public void remove(K key) throws NoSuchElementException;

    /**
     * Removes all entries from the dictionary
     */
    public void clear();

}
