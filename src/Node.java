/**********************************************************************
 * @file Node.java
 * @brief This program implements the Node class. It checks the value of
 * every node and checks if the node has any left or right children.
 * @author Blythe Greene
 * @date: September 25, 2024
 ***********************************************************************/
public class Node<T extends Comparable<T>> implements BinNode<T>{
    private T element;//generic type
    //Variables for left and right nodes.
    private Node<T> right;
    private Node<T> left;

    //Implement the default constructor
    public Node() {
        right = null;
        left = null;
    }

    //Constructor with value
    public Node(T v) {
        right = null;
        left = null;
        element = v;
    }

    //Constructor for right and left nodes
    public Node(T v, Node<T> r, Node<T> l) {
        this.left = l;
        this.right = r;
        this.element = v;//set value
    }

    // Implement the setElement method to set value to value
    public void setElement(T value) {
        element = value;
    }

    // Implement the getElement method to get the value
    public T getElement() {
        return element;
    }

    // Implement the setRight method
    // Sets right node
    public void setRight(Node<T> r) {
        right = r;
    }

    // Implement the getRight method to get right node
    public Node<T> getRight() {
        return right;
    }

    // Implement the setLeft method
    // Sets left node
    public void setLeft(Node<T> l) {
        left = l;
    }

    // Implement the getLeft method to get left node
    public Node<T> getLeft() {
        return left;
    }


    // Implement the isLeaf method
    // Checks if the node has a leaf
    public boolean isLeaf() {
        return (this.right == null) && (this.left == null);
    }
}

//Binary tree node ADT
interface BinNode<T extends Comparable<T>>{
    //get and set the element value
    public T getElement();
    public void setElement(T v);
    //return the children
    public BinNode<T> getLeft();
    public BinNode<T> getRight();
    //return True if a leaf node, False otherwise
    public boolean isLeaf();
}

