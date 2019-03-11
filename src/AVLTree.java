/**
 * Created by hinotohui on 18/5/18.
 */
package src;

public class AVLTree<V extends Comparable> {
    private AVLNode<V> root;

    public void insert(V value) throws Exception{
        if(root==null){
            root=new AVLNode<V>(value);
        }else {
            AVLNode<V> p=root;
            AVLNode<V> q=p;

            while (true){
                if(q==null)
                    break;

                p=q;

                if(value.compareTo(q.getValue())<0){
                    q=p.getLeftChild();
                }else if(value.compareTo(q.getValue())>0){
                    q=p.getRightChild();
                }else {
                    throw new Exception();
                }
            }

            AVLNode<V> avlNode=new AVLNode<V>(value);

            if (value.compareTo(p.getValue())<0){
                p.setLeftChild(avlNode);
            }else {
                p.setRightChild(avlNode);
            }

            q=p;
            avlNode.setParent(q);

            __rotate(q,false);
        }
    }

    public void remove(V value){
        AVLNode<V> p=find(value);
        AVLNode<V> q;

        if(p!=null){
            if(p.getLeftChild()==null&&p.getRightChild()==null){
                if (p==root){
                    root=null;
                    return;
                }else{
                    if(isLeftChild(p.getParent(),p)){
                        p.getParent().setLeftChild(p.getLeftChild());
                    }else {
                        p.getParent().setRightChild(p.getRightChild());
                    }
                    
                    q=p.getParent();
                }
            }else if(p.getLeftChild()!=null&&p.getRightChild()==null){
                if (p==root){
                    p.getLeftChild().setParent(p.getParent());
                    root=p.getLeftChild();
                    return;
                }else{
                    if(isLeftChild(p.getParent(),p)){
                        p.getParent().setLeftChild(p.getLeftChild());
                        p.getLeftChild().setParent(p.getParent());
                    }else {
                        p.getParent().setRightChild(p.getLeftChild());
                        p.getLeftChild().setParent(p.getParent());
                    }

                    q=p.getParent();
                }
            }else if(p.getLeftChild()==null&&p.getRightChild()!=null){
                if (p==root){
                    p.getRightChild().setParent(p.getParent());
                    root=p.getRightChild();
                    return;
                }else{
                    if(isLeftChild(p.getParent(),p)){
                        p.getParent().setLeftChild(p.getRightChild());
                        p.getRightChild().setParent(p.getParent());
                    }else {
                        p.getParent().setRightChild(p.getRightChild());
                        p.getRightChild().setParent(p.getParent());
                    }

                    q=p.getParent();
                }
            }else{
                q=p.getLeftChild();

                while (true){
                    if(q.getRightChild()==null) {
                        break;
                    }
                    q=q.getRightChild();
                }

                if(q.getParent()==p){
                    q.setRightChild(p.getRightChild());
                    p.getRightChild().setParent(q);

                    q.setParent(p.getParent());

                    if (p == root) {
                        root=q;
                    }else{
                        if (isLeftChild(p.getParent(), p)) {
                            p.getParent().setLeftChild(q);
                        } else {
                            p.getParent().setRightChild(q);
                        }
                    }
                }else {
                    AVLNode<V> r=q;

                    q = q.getParent();
                    q.setRightChild(r.getLeftChild());

                    r.setLeftChild(p.getLeftChild());
                    p.getLeftChild().setParent(r);

                    r.setRightChild(p.getRightChild());
                    p.getRightChild().setParent(r);

                    r.setParent(p.getParent());

                    if (p == root) {
                        root = r;
                    }else {
                        if (isLeftChild(p.getParent(), p)) {
                            p.getParent().setLeftChild(r);
                        } else {
                            p.getParent().setRightChild(r);
                        }
                    }

                    while (true) {
                        if (q == r)
                            break;
                        q.setHeight(max(height(q.getLeftChild()),
                                height(q.getRightChild())) + 1);
                        q = q.getParent();
                    }
                }
            }

            __rotate(q,true);
        }
    }

    public AVLNode<V> find(V value){
        AVLNode<V> p=root;

        while (true){
            if(p==null)
                return null;

            if(value.compareTo(p.getValue())<0){
                p=p.getLeftChild();
            }else if(value.compareTo(p.getValue())>0){
                p=p.getRightChild();
            }else {
                return p;
            }
        }
    }

    public boolean contains(V value){
        AVLNode<V> p=root;

        while (true){
            if(p==null)
                return false;

            if(value.compareTo(p.getValue())<0){
                p=p.getLeftChild();
            }else if(value.compareTo(p.getValue())>0){
                p=p.getRightChild();
            }else {
                return true;
            }
        }
    }

    private boolean isLeftChild(AVLNode parent,AVLNode child){
        return parent.getLeftChild()==child;
    }

    private int max(int leftHeight,int rightHeight){
        return leftHeight>=rightHeight?leftHeight:rightHeight;
    }

    private int height(AVLNode avlNode){
        if(avlNode==null)
            return 0;
        return avlNode.getHeight();
    }

