    public class Node
    {
        //Node FIELDS:
        public Node siblingNode=null; //connects to the next Node object within the LinkedList object; that is, the letters of a different word at this iterative level of the DLB
        public Node childNode=null; //connects to the next Node object in this current word abstraction; that is,
        public char data; //holds a character within some word W; if data = '*', this symbolizes the end of a word...
        public LinkedList thisList;

        //Node CONSTRUCTORS:
        public Node(char c) //constructor
        {
            this.data = c;
        }
        public Node(Node copy) {
            this.siblingNode = copy.siblingNode;
            this.childNode = copy.childNode;
            this.data = copy.data;
            this.thisList = copy.thisList;
        }

        //Node METHODS:

        public LinkedList getCurrentList() {
            return this.thisList;
        }
        public char getData()
        {
            return this.data;
        }
        public void setData(char c)
        {
            this.data = c;
        }
    }