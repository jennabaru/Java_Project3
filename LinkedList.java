package project3;

public class LinkedList<E> implements Collection<E>, Iterable<E>{
    //head
    //tail
    //size


    //default constructor
    public LinkedList(){
    }

    private static class Node<E>{
        //item field, next, prev
        private E item;
        Node<E> next;

        Node(E item, Node<E> next){
            this.item = item;
            this.nextItem = next;

        }

    }

    private class Iterator(){
        //current
        //this.current=head

        //hasnext and next throws no such element exception (when list has no elements)
    }

    //  implement all of the methods from the interface
    int indexOf(Object o){

    }
    E get(int index)throws IndexOutOfBoundsException{

    }
    @Override
    String toString(){

    }
    @SuppressWarnings
    public void sort ( ) {
        Object [] array = toArray(); 
        Arrays.sort(array); 
        this.clear();
        for (Object o : array ) { 
            this.add( (E)o );
        } 
    }
    @Override
    public boolean add(E e){
        //textbook
        newest=Node(e);
        newest.next = null;
        tail.next = newest;
        tail = newest;
        size = size + 1;
        //end textbook
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
    
    }
    @Override
    public void clear(){

    }
    @Override
    public boolean contains(Object o){
    
    }
    @Override
    public boolean containsAll(Collection<?> c){

    }
    @Override
    public boolean equals(Object o){
    
    }
    @Override
    public int hashCode(){
    
    }
    @Override
    public boolean isEmpty(){
    
    }
    @Override
    public Iterator<E> iterator(){
        return null;
    }
    @Override
    public boolean remove(Object o){

    }
    @Override
    public boolean removeAll(Collection<?> c){

    }
    @Override
    public boolean retainAll(Collection<?> c){

    }
    @Override
    public int size(){

    }
    @Override
    public Object[] toArray(){

    }
    @Override
    public <T> T[] toArray(T[] a){

    }

}