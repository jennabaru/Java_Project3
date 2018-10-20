package project3;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;
import java.util.Arrays;


public class LinkedList<E> implements Collection<E>, Iterable<E>{
 
    //head
    private Node<E> head = null;

    //size
    private int size = 0;


    //default constructor
    public LinkedList(){
    }

    
    private class Node<E>{
        //item field, next, prev
        private E item;
        private Node<E> next;

        Node(E item, Node<E> next){
            this.item = item;
            this.next = next;

        }

    }

    private class LinkedListIterator implements Iterator<E> {

        Node<E> current = head;     

        public boolean hasNext() {
            if (current != null) {
                return true;
            } else {
                return false;
            }
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> nextValue = current;
            current = current.next;
            return nextValue.item;
        }
    }

    public Iterator<E> iterator(){
        return new LinkedListIterator();
    }

    //  implement all of the methods from the interface
    int indexOf(Object o){

        int index  = 0;
       
        for (index = 0; index < size; index ++) {
            if (!(get(index) == null || o == null)  &&   get(index).equals(o) == true) {
                return index;
            }
        }
        return -1;
    }

    E get(int index)throws IndexOutOfBoundsException{

        Node<E> current = head;
        int i = 0;

        while (current != null) {
            if (index == i) {
                return current.item;
            }

            current = current.next;
            i = i + 1;
        }

        throw new IndexOutOfBoundsException("index out of bounds");

    }

    @Override
    public String toString(){

        Iterator<E> iterator = iterator();

        String toStr = "[";
        while (iterator.hasNext()) {
            toStr = toStr + String.valueOf(iterator.next());

            if (iterator.hasNext()) {
                toStr = toStr + ", ";
            }
        }

        toStr = toStr + "]";
        return toStr;
    }

    @SuppressWarnings("")
    public void sort ( ) {
        Object [] array = toArray();
        Arrays.sort(array); this.clear();
        for (Object o : array ) {
            this.add( (E)o );
        }
    }

    public boolean add(E e){
        
        if (e == null) {
            return false;
        }

        Node<E> last = null;
        if(head == null){
            Node<E> addNode = new Node<E>(e, head);   
            head = addNode;
        }else{
            last = head;
            while(last.next!=null){
                last=last.next;
            }
            Node<E> addNode = new Node<E>(e, head);  
            last.next= addNode;
        }
        size = size + 1;
        
        
        return true;
    }

    public boolean addAll(Collection<? extends E> c){
        throw new UnsupportedOperationException("addAll not supported");
    }

    public void clear(){
        head = null;
        size = 0;
    }

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

    @Override
    public boolean containsAll(Collection<?> c){
    
        for (Object item: c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }
 
    public boolean equals(Object objA){

        if (this.getClass() == objA.getClass() && this == objA) {
            return true;
        }

        LinkedList<?>objB = (LinkedList<?>)objA;

        if (objB.size() != size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if(objB.get(i).equals(get(i))!= true) {
                return false;
            }
        }
        return true;
    }

    public int hashCode(){
        throw new UnsupportedOperationException("hashCode not supported");
    }

    public boolean isEmpty(){
    
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean remove(Object o){

        if (!contains(o)) {
            return false;
        }

        // delete the object from the list
        Node<E> current = head;
        Node<E> prev  = null;

        while (current != null) {
            if (o.equals(current.item)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size = size - 1;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public boolean removeAll(Collection<?> c){
        throw new UnsupportedOperationException("removeAll not supported");
    }

    public boolean retainAll(Collection<?> c){
        throw new UnsupportedOperationException("retainAll not supported");
    }

    public int size(){
        return this.size;
    }
    public Object[] toArray(){
        Object[] array = new Object[size()];

        Node<E> current = this.head;
        int i = 0;
        while(current != null) {
            array[i] = current;
            i = i + 1;
            current = current.next;
        }
        return array;
    }

    public <T> T[] toArray(T[] a) { 

        if(a.length < size()) { 
            
            Node<E> current = this.head;
            int i = 0;
            while(current != null && i < size()) {
                a[i] = (T)current.item;
                i = i + 1;
                current = current.next;
            }
            return a;
        } else {
            Node<E> current = this.head;
            int i = 0;
            while(current != null) {
                a[i] = (T)current.item;
                i = i + 1;
                current = current.next;
            }
            return a;
        }      
    }

}