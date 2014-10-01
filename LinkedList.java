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
    }

    //LinkedList METHODS:
    public void insert(char c)
    {

    }

    public boolean contains(char c)
    {
        //TODO implement method
        return false;
    }

}