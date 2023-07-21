public class IntList {
    public Node head;
    private int count;

    public IntList() {
        head = null;
        count = 0;
    }

    public void insert(int d) {
        Node newNode = new Node(d);
        newNode.next = head;
        head = newNode;
        count++;
    }

    public String toString() {
        Node current = head;

        String str = "";
        int[] arr = new int[count];
        int i = 0;
        arr[i] = current.key;

        while (current.next != null) {
            i++;
            current = current.next;
            arr[i] = current.key;
            
        }

        for (int j = count-1; j >= 0; j--) {
            str += String.valueOf(arr[j] + " ");
        }

        return str;
    }
}