    private AVLNode<V> leftRotate(AVLNode p,AVLNode q){

        if(q!=root){
            if(isLeftChild(q.getParent(),q)) {
                q.getParent().setLeftChild(p);
            }else {
                q.getParent().setRightChild(p);
            }
        }else{
            root=p;
        }

        p.setParent(q.getParent());
        q.setLeftChild(p.getRightChild());

        p.setRightChild(q);
        q.setParent(p);

        q.setHeight(max(height(q.getLeftChild()),height(q.getRightChild())) + 1);
        p.setHeight(max(height(p.getLeftChild()),height(p.getRightChild())) +1);

        return p;
    }

    private AVLNode<V> leftRightRotate(AVLNode p,AVLNode q){
        AVLNode rightChild=p.getRightChild();

        if(q!=root){
            if(isLeftChild(q.getParent(),q)) {
                q.getParent().setLeftChild(rightChild);
            }else {
                q.getParent().setRightChild(rightChild);
            }
        }else{
            root=rightChild;
        }

        rightChild.setParent(q.getParent());

        p.setRightChild(rightChild.getLeftChild());
        q.setLeftChild(rightChild.getRightChild());

        rightChild.setLeftChild(p);
        p.setParent(rightChild);

        rightChild.setRightChild(q);
        q.setParent(rightChild);

        q.setHeight(max(height(q.getLeftChild()), height(q.getRightChild())) + 1);
        p.setHeight(max(height(p.getLeftChild()),height(p.getRightChild())) +1);
        rightChild.setHeight(max(height(rightChild.getLeftChild()),height(rightChild.getRightChild())) + 1);

        return rightChild;
    }

    private AVLNode<V> rightRotate(AVLNode p,AVLNode q){

        if(q!=root){
            if(isLeftChild(q.getParent(),q)) {
                q.getParent().setLeftChild(p);
            }else {
                q.getParent().setRightChild(p);
            }
        }else{
            root = p;
        }

        p.setParent(q.getParent());
        q.setRightChild(p.getLeftChild());
        p.setLeftChild(q);
        q.setParent(p);

        q.setHeight(max(height(q.getLeftChild()),height(q.getRightChild())) + 1);
        p.setHeight(max(height(p.getLeftChild()),height(p.getRightChild())) +1);

        return p;
    }

    private AVLNode<V> rightLeftRotate(AVLNode p,AVLNode q){
        AVLNode leftChild=p.getLeftChild();

        if(q!=root){
            if(isLeftChild(q.getParent(),q)) {
                q.getParent().setLeftChild(leftChild);
            }else {
                q.getParent().setRightChild(leftChild);
            }
        }else{
            root=leftChild;
        }

        leftChild.setParent(q.getParent());

        p.setLeftChild(leftChild.getRightChild());
        q.setRightChild(leftChild.getLeftChild());

        leftChild.setRightChild(p);
        p.setParent(leftChild);

        leftChild.setLeftChild(q);
        q.setParent(leftChild);

        q.setHeight(max(height(q.getLeftChild()), height(q.getRightChild())) + 1);
        p.setHeight(max(height(p.getLeftChild()),height(p.getRightChild())) +1);
        leftChild.setHeight(max(height(leftChild.getLeftChild()),height(leftChild.getRightChild())) + 1);

        return leftChild;
    }

    private void __rotate(AVLNode<V> q,boolean lookBack){
        while (true){
            if (q==null)
                break;

            int leftHeight=height(q.getLeftChild());
            int rightHeight=height(q.getRightChild());

            q.setHeight((max(leftHeight,rightHeight))+1);

            if(!lookBack) {
                if (leftHeight == rightHeight)
                    break;
            }

            if (rightHeight-leftHeight<-1){
                if(height(q.getLeftChild().getRightChild())-height(q.getLeftChild().getLeftChild())==-1
                        ||height(q.getLeftChild().getRightChild())-height(q.getLeftChild().getLeftChild())==0){
                    leftRotate(q.getLeftChild(),q);
                }else{
                    leftRightRotate(q.getLeftChild(), q);
                }

                if(!lookBack)
                    break;
            }else if(rightHeight-leftHeight>1){
                if(height(q.getRightChild().getRightChild())-height(q.getRightChild().getLeftChild())==1
                        ||height(q.getRightChild().getRightChild())-height(q.getRightChild().getLeftChild())==0) {
                    rightRotate(q.getRightChild(), q);
                }else {
                    rightLeftRotate(q.getRightChild(), q);
                }

                if(!lookBack)
                    break;
            }

            q=q.getParent();
        }
    }

    public void traverse(){
        if(root==null)
            return;

        __traverse(root,null);
    }

    private void __traverse(AVLNode<V> p,String s){
        if(p==null)
            return;

        if(s!=null)
            System.out.println(s);
        System.out.println(p.getValue());

        __traverse(p.getLeftChild(),"left");
        __traverse(p.getRightChild(),"right");
    }
}
