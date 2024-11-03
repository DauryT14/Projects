import java.util.Iterator;
import java.util.NoSuchElementException;
import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<T> implements Iterable<T> {
    private int n; // number of items on this deque
    private Node first; // the first Node of the deque
    private Node last; // the last Node of the deque

    // Constructs an empty deque.
    public LinkedDeque() {
        // initializing instance variables
        this.n = 0;
        this.first = null;
        this.last = null;
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        // returns whether the size is 0 (there are no items in the deque)
        return size() == 0;
    }

    // Returns the number of items in this deque.
    public int size() {
        return this.n;
    }

    // Adds item to the front of this deque.
    public void addFirst(T item) {
        // if the item is empty, we throw a null pointer exception
        if(item == null){
            throw new NullPointerException("item is null");
        }
        // setting the old first node to this new first node
        Node oldFirst = this.first;
        // setting the new first to a new node
        this.first = new Node();
        // setting the item of node first to the item we want to add
        this.first.item = item;
        // setting the next node of first to the old first because first is now
        // the new node and its item is at the beginning
        this.first.next = oldFirst;
        // first can't have a previous node because it is the first node, so we it
        // to null
        this.first.prev = null;
        // as long as the old first node is not null, meaning there was a first node,
        if(oldFirst != null){
            // we set its previous node to the new first node since it is no longer
            // the first node in the linked list
            oldFirst.prev = this.first;
        }
        // if this is the first item that we are adding to the linked list as a whole
        if(this.isEmpty()){
            // the last node and the first node must point to the same node since
            // it is the first node in the list
            this.last = this.first;
        }
        this.n++;
    }

    // Adds item to the back of this deque.
    public void addLast(T item) {
        // if the item is empty, we throw a null pointer exception
        if(item == null){
            throw new NullPointerException("item is null");
        }
        // setting the old last node to the new last node
        Node oldLast = this.last;
        // setting the new last node to a new node
        this.last = new Node();
        // setting the item of the new last node to the item we want to add
        this.last.item = item;
        // since the new last node is the last node in the linked list
        //, it doesn't have a next node, so we set its next node to null
        this.last.next = null;
        // setting the new last node to the old last node because it is the new last
        // node in the linked list
        this.last.prev = oldLast;
        // as long as there was a last node previously
        if(oldLast != null){
            // we set the next node of the last node to the new last node because
            // it is the new last node in the linked list
            oldLast.next = this.last;
        }
        // we this is the first item that we are adding to the linked list
        if(this.isEmpty()){
            // the last node and the first node must point to the same node since
            // it is the first node in the list
            this.first = this.last;
        }
        this.n++;
    }

    // Returns the item at the front of this deque.
    public T peekFirst() {
        //if the linked list is empty before we can return the item of the first node
        if(this.isEmpty()){
            // we throw a no such element exception
            throw new NoSuchElementException("Deque is empty");
        }
        // we just return the item of the first node
        return this.first.item;
    }

    // Removes and returns the item at the front of this deque.
    public T removeFirst() {
        // if the linked list is empty before we can remove the first node's item
        if(this.isEmpty()){
            // then we throw an exception because there is nothing to remove
            throw new NoSuchElementException("Deque is empty");
        }
        // we create an item and set it to the item of the first node
        T item = this.first.item;
        // we remove the first node by setting it equal to its next node
        this.first = this.first.next;
        // checking if the first node is not null
        if(this.first != null){
            // then we set its previous node to null
            this.first.prev = null;
        }
        // we decrement n to show that we removed a node from the linked list
        this.n--;
        // if after removing the first node, the linked list becomes empty
        // we know that it was the only node in the linked list
        if(this.isEmpty()){
            // so we set the first node and the last node both to null
            this.first = null;
            this.last = null;
        }

        // just return the item itself
        return item;
    }

    // Returns the item at the back of this deque.
    public T peekLast() {
        // if the linked list is empty before we can return the item of the last node
        if(this.isEmpty()){
            // we throw a no such element exception
            throw new NoSuchElementException("Deque is empty");
        }
        // we just return the item of the last node
        return this.last.item;
    }

    // Removes and returns the item at the back of this deque.
    public T removeLast() {
        // if the linked list is empty before we can remove the last node's item
        if(this.isEmpty()){
            // then we throw an exception because there is nothing to remove
            throw new NoSuchElementException("Deque is empty");
        }
        // // we create an item and set it to the item of the last node
        T item = this.last.item;
        // we remove the last node by setting it equal to its previous node
        this.last = this.last.prev;
        // we check if the previous node of last is not null before we set last to it
        if(this.last != null){
            // we set its next node to null
            this.last.next = null;
        }
        // we decrement n to show that we are removing a node from the linked list
        this.n--;
        // if after we remove the last node from the linked list, the list is empty,
        if(this.isEmpty()){
            // we set both the first and last node to null
            this.first = null;
            this.last = null;
        }
        // just return the item itself
        return item;
    }

    // Returns an iterator to iterate over the items in this deque from front to back.
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    // Returns a string representation of this deque.
    public String toString() {
        String s = "";
        for (T item : this) {
            s += item + ", ";
        }
        return this.isEmpty() ? s + "[]" : "[" + s.substring(0, s.length() - 2) + "]";
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<T> {
        // instance variables
        private Node current; // reference to the current node

        // Constructs an iterator.
        public DequeIterator() {
            // initializing the current node to first because the current node
            // is always starts at the first node of the linked list
            this.current = first;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            // making sure that the current node is not null
            // otherwise, we can't move on
            return this.current != null;
        }

        // Returns the next item.
        public T next() {
            if(this.current == null){
                throw new NoSuchElementException("Iterator is empty");
            }
            // setting T item to the item of the current node
            T item = this.current.item;
            // advancing current to the next node
            this.current = this.current.next;
            // returning the item at the next node
            return item;
        }
    }

    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private T item;  // the item
        private Node next;  // the next node
        private Node prev;  // the previous node
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its several powers, having " +
                "been originally breathed into a few forms or into one; and that, whilst this " +
                "planet has gone cycling on according to the fixed law of gravity, from so simple" +
                " a beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        StdOut.println("Filling the deque...");
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.printf("The deque (%d characters): ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        StdOut.println("Emptying the deque...");
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println("deque.isEmpty()? " + deque.isEmpty());
    }
}
