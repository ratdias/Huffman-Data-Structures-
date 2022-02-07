/**
 * The HuffmanCodeTree class operates instances of the HuffmanNode class to create a binary tree of nodes
 */
public class HuffmanCodeTree {
    private HuffmanNode node;
    private HuffmanNode root;
    public HuffmanCodeTree(HuffmanNode root){
        this.root = root;
    }

    /**
     * This constructor takes a codebook calls the put method to put the char stored in each element of the array to the
     * tree by using an iterator to loop through the array
     * @param codebook is the input HuffmanCodeBook
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook){
        root = new HuffmanNode();
        while(codebook.iterawr()){
            put(codebook.iterawrSeq, codebook.iterawrLetter);
        }
    }
    public boolean isValid(){
        return root.isValid();
    }

    /**
     * The put method uses the Binary sequence to determine the position of each leaf by checking the boolean stored.
     * if a new node has to be created, the put method initializes the node to an empty node and then sets the variables
     * of the empty node
     * @param seq is the input Binary sequence
     * @param letter is the char to be stored at the tree's node
     */
    public void put(BinarySequence seq, char letter){
        node = root;
        for (boolean b: seq){
            if (b == true){
                if (node.getOne() != null){
                    node = node.getOne();
                }
                else{
                    node.setOne(new HuffmanNode());
                    node = node.getOne();
                }
            }
            else if (b == false){
                if (node.getZero() != null){
                    node = node.getZero();
                }
                else{
                    node.setZero(new HuffmanNode());
                    node = node.getZero();
                }

            }
        }
        node.setData(letter);
    }

    /**
     * The decode method uses the StringBuilder class to append the char stored at every leaf node that is reached through the
     * Binary sequence and returns a string with the decoded message
     * @param s is the sequence (encoded message)
     * @return a string (decoded message)
     */
    public String decode(BinarySequence s){
        StringBuilder string = new StringBuilder();
        node = root;
        for (boolean b : s){
            if (node.isLeaf()){
                string.append(node.getData());
                node = root;
            }
            if (b == true){
                if (node.getOne() == null){
                    node.setOne(new HuffmanNode());
                    node = node.getOne();
                }
                else{node = node.getOne();}
            }
            else if (b == false){
                if (node.getZero() == null){
                    node.setZero(new HuffmanNode());
                    node = node.getZero();
                }
                else{node = node.getZero();}

            }
        }
        string.append(node.getData());
        String retVal = string.toString();
        return retVal;
    }
}
