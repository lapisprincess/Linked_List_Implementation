/**
 * An abbreviated List<E> interface
 *
 * @author David
 * @version 10/6/19
 */
public interface MyList<E> {
    /**
     * Adds an item to the tail of the list
     * 
     * @param item the item to add
     * @return always return true
     */
    boolean add(E item);

    /**
     * Adds item to specified location in the list
     * 
     * @param index the position in which to add the item
     * @param item a new item to add
     * @throws ArrayIndexOutOfBoundsException if the given index is illegal
     */
    void add(int index, E item);

    /**
     * Gets the item at the specified index
     * 
     * @param index the item's position
     * @return the item
     * @throws ArrayIndexOutOfBoundsException if the given index is illegal
     */
    E get(int index);

    /**
     * Sets the position at the specified index to a new item
     * 
     * @param index the item's position
     * @param new_item the new item
     * @return a reference to the displaced item
     * @throws ArrayIndexOutOfBoundsException if the given index is illegal
     */    
    E set(int index, E new_item);

    /**
     * Removes an item at the given position
     * 
     * @param index the index to the item to remove
     * @return a reference to the item that was removed
     * @throws ArrayIndexOutOfBoundsException if the given index is illegal
     */
    E remove(int index);

    /**
     * Removes an item from the list.
     * 
     * @param item a reference to the item to remove
     * @return true if successful, and false otherwise
     */
    boolean remove(E item);

    /**
     * @return current size of the list
     */
    int size();

    /**
     * Searches the list for the specified item
     * @param search the item to look for
     * @return the index of the first occurrence if found, -1 otherwise
     */    
    int indexOf(E search);

    /**
     * @return string representation of the array list
     */    
    @Override
    String toString();

    void reverse();
}
