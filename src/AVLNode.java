/**
 * Created by hinotohui on 18/5/18.
 */
package src;

public class AVLNode<V> {
    private V value;

    private AVLNode leftChild;
    private AVLNode rightChild;

    private AVLNode parent;

    private int height;

    public AVLNode(V value){
        this.value=value;
        this.height=1;
    }

    public void setLeftChild(AVLNode leftChild){
        this.leftChild=leftChild;
    }

    public void setRightChild(AVLNode rightChild){
        this.rightChild=rightChild;
    }

    public void setParent(AVLNode parent){
        this.parent=parent;
    }

    public AVLNode getLeftChild() {
        return leftChild;
    }

    public AVLNode getRightChild() {
        return rightChild;
    }

    public AVLNode getParent() {
        return parent;
    }

    public V getValue() {
        return value;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
