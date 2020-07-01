public class Node{
    int value;
    boolean color; // true = black, false = red
    Node leftChild; // a < b
    Node rightChild; // a > b

    public Node(int v){
        this.value = v;
        this.color = false; //new nodes are red
        this.leftChild = null;
        this.rightChild = null;
    }
}