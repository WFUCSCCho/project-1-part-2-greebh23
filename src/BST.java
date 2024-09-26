/**********************************************************************
 * @file BST.java
 * @brief This program implements the BSTIterator and BST classes. The iterator class
 * iterates over the tree and the BST class alters the tree.
 * @author Blythe Greene
 * @date: September 26, 2024
 ***********************************************************************/
import java.util.Stack;

public class BST<T extends Comparable<T>> {
    // root is of a generic type
    private Node<T> root;
    private int numNode;//number of nodes currently

    //implement the constructor
    public BST() {
        //set default values
        root = null;
        numNode = 0;
    }

    public int size() {
        return numNode;
    }

    // Implement the clear method
    public void clear() {
        //update size and remove elements
        root = null;
        numNode = 0;
    }

    // Implement the insert method to insert value
    // passed in as the parameter.
    public void insert(T value) {
        root = inserthelp(root, value);//root updated to new Node
        numNode++;//update to new Node
    }

    //Insert method calls this method to insert the value.
    private Node<T> inserthelp(Node<T> rt, T key) {
        //Is the node null?
        if(rt == null || rt.getElement() == null) {
            return new Node<T>(key);
        }
        //compare node value and the key
        if(rt.getElement().compareTo(key) > 0) {
            rt.setLeft(inserthelp(rt.getLeft(), key));//update left node
        }
        else {
            rt.setRight(inserthelp(rt.getRight(), key));//update right node
        }
        return rt;
    }

    // Implement the remove method to get rid
    // of a node from the tree.
    public T remove(T key) {
        //Find the key
        T temp = findhelp(root, key);
        if(temp != null) {
            //Remove the key if found
            root = removehelp(root, key);
            numNode--;//update current node
        }
        return temp;
    }

    //Recursively removes the key.
    private Node<T> removehelp(Node<T> rt, T key) {
        if(rt == null)
            return null;
        //check if in right or left subtree
        //compare the key with the current node.
        if(rt.getElement().compareTo(key) > 0) {
            rt.setLeft(removehelp(rt.getLeft(), key));
        }
        else if(rt.getElement().compareTo(key) < 0) {
            rt.setRight(removehelp(rt.getRight(), key));
        }
        else {
            //Found key. Remove it.
            //case 1: leaf node
            if(rt.getLeft() == null && rt.getRight() == null ) {
                return null;
            }
            //does the node have one child?
            if(rt.getLeft() == null) {
                return rt.getRight();
            }
            else if(rt.getRight() == null) {
                return rt.getLeft();
            }
            else {
                //The node has 2 children. Replace the node value with
                //the smallest value in the right subtree. Then delete the node.
                Node<T> temp = getMin(rt.getRight());
                rt.setElement(temp.getElement());
                rt.setRight(removehelp(rt.getRight(), temp.getElement()));
            }
        }
        return rt;
    }

    //Find the node with the smallest data located in the right subtree.
    public Node<T> getMin(Node<T> right) {
        while(right.getLeft() != null) {
            right = right.getLeft();
        }
        return right;
    }

    //Implement the search method to help find the key.
    public T search(T key) {
        return findhelp(root, key);
    }

    //This methods finds the key.
    private T findhelp(Node<T> rt, T key) {
        if(rt == null || rt.getElement() == null) {
            return null;
        }
        //If the key not equal to node, go to the right subtree.
        //Otherwise, go to left.
        if(rt.getElement().compareTo(key) > 0) {
            return findhelp(rt.getLeft(), key);
        }
        else if(rt.getElement().compareTo(key) == 0) {
            return rt.getElement();
        }
        else
            return findhelp(rt.getRight(), key);
    }


    // Implement the iterator method
    // This method returns the contents of the tree.
    public String iterator() {
        BSTIterator iterate = new BSTIterator(root);
        String str = "";
        T element;
        while(iterate.hasNext() == true) {
            element = iterate.next();
            str += element.toString();
            if(iterate.hasNext() == true) {
                str += "\n";
            }
        }
        return str;
    }

    // Implement the BSTIterator class
    // Iterates through the tree.
    class BSTIterator{
        Stack<Node<T>> stack;//Stores node

        //Constructor
        public BSTIterator(Node<T> root) {
            this.stack = new Stack<Node<T>>();
            // Call to the helper function with the root node
            this.inOrder(root);
        }

        // This method checks if there is another node.
        public boolean hasNext() {
            return stack.size() >= 1;
        }

        //Returns the value of the next node.
        public T next() {
            Node<T> root = this.stack.pop();
            if(root.getRight() != null) {
                this.inOrder(root.getRight());
            }
            return root.getElement();
        }

        //Pushes values into the stack.
        public void inOrder(Node<T> root) {
            while(root != null) {
                this.stack.push(root);
                root = root.getLeft();
            }
        }
    }
}
//Interface for BST class
interface Iterator<T extends Comparable<T>>{
    public T next();
    public boolean hasNext();
    public void inOrder(Node<T> root);
}





