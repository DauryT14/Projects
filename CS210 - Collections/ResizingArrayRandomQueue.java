import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<T> implements Iterable<T> {
    // instance variables
    private int n;
    private T[] q;

    // Constructs an empty random queue.
    @SuppressWarnings("unchecked")
    public ResizingArrayRandomQueue() {
        // initializing the instance variables
        this.n = 0;
        this.q = (T[]) new Object[2];
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        return size() == 0;
    }

    // Returns the number of items in this queue.
    public int size() {
        return this.n;
    }

    // Adds item to the end of this queue.
    public void enqueue(T item) {
        // if item is null, we throw a Null pointer exception
        if(item == null){
            throw new NullPointerException("item is null");
        }
        // if n is the same size as q
        if(this.n == this.q.length){
            // resize q to twice its length
            resize(2*this.q.length);
        }
        // inserting the given item at index n
        this.q[this.n++] = item;
    }

    // Returns a random item from this queue.
    public T sample() {
        // if the random queue is empty, we throw a no such element exception
        if(this.size() == 0){
            throw new NoSuchElementException("Random queue is empty");
        }
        // Getting a random int r from 0 to n
        int r = StdRandom.uniform(0,this.n);
        // returning the rth element in q
        return this.q[r];
    }

    // Removes and returns a random item from this queue.
    public T dequeue() {
        // if the random queue is empty, we throw a no such element exception
        if(this.size() == 0){
            throw new NoSuchElementException("Random queue is empty");
        }
        // creating a random integer r like in the sample method from 0 to n
        int r = StdRandom.uniform(0,this.n);
        // setting item to the element in q at index r
        T item = this.q[r];
        // setting q[r] to q[n-1] and then setting q[n-1] to null
        this.q[r] = this.q[n-1];
        this.q[n-1] = null;
        this.n--;
        // if q is at quarter capacity
        if(this.n == this.q.length/4){
            // we resize it to half its capacity
            resize(this.q.length/2);
        }
        return item;
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<T> iterator() {
        return new RandomQueueIterator();
    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<T> {
        // instance variables
        private T[] items;
        private int current;

        // Constructs an iterator.
        @SuppressWarnings("unchecked")
        public RandomQueueIterator() {
            this.items = (T[]) new Object[n];
            // copying the n items from q into items
            for(int i = 0; i < n; ++i){
                this.items[i] = q[i];
            }

            // shuffling items using StdRandom
            StdRandom.shuffle(this.items);

            // initializing current
            this.current = 0;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            // continue iterating as long as the current index is less than the
            // size of the items array
            return this.current < this.items.length;
        }

        // Returns the next item.
        public T next() {
            // if there are no more items to iterate over
            if(!hasNext()){
                // we throw a no such element exception
                throw new NoSuchElementException("Iterator is empty");
            }
            // setting item to the current item in items
            T item = this.items[this.current];
            // advancing to the next item
            this.current++;
            return item;
        }
    }

    // Resizes the underlying array.
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(10000);
            q.enqueue(r);
            sum += r;
        }
        int iterSumQ = 0;
        for (int x : q) {
            iterSumQ += x;
        }
        int dequeSumQ = 0;
        while (q.size() > 0) {
            dequeSumQ += q.dequeue();
        }
        StdOut.println("sum       = " + sum);
        StdOut.println("iterSumQ  = " + iterSumQ);
        StdOut.println("dequeSumQ = " + dequeSumQ);
        StdOut.println("iterSumQ + dequeSumQ == 2 * sum? " + (iterSumQ + dequeSumQ == 2 * sum));
    }
}
