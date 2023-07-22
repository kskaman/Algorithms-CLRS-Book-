
/**************************************************************************************
 * Compilation:     javac Stack.java 
 * Execution:       java Stack
 * Dependencies:    None
 * Data Files:      None
 * @author Kamanpreet Singh Manoor
 * A generic stack, implemented using an Link class . Each stack element is of certain type.
 * 
 * % java Stack
 * true
 * false
 *
 * Stack : 67 45 34 11 20
 * 67
 * 45

 * Stack : 45 34 11 20
 **************************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Stack<Item> implements Iterable<Item> {
    private Link<Item> top;     // top of stack
    private int n;                // size of the stack

    // helper link class
    private static class Link<Item> {
        private Item data;
        private Link<Item> next;
    }

    /**
     * Initializes an empty stack.
     */
    public Stack() {
        top = null;
        n = 0;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this stack.
     *
     * @param  item the item to add
     */
    public void push(Item item) {
        Link<Item> newLink = new Link<Item>();
        newLink.data = item;
        newLink.next = top;
        top = newLink;
        n++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item d = top.data;        // save item to return
        top = top.next;            // delete first node
        n--;
        return d;                   // return the saved item
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return top.data;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(top);
    }

    // the iterator
    private class LinkedIterator implements Iterator<Item> {
        private Link<Item> current;

        public LinkedIterator(Link<Item> first) {
            current = first;
        }

        // is there a next item?
        public boolean hasNext() {
            return current != null;
        }

        // returns the next item
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.next;
            return item;
        }
    }


    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
                  
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
