/**
 * The Public class HuffmanCodeBook is an array based list in which each element of the array is an instance of the HuffmanCodeBook
 * class. Consequently, each member stores a letter and a BinarySequence, which can be manipulated using the methods of the class
 */
public class HuffmanCodeBook {
    public char iterawrLetter;
    public BinarySequence iterawrSeq;
    private HuffmanCodeBook chain;
    private BinarySequence seq;
    private char letter;
    private int index = 0;
    private int lastIndex;
    private int size = 10;
    private HuffmanCodeBook[] array = new HuffmanCodeBook[size];
    public BinarySequence getSeq() {
        return seq;
    }
    public char getLetter() {
        return letter;
    }

    /**
     * The constructor initializes an instance of the class, storing a letter and the matching BinarySequence
     * @param c is the letter
     * @param seq is the sequence
     */
    public HuffmanCodeBook(char c, BinarySequence seq) {
        letter = c;
        this.seq = seq;
    }

    /**
     * This constructor does not take any arguments and just sets the first element of the array to 0
     */
    public HuffmanCodeBook() {
        if (chain == null) {
            array[0] = chain;
        }
    }

    /**
     * The addSequence method initializes the chain if this is the first call of the method, or append a new element with char c
     * and BinarySequence seq. Then, besides updating core variables it also call sortArray
     * @param c is the letter
     * @param seq is the seq
     */
    public void addSequence(char c, BinarySequence seq) {
        if (chain == null) {
            chain = new HuffmanCodeBook(c, seq);
            array[0] = chain;
            lastIndex = 0;
        } else {
            expandArray();
            insert(c, seq);
        }
    }

    /**
     * The iterawr method iterates through the array visiting each element and getting their letter/sequence so that other classes
     * can have the access to the members of the array through the public variables iterawrLetter and iterawrSeq
     * @return true while the current index has not reached the last index
     */
    public boolean iterawr(){
        while(index <= lastIndex){
            iterawrLetter = array[index].getLetter();
            iterawrSeq = array[index].getSeq();
            index++;
            return true;
        }
        index = 0;
        return false;
    }

    /**
     * The expandArray method checks if the last index of the array has reached the end of the array, if that is the case, it
     * creates a copy of the array with double the length and sets the array equal to the copied array
     */
    private void expandArray() {
        if (lastIndex >= size -1) {
            HuffmanCodeBook[] temporary = new HuffmanCodeBook[2 * size];
            for (int i = 0; i < 2*size; i++) {
                if (i < size){
                    if(array[i] != null) {
                        temporary[i] = array[i];
                    }
                }
                else{ temporary[i] = null;}
            }
            array = temporary;
            size = size*2;
        } else {
            ;
        }
    }

    /**
     * The moveRight method takes an index and shift every element to the right starting at the index
     * @param position is the index
     */
    private void moveRight(int position) {
        for (int i = lastIndex; i >= position; i--) {
            array[i + 1] = array[i];
        }
    }

    /**
     * The insert method iterate through the array and find the proper index to insert a new element maintaining the order
     * @param c is the letter of the new element
     * @param seq is the sequence of the new element
     */
    private void insert(char c, BinarySequence seq){
        int position = 0;
        for (int i = 0; i <= lastIndex; i++){
            if(array[i].getLetter() > c){
                position = i;
                break;
                }
            else if(i == lastIndex){
                position = i+1;
            }
        }
        moveRight(position);
        array[position] = new HuffmanCodeBook(c, seq);
        lastIndex++;
    }

    /**
     * The sortArray method uses a sorting algorithm to sort the elements of the array  associating with the numerical value of the
     * char stored in each element
     */
    private void sortArray() {
        HuffmanCodeBook temp = null;
        if (lastIndex >= 1) {
            for (int i = 0; i < lastIndex; i++) {
                for (int j = i + 1; j < lastIndex; j++) {
                    if (array[i].getLetter() > array[j].getLetter()) {
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * The contains method uses a binary search algorithm to find which element contains the letter to be found
     * @param letter to be found
     * @return true if an element is storing the letter, false if no element stores the letter
     */
    public boolean contains(char letter) {
        int firstIndex = 0;
        int indexLast = lastIndex;
        while (firstIndex <= indexLast) {
            int middle = (firstIndex + indexLast) / 2;
            if (array[middle] == null){
                return false;
            }
            if (array[firstIndex] .getLetter() == letter){
                return true;
            }
            else if (array[indexLast-1] .getLetter() == letter){
                return true;
            }
            if (array[middle].getLetter() == letter) {
                return true;
            }
            else if (array[middle].getLetter() > letter) {
                indexLast = middle - 1;
            } else if (array[middle].getLetter() < letter) {
                firstIndex = middle + 1;
            }
        }
        return false;
    }

    /**
     * The containsAll method iterates through a string and call contains for each char of the string, if any of the contain's
     * calls are false, that means that not every single char of the string is stored
     * @param letters input string
     * @return true if the codebook contains all the characters, false if it does not
     */
    public boolean containsAll(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            if (!contains(letters.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * The getSequence method uses a binary search to find each element of the array is storing the char c
     * @param c is the char to be found
     * @return the sequence stored by the element that stores c, or null if no element stores c
     */
    public BinarySequence getSequence(char c) {
        int firstIndex = 0;
        int indexLast = lastIndex;
        while (firstIndex <= indexLast) {
            int middle = (firstIndex + indexLast) / 2;
            if(array[firstIndex].getLetter() == c){
                return array[firstIndex].getSeq();
            }
            else if (array[indexLast].getLetter() == c){
                return array[indexLast].getSeq();
            }
            if (array[middle].getLetter() == c) {
                return array[middle].getSeq();
            } else if (array[middle].getLetter() > c) {
                indexLast = middle - 1;
            } else if (array[middle].getLetter() < c) {
                firstIndex = middle + 1;
            }
        }
        return null;
    }

    /**
     * The encode method sorts the array and append the Binary Sequence stored by the element that stores every character
     * of the array to an empty binary sequence and
     * @param s is the string input
     * @return is the Binary sequence after all the append operations
     */
    public BinarySequence encode(String s) {
        sortArray();
        BinarySequence ret = new BinarySequence();
        for (int i = 0; i < s.length(); i++) {
            if (getSequence(s.charAt(i)) != null) {
                ret.append(getSequence(s.charAt(i)));
            }
        }
        return ret;
    }
}