public class MainClass{
    public static void main(String[] args){
        RedBlackTree t = new RedBlackTree();
        
        t.insertNode(3);t.insertNode(2); t.insertNode(1); t.insertNode(4);     
        System.out.println(t);
    }
}
class Node{
    int value;
    boolean color; // true = black, false = red
    Node leftChild; // a < b
    Node rightChild; // a > b
    Node parent;
    Node uncle;

    public Node(int v){
        this.value = v;
        this.color = false; //new nodes are red
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
        this.uncle = null;
    }

    public String toString(){
        return this.value + ": " + (this.color ? "black" : "red");
    }
}

class RedBlackTree{
    Node root;
    int size;
    final int COUNT = 10;
    /***
     * initialize tree:
     *      - root set to null
     *      - root color set to black (true)
     *      - size is number of nodes in tree
     */
    public RedBlackTree(){
        this.root = null;
        this.size = 0;
    }

    /***
     * 
     * @param v: value assoc with new node
     *  Find the parent of the node we wish to insert, then insert new node as correct child of the parent
     *  Check balancing
     */
    public void insertNode(int v){
        this.size++;

        if(this.root == null){
            this.root = new Node(v);
            this.root.color = true;
            return;
        }

        Node newNode = new Node(v);

        Node p = findParent(v);

        if(p.parent != null){  // re-init uncle
            if(p.value < p.parent.value){ newNode.uncle = p.parent.rightChild; }
            else{ newNode.uncle = p.parent.leftChild; }
        }

        newNode.parent = p;
        recolor(newNode);

        if(v < p.value){
            //System.out.println("insert left: "+ newNode);
            p.leftChild = newNode;
            return;
        }
        //System.out.println("insert right: "+ this.root.rightChild.parent);
        p.rightChild = newNode;
        return;
    }
    
    /***
     * Find the parent node of the node we wish to add based on the input value
     */
    public Node findParent(int v){
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
    
    /**
     * Logic on cases pulled from GeeksforGeeks: https://www.geeksforgeeks.org/red-black-tree-set-2-insert/?ref=lbp
     * @param x: current node being added to the tree
     */
    public void recolor(Node x){
        // if x is root, then do nothing
        if(x.parent == null){ return; }
        if(!x.parent.color){ // parent is RED
            if(x.uncle == null || x.uncle.color){ //uncle is BLACK
                // is parent a left child of grandp?
                if(x.parent.value < x.parent.parent.value){
                    if(x.value < x.parent.value){ // LEFT-LEFT CASE
                        System.out.println("left-left: "+ x);
                        if(x.parent.parent.parent != null){// gp.parent = p
                            if(x.parent.parent.value < x.parent.parent.parent.value){ x.parent.parent.parent.leftChild = x.parent;} // left
                            else{x.parent.parent.parent.rightChild = x.parent;} // right
                        }
                        // if gp is ROOT, then p becomes ROOT
                        if(this.root.value == x.parent.parent.value){
                            this.root = x.parent;
                            x.parent.parent.color = false; // make OLD gp RED
                        } 
                        if(x.parent.rightChild != null){ x.parent.rightChild.parent = x.parent.parent; }// p.right.parent = gp
                        
                        x.parent.parent.leftChild = x.parent.rightChild; // gp.leftChild = p.rightChild
                        x.parent.rightChild = x.parent.parent; // p.rightChild = gp
                        x.parent.rightChild.parent = x.parent; // set parent for OLD gp
                        x.parent.color = true; // p.color = BLACK
                        return;
                        
                    }else{ // LEFT-RIGHT CASE
                        System.out.println("left-right: "+ x);
                        return;
                    }
                }else{
                    if(x.value < x.parent.value){ // RIGHT-LEFT CASE
                        System.out.println("right-left: "+ x); return;
                    }else{ // RIGHT-RIGHT CASE
                        System.out.println("right-right: "+ x); 
                        if(x.parent.parent.parent != null){// gp.parent = p
                            if(x.parent.parent.value < x.parent.parent.parent.value){ x.parent.parent.parent.leftChild = x.parent;} // left
                            else{x.parent.parent.parent.rightChild = x.parent;} // right
                        }
                        // if gp is ROOT, then p becomes ROOT
                        if(this.root.value == x.parent.parent.value){
                            this.root = x.parent;
                            x.parent.parent.color = false; // make OLD gp RED
                        } 
                        if(x.parent.leftChild != null){ x.parent.leftChild.parent = x.parent.parent; }// p.right.parent = gp
                        
                        x.parent.parent.rightChild = x.parent.leftChild; // gp.leftChild = p.rightChild
                        x.parent.leftChild = x.parent.parent; // p.rightChild = gp
                        x.parent.leftChild.parent = x.parent; // set parent for OLD gp
                        x.parent.color = true; // p.color = BLACK
                        return;
                    }
                }
            }else{ //unlce is RED
                System.out.println("uncle is red");
                x.uncle.color = true; // change uncle to BLACK
                x.parent.color = true;// change parent to BLACK

                if(x.parent.parent.value != this.root.value){ x.parent.parent.color = false; } // change grandparent to RED
                recolor(x.parent.parent);
                return;
            }
        }
        return;
    }
    
    // code to print tree from GeeksForGeeks - https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
    public void printTree(Node root, int space){
        // Base case  
        if (root == null){  return;  }

        // Increase distance between levels  
        space += COUNT;  

        // Process right child first  
        printTree(root.rightChild, space);  

        // Print current node after space  
        System.out.println("\n"+" ".repeat(space-COUNT) + root);  

        // Process left child  
        printTree(root.leftChild, space);
    }

    public String toString(){
        printTree(this.root, 0);
        return "\n";
    }

}


