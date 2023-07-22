/******************************************************************************
 *  Compilation:    javac Queue.java
 *  Execution:      java Queue
 *  Dependencies:   None
 *  Data files:     None
 *
 *  A generic queue, implemented using a link class.
 *
 *  % java Queue
 *  true
 * false
 *
 * Queue : 20 40 60 80 100 120 140 160 180 200 
 * 20
 * 40
 *
 * Queue : 40 60 80 100 120 140 160 180 200
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
    private Node<T> first;    // beginning of queue
    private Node<T> last;     // end of queue
    private int count;               // number of elements on queue

    // helper linked list class
    private static class Node<T> {
        private T data;
        private Node<T> next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
        count = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return count;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.data;
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(T t) {
        Node<T> oldlast = last;
        last = new Node<T>();
        last.data = t;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        count++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T t = first.data;
        first = first.next;
        count--;
        if (isEmpty()) last = null;
        return t;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<T> current = first;
        while (current != null) {
            s.append(current.data + " ");
            current = current.next;
        }
        return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<T> iterator()  {
        return new QueueIterator(first);
    }

    // a linked-list iterator
    private class QueueIterator implements Iterator<T> {
        private Node<T> current;

        public QueueIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T t = current.data;
            current = current.next;
            return t;
        }
    }


    /**
     * Unit tests the {@code Queue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();

        System.out.println(queue.isEmpty());
        queue.enqueue(20);
        queue.enqueue(40);
        queue.enqueue(60);
        queue.enqueue(80);
        queue.enqueue(100);
        queue.enqueue(120);
        queue.enqueue(140);
        queue.enqueue(160);
        queue.enqueue(180);
        queue.enqueue(200);
        System.out.println(queue.isEmpty());
        System.out.print("\nQueue : ");
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.peek());

        System.out.print("\nQueue : ");
        for (Integer x: queue) {
            System.out.print(x + " ");
        }
    }
}
