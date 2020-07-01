public class cs435_proj {
    public static void main(String[] args){
        System.out.println("hello, world");
    }
}
class Node{
    int value;
    boolean color; // true = black, false = red
    Node leftChild; // a < b
    Node rightChild; // a > b
    Node parent;

    public Node(int v){
        this.value = v;
        this.color = false; //new nodes are red
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }
}

class RedBlackTree{
    Node root;
    /***
     * initialize tree:
     *      - root set to null
     *      - root color set to black (true)
     */
    public RedBlackTree(){
        this.root = null;
    }

    public void insertNode(int v){
        if(this.root == null){
            this.root = new Node(v);
            this.root.color = true;
            return;
        }
        Node n = findNode(v);
        String c = n.color ? "black" : "red";
        System.out.println(n.value + ": " + c);
    }
    /***
     * Find the parent node of the node we wish to add based on the input value
     */
    public Node findNode(int v){
        Node tmp = this.root;
        while(tmp.leftChild != null 
                || tmp.rightChild != null){
            if(v < tmp.value){
                if(tmp.leftChild == null){ return tmp; }
                tmp = tmp.leftChild;
            }else{
                if(tmp.rightChild == null){ return tmp; }
                tmp = tmp.rightChild;
            }
        }
        
        return tmp;
    }
    
   

}