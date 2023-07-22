/******************************************************************************
 *  Compilation:    javac QueueResizingArray.java
 *  Execution:      java QueueResizingArray
 *  Dependencies:   None
 *  Data files:     None
 *
 *  A generic queue, implemented using an array.
 *
 *  % java Queue
 *  true
 *  count : 4
 *  count : 10
 *  false
 *
 *  Queue : 20 40 60 80 100 120 140 160 180 200 
 *  20
 *  40
 *
 *  Queue : 40 60 80 100 120 140 160 180 200
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
  
public class QueueResizingArray<T> implements Iterable<T> {
     
    private T[] arr;    // array representing queue
    private int first;  // index of element at fromt
    private int last;   // index of element at last
    private int MAX;
    private int count;  // number of elements on queue
  
    /**
     * Initializes an empty queue.
     */
    public QueueResizingArray() {
        MAX = 4;
        arr = (T[]) new Object[4];
        first = 0;
        last = 0;
        count = 0;
    }
  
    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return count == 0;
    }
  
    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
    */
    public int size() {
        return count;
    }

    // resize the underliyng array
    private void resize(int capacity) {
        assert capacity >= count;
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            copy[i] = arr[(first+i)%MAX];
        }
        arr = copy;
        first = 0;
        last = count;
    }
  
    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return arr[first];
    }
  
    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(T t) {
         
        if (count == MAX) resize(2*MAX);
         
        arr[last++] = t;
        count++;
 
        if (last == MAX) last = 0;
    }
  
    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public T dequeue() {
        if (first == last) throw new NoSuchElementException("Queue underflow.");
        T t = arr[first];
        arr[first] = null;
        count--;
        first++;
        if (first == MAX) first = 0;
        
        if (count > 0 && count == MAX/4) resize(MAX/2);

        return t;
    }
  
    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i =0; i < count; i++) {
            s.append(arr[(i + first)%MAX] + " ");
        }
        return s.toString();
    }
  
    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<T> iterator()  {
        return new ArrayIterator();
    }
  
    // a linked-list iterator
    private class ArrayIterator implements Iterator<T> {
  
        private int current;
         
        public ArrayIterator() {
            current = 0;
        }
  
        public boolean hasNext() {
            return current < count;
        }
  
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T t = arr[(current + first) % MAX];
            current++;
            return t;
        }
    }
  
  
    /**
     * Unit tests the {@code Queue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        QueueArray<Integer> queue = new QueueArray<Integer>(20);
  
        System.out.println(queue.isEmpty());
        queue.enqueue(20);
        queue.enqueue(40);
        queue.enqueue(60);
        queue.enqueue(80);
        System.out.println("count : " + queue.size());
        queue.enqueue(100);
        queue.enqueue(120);
        queue.enqueue(140);
        queue.enqueue(160);
        queue.enqueue(180);
        queue.enqueue(200);
        System.out.println("count : " + queue.size());
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
  