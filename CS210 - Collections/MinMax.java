import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class MinMax {
    // Returns the minimum value in the given linked list.
    public static int min(Node first) {
        // setting an int variable to the MAX integer variable possible
        int min = Integer.MAX_VALUE;
        // looping through the linked list
        for(Node x = first; x!= null; x = x.next){
            // setting min to the smallest item in the linked list
            if(x.item < min){
                min = x.item;
            }
        }
        return min;
    }

    // Returns the maximum value in the given linked list.
    public static int max(Node first) {
        // setting max to the smallest number possible
        int max = Integer.MIN_VALUE;
        // looping through the linked list
        for(Node x = first; x!= null; x = x.next){
            // setting max to the largest item in the linked list
            if(x.item > max){
                max = x.item;
            }
        }
        return max;
    }

    // A data type to represent a linked list. Each node in the list stores an integer item and a
    // reference to the next node in the list.
    protected static class Node {
        protected int item;  // the item
        protected Node next; // the next node
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        int[] items = new int[1000];
        for (int i = 0; i < 1000; i++) {
            items[i] = StdRandom.uniform(-10000, 10000);
        }
        Node first = null;
        for (int item : items) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
        StdOut.println("min(first) == StdStats.min(items)? " + (min(first) == StdStats.min(items)));
        StdOut.println("max(first) == StdStats.max(items)? " + (max(first) == StdStats.max(items)));
    }
}
