package project3;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;
import java.util.Arrays;

/**
* This class implements the Collections and Iterable
* interface. It contains a private internal class
* and many methods from the collection anf iterable interfaces.
*
* @author Jenna Baruch * @version 10/19/2018
*/
public class LinkedList<E> implements Collection<E>, Iterable<E>{
 
    //head
    private Node<E> head = null;

    //size
    private int size = 0;

    /**
     * Default constructor
     */
    public LinkedList(){
    }

    /**
     * Private node class, contains a constructor
     * @param <E> accepts the data to be put into the node
     */
    private class Node<E>{

        private E item;
        private Node<E> next;

        /**
         * Node constructor sets item and next data fields
         * @param item type E
         * @param next Node<E>
         */
        Node(E item, Node<E> next){
            this.item = item;
            this.next = next;

        }

    }

    /** 
     * This private internal linkedlistiterator class
     * implements the Iterator<E> interface.
     * Methods include hasNext and next.
     * 
     * @author Jenna Baruch @version 10/20/18
    */
    private class LinkedListIterator implements Iterator<E> {

        Node<E> current = head;     

        /**
         * This method checks to see if current is pointing to null
         * @return true if not null, false if null
         */
        public boolean hasNext() {
            if (current != null) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * This method returns the data inside current node
         * and then moves the pointer to current.next
         * @return data stored inside the node
         * @throws NoSuchElementException
         */
        public E next() throws NoSuchElementException{
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> nextValue = current;
            current = current.next;
            return nextValue.item;
        }
    }

    /**
     * This method when called creates and returns a new
     * LinkedListIterator.
     * @return returns a LinkedListiterator
     */
    public Iterator<E> iterator(){
        return new LinkedListIterator();
    }

    /**
     * This method checks to see if an object exists
     * in a collection.
     * @param o Object
     * @return int: index of object if in list, -1 if not
     */
    int indexOf(Object o){
        int index  = 0;
       
        for (index = 0; index < size; index++) {
            if (!(get(index) == null || o == null)  &&   get(index).equals(o) == true) {
                return index;
            }
        }
        return -1;
    }
/**
 * This method finds and returns the node at
 * a specific index.
 * @param index int index value
 * @return data stored inside of node
 * @throws IndexOutOfBoundsException
 */
    E get(int index)throws IndexOutOfBoundsException{

        Node<E> current = head;
        int i = 0;

        while (current != null) {
            //if at correct index, return item
            if (index == i) {
                return current.item;
            }

            //move current to next
            current = current.next;
            //increment i
            i = i + 1;
        }
        //if index > length of list-1, throw exception
        throw new IndexOutOfBoundsException("Index out of bounds");

    }

