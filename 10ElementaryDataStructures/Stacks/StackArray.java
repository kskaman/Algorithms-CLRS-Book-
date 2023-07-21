/**************************************************************************************
 * Compilation:     javac StackArray.java 
 * Execution:       java StackArray
 * Dependencies:    None
 * Data Files:      None
 * @author Kamanpreet Singh Manoor
 * A generic stack, implemented using an array. Each stack element is of certain type.
 * 
 * % java StackArray
 * true
 * false
 *
 * Stack : 67 45 34 11 20
 * 67
 * 45

 * Stack : 45 34 11 20
 **************************************************************************************/

import java.util.NoSuchElementException;
import java.util.Iterator;

public class StackArray<T> implements Iterable<T> {
    
    private T[] arr;      // array stores emeents of type 'T'
    private int count;    // number of elements in array

    public StackArray(int n) {
        arr = (T[]) new Object[n];
        count = 0;
    }
 
    /**
     * Checks whether stack is empty or not
     * @return {@code true} if stack is empty otherwise {@code false}
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * inserts an element to the top of Stack
     * @param t element to be inserted
     */
    public void push(T t) {
        if (count == arr.length) {
            throw new NoSuchElementException("Stack Overflow.");
        }
        arr[count++] = t;
    }

    /**
     * Deletes the elemnt at the top of Stack and returns it
     * @return element at the top of Stack
     */
    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack Underflow.");
        return arr[--count];
    }

    /**
     * Returns the element at the top of Stack
     * @return element at top of Stack
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack Underflow.");
        return arr[count-1];
    }

    /**
     * Returns the number of elements in the Stack
     * @return number of elements in the Stack
     */
    public int size() {
        return count-1;
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
     * Returns an iterator to this statck that iterates through the items.
     * @return an iterator to this stack 
     */
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    // an array iterator
    private class ArrayIterator implements Iterator<T> {
        private int i = count-1;

        public boolean hasNext() {
            return i >= 0;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T t = arr[i];
            i--;
            return t;
        }
    }


    /** Test function */
    public static void main(String[] args) {
        StackArray<Integer> stack = new StackArray<Integer>(20);

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
