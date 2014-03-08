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

    private OrderedLinkedListEntry<K, V> head;
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
    public V get(K key) {

        OrderedLinkedListEntry<K, V> entry = getEntry(key); // May throw NoSuchElementException
        return entry.getValue();

    }

    private OrderedLinkedListEntry<K, V> getEntry(K key) throws NoSuchElementException {

        OrderedLinkedListEntry<K, V> entry = head;
        boolean found = false;

        try { // See if head has a key
            entry.getKey();
        } catch (NullPointerException e) {
            throw new NoSuchElementException();
        }

        do {
            if (entry.getKey().compareTo(key) == 0) {
                found = true; // We've found the key!
                break; // Get out of here!
            } else {
                entry = entry.getNext(); // Try the next one...
            }
        } while (entry.getNext() != null); // Stop at end of list, or when key found

        if (!found) {
            throw new NoSuchElementException("The key is not in the list!");
        }

        return entry;
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
    public void put(K key, V value) {

        OrderedLinkedListEntry<K, V> prev = this.findPrev(key);
        OrderedLinkedListEntry<K, V> newEntry = new OrderedLinkedListEntry<K, V>(key, value);

        // If list is empty, add single node, and make head refer to this
        if (prev == null) {

            newEntry.setNext(head);
            head = newEntry;

        // If prevKey == key, update the value
        } else if (prev.getKey().compareTo(key) == 0) {

            prev.setValue(value);

        // If prev < key, add a new entry with key and value after prev
        } else if (prev.getKey().compareTo(key) < 0) {

            newEntry.setNext(prev.getNext());
            prev.setNext(newEntry);

        // Add a new entry with key and value as first node
        } else {

            newEntry.setNext(head);
            head = newEntry;

        }

        size++;

    }

    // Helper for put
    private OrderedLinkedListEntry<K,V> findPrev(K searchKey){

        // post: Returns the Node with key equal to searchKey, if any exists in the list.
        // Or it returns the previous key (if any).
        // Or if the previous key does not exists, it returns the Node with key after searchKey (if any).
        // Or it returns null if the list is empty.

        OrderedLinkedListEntry<K,V> prev = head;

        if ( (prev != null) && (prev.getKey().compareTo(searchKey)<0) ) {

            OrderedLinkedListEntry<K,V> curr = prev.getNext();

            while ((curr != null)&&(curr.getKey().compareTo(searchKey) <= 0)){
                prev = curr;
                curr = curr.getNext();
            }
        }
        return prev;
    }

    /**
     * Removes the entry for the key from the dictionary if it is present.
     *
     * @param key
     *            The key to remove from the dictionary
     * @throws NoSuchElementException
     *             if the key is not in the dictionary
     */
    public void remove(K key) {

        OrderedLinkedListEntry<K, V> entry = getEntry(key); // May throw NoSuchElementException
        OrderedLinkedListEntry<K, V> before = findStrictlyPrev(entry.getKey());

        if (before == null) { // If entry is the head of the list
            head = entry.getNext(); // Make entry immediately after the head of the list
        } else {
            before.setNext(entry.getNext()); // Make entry before refer to the entry after
        }

        size--;
    }

    private OrderedLinkedListEntry<K, V> findStrictlyPrev(K key) {

        OrderedLinkedListEntry<K, V> current = head;
        Iterator<DictionaryEntry<K, V>> iter = this.iterator();

        if (current.getKey().compareTo(key) == 0) {
            return null; // There's no entry before the head! Return null
        } else {
            while (iter.hasNext()) {
                if (current.getNext().getKey().compareTo(key) == 0) { // If entry after current is the key
                    return current; // This must be the previous node!
                } else {
                    current = (OrderedLinkedListEntry<K, V>) iter.next(); // Try again...
                }
            }
        } // Out of this if-else statement

        throw new NoSuchElementException(); // Only reached if key not found

    }

    /**
     * Removes all entries from the dictionary
     */
    public void clear() {

        OrderedLinkedListEntry<K, V> current = head;
        OrderedLinkedListEntry<K, V> next;
        Iterator<DictionaryEntry<K, V>> iter = this.iterator();

        while (iter.hasNext()) {
            next = current.getNext();
            remove(current.getKey());
            current = next;
        }

    }

    @Override
    public Iterator<DictionaryEntry<K, V>> iterator() {
            return new ListIterator<K, V>();
    }

    private class ListIterator<K, V> implements Iterator<DictionaryEntry<K, V>> {

        private OrderedLinkedListEntry<K, V> current;

        private ListIterator() {
            current = (OrderedLinkedListEntry<K, V>) head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public DictionaryEntry<K, V> next() {
            if (current == null) {
                return null;
            } else {
                DictionaryEntry<K, V> result = current;
                current = current.getNext();
                return result;
            }
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }
}
