//DLB CLASSES:
public class LinkedList //a basic linked list data structure; used for holding different "layers" of letters (NOT words) represented via the Node class.
{
    public Node head; //head of the Linked List
    public int i;
    public LinkedList thisList = this;
    public int size;

    //LinkedList CONSTRUCTORS:
    public LinkedList()
    {

    }
    public LinkedList(char c)
    {
        Node newNode = new Node(c);
        this.head = newNode;
        newNode.thisList=this;
        this.size++;
    }

    public boolean contains(char c) {
        boolean ret = false;
        Node iter = this.head;
        while (iter!=null) {
            if (iter.data==c) {
                ret = true;
                break;
            }
            else {
                iter = iter.siblingNode;
            }
        }
        return ret;
    }

    public void insert(char c) {
        Node newNode = new Node(c);
        if (this.size == 0) {
            this.head = newNode;
        }
        else {
            newNode.siblingNode = this.head;
            this.head = newNode;
        }
        this.size++;
    }

    public void insert(Node n) {
        if (this.size == 0) {
            this.head = n;
        }
        else {
            n.siblingNode = this.head;
            this.head = n;
        }
        this.size++;
    }
}