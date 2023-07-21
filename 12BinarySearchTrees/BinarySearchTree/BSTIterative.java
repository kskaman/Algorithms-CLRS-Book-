import java.util.Stack;

public class BSTIterative {

    private Node root;

    public BSTIterative() {
        root = null;
    }

    
    public void insert(int k) {

        Node newNode = new Node(k);

        Node current = root;
        Node  temp = null;
        while (current != null) {
            temp = current; 
            if (k < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = temp;

        if (temp == null) {
            root = newNode;
        } else if (temp.key > k) {
            temp.left = newNode;
        } else {
            temp.right = newNode;
        }
    }


    public void inorderTreeWalk() {

        System.out.println("Binary Tree (In-order) : ");

        /** Using Stack */
        // Node current = root;
        Node current = root;
        Stack<Node> stack = new Stack<>();

        while (current != null || stack.size() > 0) {
            
            // Reach the left most node of current node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            System.out.print(current.key + " ");

            current = current.right;
        }


        /** Without using stack */

        // /** Used Morris transversal */
        // Node current, prev = null;
        // current = root;

        // while (current != null) {
        //     if (current.left == null) {
        //         System.out.print(current.key + " ");
        //         current = current.right;
        //     } else {
        //         prev = current.left;
                    
        //         while (prev.right != null && prev.right != current) {
        //             prev = prev.right;
        //         }
                    
        //         if (prev.right == null) {
        //             prev.right = current;
        //             current = current.left;
        //         } else {
        //             prev.right = null;
        //             System.out.print(current.key + " ");
        //             current = current.right;
        //         }
        //     }
        // }

        /** Second method */
        // Node current = minimum(root);

        // while (current!= null) {
        //     System.out.print(current.key + " ");
        //     current = successor(current);
        // }


        System.out.println();
    }
    


    public void preorderTreeWalk() {
        
        System.out.println("Binary Tree (Pre-order) : ");

        /** Using Stack */
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node current = null;
        while (stack.size() > 0) {
            current = stack.pop(); 
            System.out.print(current.key + " ");
            if (current.right != null) stack.push(current.right);
            if (current.left != null)  stack.push(current.left);  
        }

        System.out.println();
    }


    public void postorderTreeWalk() {

        System.out.println("Binary Tree (Post-order) : ");

        /** Using Stack */
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        Stack<Node> out = new Stack<>();
        
        Node current = null;
        while (stack.size() > 0) {
            current = stack.pop(); 
            out.push(current);
            if (current.left != null)  stack.push(current.left);  
            if (current.right != null) stack.push(current.right);
        }

        while (!out.isEmpty()) {
            current = out.pop();
            System.out.print(current.key + " ");
        }
        System.out.println();

    }


    public Node search(int x) {
        Node current = root;

        while (current != null) {
            if (current.key == x) {
                return current;
            } else if (current.key < x) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }


    public Node minimum(Node x) {
        
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }


    public Node maximum(Node x) {
        
        while (x.right != null) {
            x = x.right;
        }
        return x;
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
        BSTIterative obj = new BSTIterative();

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