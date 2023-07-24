/******************************************************************************
 *  Compilation:    javac LinkedList.java
 *  Execution:      java LinkedList
 *  Dependencies:   None
 *  Data files:     None
 *  @author Kamanpreet Singh Manoor
 *
 *  % java LinkedList
 * true
 * 3
 * false
 * 6
 * My name is Kamanpreet Sinh Manoor
 * 'Kaman' is not present in the list.
 * 
 * My name is Kaman Preet Singh Manoor
 * My name is Kaman Preet Singh Manoor
 * 
 * My name is Kaman Preet
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private Link<T> head;    // Link at the beginning of the list
    private int count;    // number of elements in List

    // helper Link class
    private static class Link<T> {
        private T key;
        private Link<T> next;

        public Link(T t) {
            next = null;
            key = t;
        }
    }

    // initializes an empty list
    public LinkedList() {
        head = null;
        count = 0;
    }

    /**
     * Returns number of elements in list
     * @return number of elements
     */
    public int size() {
        return count;
    }

    /**
     * Returns {@code true} is list is empty otherwise {@code false}
     * @return {@code true} is list is empty otherwise {@code false}
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Insert a Link in List at the end of list
     * @param the key to be inserted
     */
    public void insert(T t) {
    
        Link<T> newLink = new Link<>(t);
        count++;
        if (head == null) {
            head = newLink;
            return;
        }
        Link<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newLink;
    }

    /**
     * Insert the Link at place i from head
     * @param index
     */
    public void insert(int index, T t) {
        if (index >= count) throw new NoSuchElementException("Index out of range");
        Link<T> current = head;
        Link<T> newLink = new Link<>(t);
        if (index == 0) {
            newLink.next = head;
            head = newLink;
            return;
        }

        Link<T> prev = head;
        current = head.next;
        for (int i = 1; i < index; i++) {
            prev = current;
            current = current.next;
        }
        
        prev.next = newLink;
        newLink.next = current;
    }


    /**
     * Replace the Link with key x to key y
     * @param index
     */
    public void replace(T x, T y) {
        Link<T> newLink = new Link<>(y);
        if (head.key == x) {
            head.key = y;
        }

        Link<T> prev = head;
        Link<T> current = head.next;
        while (current != null) {
            if (current.key == x) {
                break;
            }
            prev = current;
            current = current.next;
        }
        prev.next = newLink;
        newLink.next = current.next;
    }


    /**
     * Replace the Link at place 'index' from head
     * @param index
     */
    public void replace(int index, T t) {
        if (index >= count) throw new NoSuchElementException("Index out of range");
        Link<T> current = head;
        Link<T> newLink = new Link<>(t);
        if (index == 0) {
            newLink.next = head.next;
            head = newLink;
            return;
        }

        for (int i = 1; i < index; i++) {
            current = current.next;
        }

        
        newLink.next = current.next.next;
        current.next = newLink;
    }


    /**
     * Returns index of first occurrence of given key.
     * @param t
     * @return index of first occurrenc of given key, if present
     *         otherwise -1.
     */
    public int search(T t) {
        Link<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.key == t) 
            return index;;
            current = current.next;
            index++;
        }
        return -1;
    }
    
    /**
     * Returns the element at the required index
     * or null if index is out of range
     * (grater than number of elments in list)
     * @return key of Link at given index
     * @throws NoSuchElementException if index out of range 
     */
    public T search(int index) {
        if (index >= count) throw new NoSuchElementException("Index out of range"); 
        Link<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
        }
        return current.key;
    }

    /**
     * Delete the first ocurrence of Link with the given key, if present
     * @param t the key to be delete
     */
    public void delete(T t) {
        if (count == 0) throw new NoSuchElementException("List is empty. Cannot delete from an empty list.");

        count--;
        if (head.key == t) {
            head = head.next;
            return;
        }

        Link<T> current = head.next;
        Link<T> prev = head;
        while (current != null) {
            if (current.key == t) {
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }
    }


    /**
     * Deletes the Link at given index
     * @param index
     */
    public void delete(int index) {
        if (index >= count) throw new NoSuchElementException("Index put of range.");

        count--;
        if (index == 0) {
            head = head.next;
            return;
        }

        Link<T> current = head.next;
        Link<T> prev = head;

        for (int i = 1; i < index; i++) {
            prev = current;
            current = current.next;
        }
        prev.next = current.next;
    }

    /**
     * Returns a string representation of this LinkedList.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Link<T> current = head;
        
        while (current != null) {
            sb.append(current.key + " ");
            current = current.next;
        }
        return sb.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<T> iterator() {
        return new ListIterator(head);
    }

    private class ListIterator implements Iterator<T> {
        private Link<T> current;
        
        public ListIterator(Link<T> head) {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T t = current.key;
            current = current.next;
            return t;
        }
    }


    // Test function
    public static void main(String[] args) {
        LinkedList<String> name = new LinkedList<>();

        System.out.println(name.isEmpty());

        name.insert("My");
        name.insert("name");
        name.insert("is");

        System.out.println(name.size());

        name.insert("Kamanpreet");
        name.insert("Sinh");
        name.insert("Manoor");

        System.out.println(name.isEmpty());
        System.out.println(name.size());

        System.out.println(name);
        name.replace("Sinh", "Singh");
        int i = name.search("Kaman");
        if (i == -1) {
            System.out.println("'Kaman' is not present in the list.");
        }

        i = name.search("Kamanpreet");
        if (i == -1) {
            System.out.println("'Kamanpreet' is not present in the list.");
        } else {
            name.replace(i, "Kaman");
        }
        name.insert(i+1, "Preet");

        System.out.println();
        for (String s : name) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println(name);
        name.delete(i+2);
        name.delete("Manoor");

        System.out.println();
        for (String s : name) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
