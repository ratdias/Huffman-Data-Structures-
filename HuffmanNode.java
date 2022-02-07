/**
 * The HuffmanNode class represents a node of the HuffmanCodeTree class. Each instance of this class stores 2 next nodes
 * Zero and One, and a letter char
 */
public class HuffmanNode {
    private HuffmanNode zero;
    private HuffmanNode one;
    private Character data;

    /**
     * This is the constructor to create a completely empty node
     */
    public HuffmanNode(){
        zero = null;
        one = null;
        data = null;
    }

    /**
     * This constructor takes the next 2 nodes and sets data to null. This is an internal node constructor
     * @param zero Is the next Zero node
     * @param one Is the next One node
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
        data = null;
    }

    /**
     * This constructor sets the data stored at the node to a char and the 2 next nodes to null. This is a leaf constructor
     * @param data is the data to be stored
     */
    public HuffmanNode(char data) {
        this.zero = null;
        this.one = null;
        this.data = data;
    }

    public HuffmanNode getOne() {
        return one;
    }

    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    public HuffmanNode getZero() {
        return zero;
    }

    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }

    /**
     * The isLeaf method checks if a certain node is a leaf by checking if there is data stored and if there are no 2 next nodes
     * @return true if the node is a leaf, false if it is not
     */
    public boolean isLeaf() {
        if (data != null && zero == null && one == null) {
            return true;
        }
        return false;
    }

    /**
     * The isValid method uses recursion to test if each node of the tree is valid by using the isLeaf and isInternalNode method
     * @return true if every single node is valid, and false if there is at least one invalid node
     */
    public boolean isValid(){
        if (!isLeaf()){
            if(!isInternalNode()){
                return false;
            }
        }
        if ((zero == null || one == null) && !isLeaf()){
            return false;
        }

        else if (zero != null && !zero.isValid()){
            return false;
        }
        else if (one != null && !one.isValid()){
            return false;
        }
        else {return true;}
    }

    /**
     * the isInternalNode method checks if a certain node is an internal node by check if there are next nodes and if the data is
     * empty
     * @return true if the node is an internal node, false if it is not
     */
    public boolean isInternalNode(){
        if (data == null && zero != null || one != null){
            return true;
        }
        return false;
    }
}
