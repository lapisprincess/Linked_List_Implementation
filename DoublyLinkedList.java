/**
 * This is a loose implementation of the singly linked list.
 * Note: It does not implement Java's List<E> interface.
 *
 * @author David
 * @version 6/29/17
 */
public class DoublyLinkedList<E> implements MyList<E>
{
    private Node<E> head;   /** this is the first node in the list */
    private Node<E> tail;   /** this is the last node in the list */

    private int size;  /** counts the number of nodes linked in the list */
    private int hop_count;  /** counts the number of hops taken */

    private int cache_index; /** cache last used index */
    private Node<E> cache_node; /** cache last used node */

    /**
     * Creates an empty list
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;

        this.size = 0;
        this.hop_count = 0;

        this.cache_index = 0;
        this.cache_node = null;
    }
    
    public int getHopCount() {
        return this.hop_count;
    }

    public void resetHopCount() {
        this.hop_count = 0;
    }
    
    /**
     * Removes an item at the given position
     * 
     * @param index the index to the item to remove
     * @return a reference to the item that was removed
     * @throws ArrayIndexOutOfBoundsException if the given index is illegal
     */
    public E remove(int index) {
        if (index == 0) {
            return this.removeFirst();
        } else if (index > 0 && index == this.size - 1) {
            return this.removeLast();
        }

        Node<E> current_node = this.getNode(index - 1);
        this.cache_index = index - 1;
        this.cache_node = current_node;
        if (current_node != null) {
            return this.removeAfter(current_node);
        }
        return null;
    }
    
    /**
     * Removes an item from the list.
     * @param item a reference to the item to remove
     * @return true if successful, and false otherwise
     */
    @Override
    public boolean remove(E item) {
        int index = this.indexOf(item);
        if (index >= 0) {
            this.remove(index);
            return true;
        }
        return false;
    }


    /**
     * Get the data at index
     *
     * @param index The position of the data to return
     * @return The data at index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        //get the node at the specified index, then return its data portion
        Node<E> node = this.getNode(index);
        return node.data;
    }

    /**
     * Store a reference to new_value in the element at position index.
     * @param index The position of the item to change
     * @param new_value The new data
     * @return The data previously at index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public E set(int index, E new_value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        Node<E> node = this.getNode(index);  
        E old_val = node.data;
        node.data = new_value;
        return old_val;
    }

    /**
     * Adds an element after the specified index
     * @param index The position at which to add this element
     * @param item The new element
     * @return true if successful
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        hop_count++;
        if (index == 0) {
            this.addFirst(item);
        } else {
            Node<E> node = (index == this.size) ? 
                this.tail :
                this.getNode(index-1);
            this.addAfter(node, item);
        }
    }

    /** 
     * Adds an element to the tail of the list
     * @param item The new element
     * @return true if successful
     */
    @Override
    public boolean add(E item) {
        this.add(size, item);
        return true;
    }

    /**
     * Searches the list for the specified item
     * @param item the item to look for
     * @return the index of the first occurrence if found, -1 otherwise
     */
    @Override
    public int indexOf(E item) {
        Node<E> current = this.head; //start iterating from the head node
        for (int i = 0; i < size; i++) {
            if (item.equals(current.data)) {
                this.cache_node = current;
                this.cache_index = i;
                return i;
            }
            current = current.next;
        }
        return -1;  //not found
    }

    /**
     * Add an item to the head of the list.
     * @param item The item to be added
     */
    private void addFirst(E item) {
        Node<E> new_node = new Node<E>(null, item, this.head);
        this.head = new_node;
        if (size == 0) {
            this.tail = new_node;
        }
        this.size++;
    }

    /**
     * Add a node after a given node
     *
     * @param node The node preceding the new item
     * @param item The item to insert
     */
    private void addAfter(Node<E> node, E item) {
        Node<E> new_node = new Node<E>(node.prev, item, node.next);
        node.next = new_node; 
        if (node.equals(this.tail)) {
            this.tail = new_node;
        }
        this.size++;
    }

    /**
     * Remove the first node from the list
     * @return The removed node's data or null if the list is empty  
     */
    private E removeFirst() {
        Node<E> top_node = this.head;
        if (size > 0) {
            this.head = top_node.next;
            this.size--;
            return top_node.data;
        }
        return null;
    }

    private E removeLast() {
        E curr_last_node = this.tail.data;
        Node new_last_node = this.getNode(this.size - 2);
        new_last_node.next = null;
        this.tail = new_last_node;
        this.size--;
        return curr_last_node;
    }

    /**
     * Remove the node after a given node
     *
     * @param node The node before the one to be removed
     * @return The data from the removed node, 
     *         or null if there is no node to remove
     */
    private E removeAfter(Node<E> node) {
        Node<E> next_node = node.next;
        if (next_node != null) {
            node.next = next_node.next;
            this.size--;
            return next_node.data;
        }
        return null;
    }

    /**
     * Find the node at a specified position
     * @param index The position of the node sought
     * @return The node at index or null if it does not exist
     */
    private Node<E> getNode(int index) {
        int i;
        Node<E> current;
        if (0 < cache_index && cache_index < index) {
            i = cache_index;
            current = cache_node;
        } else {
            i = 0;
            current = this.head;
        }

        if (index < size / 2) {
            while ( i < index && current != null) {
                i++;
                current = current.next;
                hop_count++;
            }
        } else {
            while (i > index && current != null) {
                i--;
                current = current.prev;
                hop_count;
            }
        }

        this.cache_node = current;
        this.cache_index = i;
        return current;
    }

    /**
     * @return current size of the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return string representation of the list
     */
    @Override
    public String toString() {
        String ret = "[";
        Node<E> current = this.head; 
        for (int i = 0; i < size; i++) {
            ret += current.toString();
            if (i < size-1) {
                ret += ", ";
            }
            current = current.next;
            this.hop_count++;
        }
        ret += "]";
        this.cache_node = current;
        this.cache_index = size - 1;
        return ret;
    }

    @Override 
    public void reverse() {
        Node new_head = this.getNode(this.size-1); 
        for (int i = this.size - 1; i >= 0; i--) {
            Node current = this.getNode(i);
            current.next = (i == 0) ? null : this.getNode(i - 1);
        }
        this.head = new_head;
    }

    /**
     * A list node can store a data element and a reference to the next 
     * list node.
     *
     * @author David
     * @version 6/29/17
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        /**
         * Creates a new node with a null next field
         *
         * @param new_data a value for the data element
         */
        public Node(E new_data) {
            this.data = new_data;
        }

        /**
         * Creates a new node with a given next field
         *
         * @param prev_node a reference to the previous node in the list
         * @param init_data a value for the data element
         * @param next_node a reference to the next node in the list
         */
        public Node(E new_data, Node<E> next_node) {
            this.prev = prev;
            this.data = new_data;
            this.next = next_node;
        }

        /**
         * @return the string representation of this node
         */
        @Override
        public String toString() {
            return this.data.toString();
        }
    }
}
