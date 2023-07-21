public class BSTRecursive {

    private Node root;

    public BSTRecursive() {
        root = null;
    }


    public void insert(int k) {
        Node newNode = new Node(k);
        Node temp = null;
        if (root != null) temp = insertAux(root, k);
        newNode.parent = temp;

        if (temp == null) {
            root = newNode;
        } else if (temp.key > k) {
            temp.left = newNode;
        } else {
            temp.right = newNode;
        }
    }

    private Node insertAux(Node n, int k) {
    
        if (n.key > k) {
            if (n.left == null) return n;
            return insertAux(n.left, k);
        } else {
            if (n.right == null) return n;
            return insertAux(n.right, k);
        }
    }

    public void inorderTreeWalk() {
        System.out.println("Binary Tree (In-order) : ");
        inorderTreeWalkAux(root);
        System.out.println();
    }

    private void inorderTreeWalkAux(Node current) {
        if (current == null) return;

        inorderTreeWalkAux(current.left);
        System.out.print(current.key + " ");
        inorderTreeWalkAux(current.right);
    }


    public void preorderTreeWalk() {
        System.out.println("Binary Tree (Pre-order) : ");
        preorderTreeWalkAux(root);
        System.out.println();
    }

    private void preorderTreeWalkAux(Node current) {
        if (current == null) return;

        System.out.print(current.key + " ");
        preorderTreeWalkAux(current.left);
        preorderTreeWalkAux(current.right);
    }


    public void postorderTreeWalk() {
        System.out.println("Binary Tree (Post-Order) : ");
        postorderTreeWalkAux(root);
        System.out.println();
    }

    private void postorderTreeWalkAux(Node current) {
        if (current == null) return;

        postorderTreeWalkAux(current.left);
        postorderTreeWalkAux(current.right);
        System.out.print(current.key + " ");
    }


    public Node search(int x) {
        return searchAux(root, x);
    }

    private Node searchAux(Node n, int x) {
        if (n == null) {
            return null;
        }
        if (n.key == x) {
            return n;
        } else if (n.key < x) {
            return searchAux(n.right, x);
        } else {
            return searchAux(n.left, x);
        }
    }


    public Node minimum(Node x) {
        
        if (x.left == null) {
            return x;
        }
        return minimum(x.left);
    }

 
    public Node maximum(Node x) {
        
        if (x.right == null) {
            return x;
        }
        return maximum(x.right);
    }

    public Node successor(Node x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node predecessor(Node x) {
        if (x.left != null) {
            return maximum(x.left);
        }
        Node y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }


    public void delete(int i) {

        Node z = search(i);
        if (z == null) {
            System.out.println("Could not find Node with key " + i +".");
            return;
        }


        if (z.left == null) {
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
            Node y = minimum(z.right);
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    private void transplant(Node u, Node v) {

        if (u.parent == null) {
            root = v;
        } else if (u.parent.left == u) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null)    v.parent = u.parent;
    }


    /** Test function */
    public static void main(String[] args) {
        BSTRecursive obj = new BSTRecursive();

        obj.insert(12);
        obj.insert(5);
        obj.insert(9);
        obj.insert(2);
        obj.insert(18);
        obj.insert(15);
        obj.insert(13);
        obj.insert(17);
        obj.insert(19);

        obj.inorderTreeWalk();

        obj.insert(15);

        obj.inorderTreeWalk();
        obj.preorderTreeWalk();
        obj.postorderTreeWalk();
        System.out.println("Node with largest key in subtree rooted at 12 : " + obj.maximum(obj.search(12)).key);
        System.out.println("Node with smallest key in subtree rooted at 12 : " + obj.minimum(obj.search(12)).key);
        
        System.out.println("Node with largest key in subtree rooted at 18 : " + obj.maximum(obj.search(18)).key);
        System.out.println("Node with smallest key in subtree rooted at 18 : " + obj.minimum(obj.search(18)).key);

        System.out.println("Predecessor of 13 : " + obj.predecessor(obj.search(13)).key);
        System.out.println("Successor of 17 : " + obj.successor(obj.search(17)).key);

        obj.delete(15);
        obj.delete(15);
        obj.inorderTreeWalk();
        obj.insert(15);
        obj.inorderTreeWalk();
    }
}