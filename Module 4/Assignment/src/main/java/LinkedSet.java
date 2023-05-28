import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Gregory Walker (ghw0012@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the index size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {

        //Checks for element being false or the list already containing the element
        if ((element == null) || (this.contains(element))) {
            return false;
        }

        //Creates a new node and checks to see if the list is empty
        Node n = new Node(element);
        if (isEmpty()) {
            front = n;
            rear = n;
        }
        else {

            //Finds the node in the list that the element needs to be placed before or after
            Node index = indexFind(element);

            //If list size = 1, check if the element is greater or less than the node
            if (size == 1) {

                //If less than, insert the element before the node
                if (element.compareTo(index.element) < 0) {
                    n.next = index;
                    index.prev = n;
                    front = n;
                }
                //Else, insert the element after the node.
                else {
                    n.prev = index;
                    index.next = n;
                    rear = n;
                }

            }
            //If at the end of the list, check if the element is greater or less than the node
            else if (index.next == null) {

                //If greater than, insert the element after the node
                if (element.compareTo(index.element) > 0) {
                    n.prev = index;
                    index.next = n;
                    rear = n;
                }
                //Else, insert the element before the node
                else {
                    n.next = index;
                    n.prev = index.prev;
                    index.prev.next = n;
                    index.prev = n;
                }

            }
            //If at the beginning of the list, check if the element is greater or less than the node
            else if (index.prev == null) {

                //If less than, insert the element before the node
                if (element.compareTo(index.element) < 0) {
                    n.next = index;
                    index.prev = n;
                    front = n;
                }
                //Else, insert the element after the node
                else {
                    n.prev = index;
                    n.next = index.next;
                    index.next.prev = n;
                    index.next = n;
                }

            }
            //In all other cases, insert the element before the node, linking it to both the
            //next node and the previous node
            else {
                n.next = index;
                n.prev = index.prev;
                index.prev.next = n;
                index.prev = n;
            }

        }
        size++;
        return true;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {

        if (this.contains(element)) {

            Node index = indexFind(element);

            if (size == 1) {
                index = null;
                front = index;
                rear = index;
            }
            else if (index == front) {
                front = index.next;
                front.prev = null;
                index = null;
            }
            else if (index == rear) {
                rear = index.prev;
                rear.next = null;
                index = null;
            }
            else {

                index.next.prev = index.prev;
                index.prev.next = index.next;
                index = null;

            }

            size--;
            return true;

        }

        return false;

    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {

        return locate(element) != null;

    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {

        if ((this.size() != s.size())) {
            return false;
        }

        int tempSize = this.size();
        int commonElements = 0;
        Node n = front;

        for (int i = 0; i < tempSize; i++) {

            if ((s.contains(n.element))) {
                commonElements++;
            }

            n = n.next;

        }

        if (commonElements == tempSize) {
            return true;
        }

        return false;

    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        return (complement(s).size() == 0) && (size == s.size());
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){

        Node index = this.front;
        LinkedSet<T> finalSet= new LinkedSet<T>();

        while (index != null) {
            finalSet.add(index.element);
            index = index.next;
        }

        Iterator<T> itr = s.iterator();
        while (itr.hasNext()) {
            finalSet.add(itr.next());
        }

        return finalSet;

    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){

        Node index = this.front;
        LinkedSet<T> finalSet= new LinkedSet<T>();

        while (index != null) {
            finalSet.add(index.element);
            index = index.next;
        }

        Iterator<T> itr = s.iterator();
        while (itr.hasNext()) {
            finalSet.add(itr.next());
        }

        return finalSet;

    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {

        LinkedSet<T> finalSet = new LinkedSet<T>();
        Node index = this.front;

        while (index != null) {

            if (s.contains(index.element)) {
                finalSet.add(index.element);
            }

            index = index.next;

        }

        return finalSet;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {

        LinkedSet<T> finalSet = new LinkedSet<T>();
        Node index = this.front;

        while (index != null) {

            if (s.contains(index.element)) {
                finalSet.add(index.element);
            }

            index = index.next;

        }

        return finalSet;

    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {

        LinkedSet<T> finalSet = new LinkedSet<T>();
        Node n = front;

        while (n != null) {

            if (!(s.contains(n.element))) {
                finalSet.add(n.element);
            }

            n = n.next;

        }

        return finalSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {

        LinkedSet<T> finalSet = new LinkedSet<T>();
        Node n = front;

        while (n != null) {

            if (!(s.contains(n.element))) {
                finalSet.add(n.element);
            }

            n = n.next;

        }

        return finalSet;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new ListIterator();
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new descendingListIterator();
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {



        return null;
    }

    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    private Node locate(T element) {

        Node n = front;
        while (n != null) {
            if (n.element.compareTo(element) == 0) {
                return n;
            }
            n = n.next;
        }

        return null;

    }

    private Node indexFind(T element) {

        Node index = front;
        while ((index.next != null) && (element.compareTo(index.element) > 0)) {
            index = index.next;
        }

        return index;

    }

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

    private class ListIterator implements Iterator<T> {

        private Node index = front;

        public boolean hasNext() {
            return index != null;
        }

        public T next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T result = index.element;
            index = index.prev;
            return result;

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class descendingListIterator implements Iterator<T> {

        private Node index = rear;

        public boolean hasNext() {
            return index != null && index.element != null;
        }

        public T next() {

            if (index != null) {
                T result = index.element;
                index = index.prev;
                return result;
            }

            return null;

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
