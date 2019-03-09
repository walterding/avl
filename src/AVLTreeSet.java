/**
 * Created by hinotohui on 18/5/18.
 */
public class AVLTreeSet<V extends Comparable> {
    private AVLTree<V> avlTree;

    public AVLTreeSet(){
        avlTree=new AVLTree<V>();
    }

    public void add(V value){
        try {
            avlTree.insert(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(V value){
        avlTree.remove(value);
    }

    public void traverse(){
        avlTree.traverse();
    }

    public boolean contains(V value){
        return avlTree.contains(value);
    }

    public static void main(String[] args){
        AVLTreeSet<Integer> avlTreeSet=new AVLTreeSet<Integer>();

        avlTreeSet.add(new Integer(100));
        avlTreeSet.add(new Integer(80));
        avlTreeSet.add(new Integer(150));
        avlTreeSet.add(new Integer(40));
        avlTreeSet.add(new Integer(90));
        avlTreeSet.add(new Integer(140));
        avlTreeSet.add(new Integer(160));
        avlTreeSet.add(new Integer(60));
        avlTreeSet.add(new Integer(85));
        avlTreeSet.add(new Integer(91));
        avlTreeSet.add(new Integer(130));
        avlTreeSet.add(new Integer(141));
        avlTreeSet.add(new Integer(159));
        avlTreeSet.add(new Integer(170));
        avlTreeSet.add(new Integer(92));
        avlTreeSet.add(new Integer(142));
        avlTreeSet.add(new Integer(158));
        avlTreeSet.add(new Integer(165));
        avlTreeSet.add(new Integer(180));
        avlTreeSet.add(new Integer(190));


        avlTreeSet.traverse();
        avlTreeSet.remove(new Integer(80));
        System.out.println("----------");
        avlTreeSet.traverse();

        System.out.println(avlTreeSet.contains(new Integer(80)));

    }
}