    /**
     * This method overrides the toString method and
     * uses iterator to traverse through list and add data
     * to the string, separated by commas enclosed in brackets.
     * @return string concatenation of all items in collection
     */
    @Override
    public String toString(){

        //create new iterator
        Iterator<E> iterator = iterator();

        String toStr = "[";
        while (iterator.hasNext()) {
            //adds data in node to string
            toStr = toStr + String.valueOf(iterator.next());

            if (iterator.hasNext()) {
                toStr = toStr + ", ";
            }
        }

        toStr = toStr + "]";
        return toStr;
    }
/**
 * This method converts a list to an array, 
 * sorts the array, and converts it back to a list.
 * @author Joanna Klukowska
 */
    @SuppressWarnings("")
    public void sort ( ) {
        Object [] array = toArray();
        Arrays.sort(array); 
        this.clear();
        for (Object o : array ) {
            this.add( (E)o );
        }
    }
/**
 * This method adds a new node to the end of the linked list
 * and increments the size of the list
 * @param e E object
 * @return boolean: true once object is added, false if object is null
 */
    public boolean add(E e){
        // if input is null, return false
        if (e == null) {
            return false;
        }

        // create new node
        Node<E> last = null;
        //if list is empty, add new node and assign to head
        if(head == null){
            Node<E> addNode = new Node<E>(e, head);   
            head = addNode;
        // if list already has elements
        }else{
            last = head;
            // find last nodea
            while(last.next!=null){
                last=last.next;
            }
            //make new node
            Node<E> addNode = new Node<E>(e, null);  
            //add node to end of list
            last.next= addNode;
        }
        //increment size
        size = size + 1;
        
        return true;
    }
/**
 * This method throws an error if called
 * @param c Collection
 * @throws UnsupportedOperationException
 */
    public boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("addAll not supported");
    }
    /**
     * This method clears the list and makes size 0
     */
    public void clear(){
        head = null;
        size = 0;
    }
/**
 * This method checks to see if an object exists in a collection
 * @param o Object
 * @return boolean: true if o exists in collection, false if not
 */
    public boolean contains(Object o){

        Node<E> current = head;

        while (current != null) {
            if (current.item.equals(o) == true) {
                return true;
            }

            current = current.next;
        }
        return false;
    }
/**
 * This method overrides the containsAll method
 * and tests to see if a collection contains all 
 * items in c.
 * @param c Collection
 * @return true if contains all, false if one isnt contained
 */
    @Override
    public boolean containsAll(Collection<?> c){
    
        for (Object item: c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }
/**
 * This method tests to see if two linkedLists
 * are the same size and ocntain the same elements.
 * @param objA Object
 * @return true if LinkedLists are the same, false if not
 */
    public boolean equals(Object objA){

        if (this.getClass() == objA.getClass() && this == objA) {
            return true;
        }

        LinkedList<?>objB = (LinkedList<?>)objA;
        //checks if size is same
        if (objB.size() != size()) {
            return false;
        }
    
        for (int i = 0; i < size(); i++) {
            //if each node has same data is false
            if(objB.get(i).equals(get(i))!= true) {
                return false;
            }
        }
        return true;
    }
/**
 * This method throws an exception if called
 * @throws UnsupportedOperationException
 */
    public int hashCode() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("hashCode not supported");
    }

    /**
     * This method checks to see if LinkedList is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty(){
    
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes a node from a
     * linkedlist.
     * @param o Object
     * @return true if item is remover, false otherwise
     */
    public boolean remove(Object o){
        //if o is not in list, return false
        if (!contains(o)) {
            return false;
        }

        Node<E> current = head;
        Node<E> prev  = null;

        while (current != null) {
            // if object to remove is at the head
            if (o.equals(current.item)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size = size - 1;
                return true;
            }
            //move pointer to current.next
            prev = current;
            current = current.next;
        }
        return false;
    }
    /**
     * This method throws an error if called
     * @param c Collection
     * @throws UnsupportedOperationException
     */
    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("removeAll not supported");
    }
    
    /**
     * This method throws an error if called
     * @param c Collection
     * @throws UnsupportedOperationException
     */
    public boolean retainAll(Collection<?> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("retainAll not supported");
    }

    /**
     * This method gets and eturns size
     * @return int size
     */
    public int size(){
        return this.size;
    }

    /**
     * Adds all items of linkedlist to an array
     * @return array of objects
     */
    public Object[] toArray(){
        Object[] array = new Object[size()];

        //make new node, set to head
        Node<E> current = this.head;
        int i = 0;
        while(current != null) {
            //add node to array
            array[i] = current.item;
            i = i + 1;
            current = current.next;
        }
        return array;
    }

    /**
     * This method either adds linkedlist to an existing array
     * or creates a new array of correct size and then adds
     * linkedlist.
     * @param a T[]
     * @return array of type T
     */
    public <T> T[] toArray(T[] a) { 
        //if array is too small to house linkedlist
        if(a.length < size()) { 
            
            // creates a new array that is type compatible with the paramater type T
            T[] biggerA = (T[])Array.newInstance(a.getClass().getComponentType(), size());
            
            //set new node to head
            Node<E> current = this.head;
            int i = 0;
            while(current != null && i < size()) {
                //add node to array
                biggerA[i] = (T)current.item;
                i = i + 1;
                //move pointer to current.next
                current = current.next;
            }
            return biggerA;
        } else {
            //set new node to head
            Node<E> current = this.head;
            int i = 0;
            while(current != null) {
                //add node to array
                a[i] = (T)current.item;
                i = i + 1;
                //move pointer to current.next
                current = current.next;
            }
            return a;
        }      
    }

}