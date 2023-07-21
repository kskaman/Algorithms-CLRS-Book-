import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackResizingArray<T> implements Iterable<T> {

    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 4;

    private T[] arr;         // array of items
    private int count;            // number of elements on stack


    /**
     * Initializes an empty stack.
     */
    public StackResizingArray() {
        arr = (T[]) new Object[INIT_CAPACITY];
        count = 0;
    }

    /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
        return count;
    }


    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= count;

        // textbook implementation
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }



    /**
     * Adds the item to this stack.
     * @param item the item to add
     */
    public void push(T t) {
        if (count == arr.length) resize(2*arr.length);
        arr[count++] = t;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        T t = arr[count-1];
        arr[count-1] = null;
        count--;
        
        // shrink size of array if necessary
        if (count > 0 && count == arr.length/4) resize(arr.length/2);
        return t;
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return arr[count-1];
    }


    /**
     * Returns a String containing each element of Stack in LIFO order
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = count-1; i >= 0; i--) {
                s.append(arr[i] + " ");
        }
        return s.toString();
    }


    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates through the items in LIFO.
     */
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    // a array iterator
    private class ArrayIterator implements Iterator<T> {
        private int i;

        public ArrayIterator() {
            i = count-1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[i--];
        }
    }


    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        StackResizingArray<Integer> stack = new StackResizingArray<Integer>();

        System.out.println(stack.isEmpty());
        stack.push(20);
        stack.push(11);
        stack.push(34);
        stack.push(45);
        stack.push(67);
        System.out.println(stack.isEmpty());
        System.out.print("\nStack : ");
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.peek());

        System.out.print("\nStack : ");
        for (Integer x: stack) {
            System.out.print(x + " ");
        }
    }
}

