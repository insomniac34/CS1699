/*
 *@Programmer: Tyler Raborn
 *@email: <tpr11@pitt.edu>
 *@Class: CS-1501 Summer 2013
 *@Environment: Eclipse Juno IDE, Windows 8 Pro 64-bit, JDK7.0_21
 *@Assignment: Project 1 - Part B - DLB
 *@Due: 6/5/2013
 */

//Eclipse Stuff - DELETE BEFORE RUNNING NATIVELY:////////////////////
//package assignment1;

//library imports
import java.util.*;
import java.io.*;
import java.math.*;
import java.awt.*;

//@SuppressWarnings("unused")
public class DLB implements DictInterface  //external class; provides an external medium for the various LinkedLists and their nodes to be created within; these, coloquially, make up the De La Brandais Trie.
{
    private int linkedListCount;
    private final int IS_WORD_ONLY = 2;
    private final int IS_PREFIX_AND_WORD = 3;
    private final int IS_PREFIX_ONLY = 1;
    private final int NOT_WORD_OR_PREFIX = 0;

    //DLB FIELDS:
    public boolean isEmpty;
    protected LinkedList root; //holds the "root level" LinkedList; that is, the LinkedList that contains the first letters of all words stored within the De La Brandais Trie.
    private int size; //how many words?
    private final char SENTINEL='*';
    private Node iteratorNode;
    private LinkedList iteratorList;

    //DLB CONSTRUCTORS:
    public DLB()
    {
        this.isEmpty=true;
        this.linkedListCount = 0;
        this.size = 0;
    }
    public DLB(MyDictionary M)
    {
        this.isEmpty=true;
        this.size = 0;
    }
    public DLB (char c)
    {
        this.isEmpty=true;
        String s = new String(new Character(c).toString());
        add(s);
        this.size = 1;
    }
    public DLB(String s)
    {
        this.linkedListCount = 0;
        this.isEmpty=true;
        this.add(s);
        this.size = 1;
    }
    public DLB(StringBuilder s)
    {
        this.size = 1;
        this.linkedListCount = 0;
        this.isEmpty=true;
        this.add(s.toString());
    }

    public int size() {
        return this.size;
    }

    //DLB METHODS:
    public int linkedListCount() {
        return 0;
    }

    public boolean add(String s) //embeds s into the DLB...
    {
        this.size+=1;
        int pos=-1;
        if (this.isEmpty==true) //if the DLB is empty
        {
            LinkedList prevList = new LinkedList(s.charAt(0));
            this.root = prevList;
            for (int i = 1; i < s.length(); i++) //for each letter in s, starting at i+1
            {
                LinkedList curList = new LinkedList(s.charAt(i)); //creates NEW list
                prevList.head.childNode = curList.head; //points prevList pointer Node to curList head Node
                prevList = curList;
            } //end forloop...there is now s.length()-1 LinkedLists in the DLB
            LinkedList sentinelList = new LinkedList(this.SENTINEL);
            prevList.head.childNode=sentinelList.head;
            this.isEmpty=false; //isEmpty only changes to false once the first word is added...
            return true;
        }
        else
        {
            int letterFound = 0;
            int temp=0;
            Node curNode = this.root.head;
            for (int i = 0; i<=s.length()-1; i++) //for i=each letter in s
            {
                //System.out.println(curNode.data);
                if ((i==s.length()-1) && curNode.data==s.charAt(i)) //if the last letter has been found
                {
                    //System.out.println(s.charAt(i));
                    if (curNode.childNode.data!=this.SENTINEL) //if there is NO sentinel at the end of the word
                    {
                        curNode = curNode.childNode; //move down one node
                        if (curNode.siblingNode==null) // if the current node has no siblings
                        {
                            curNode.siblingNode = new Node(this.SENTINEL);
                            //System.out.println("Created new sentinel node; s is a prefix to a pre-existing word in the DLB");
                            return true;
                        }
                        else //we must iterate to make sure there are no siblings that hold a sentinel
                        {
                            Node prevNode=curNode;
                            while (curNode.siblingNode!=null)
                            {
                                //System.out.println(curNode.data);
                                if (curNode.data==this.SENTINEL)
                                {
                                    //System.out.println("add() error: String has already been added to the DLB.");
                                    this.size--;
                                    return false;
                                }
                                //prevNode = curNode;
                                curNode = curNode.siblingNode;
                            }
                            curNode.siblingNode=new Node(this.SENTINEL);
                            curNode=curNode.siblingNode;
                            //System.out.println("created new sentinel node after iterating to ensure sentinel did not already exist.");
                            return true;
                        }
                    }
                    else //else s is already a word in the DLB; return false
                    {
                        //System.out.println("add() error: String has already been added to the DLB.");
                        this.size--;
                        return false;
                    }
                }
                if (curNode.data!=s.charAt(i)) //if the current letter doesn't match the current Node's data
                {
                    if (curNode.siblingNode!=null) //if the next node is NOT null, begin a while loop checking for the letter:
                    {
                        while (curNode.siblingNode!=null) //while loop moves siblingNode into a position such that we can create a new node there
                        {
                            //System.out.println("curNode data: " + curNode.data + " " + letterFound);
                            curNode = curNode.siblingNode;
                            if (curNode.data==s.charAt(i)) //if we found the letter
                            {
                                //System.out.println("letter found");
                                letterFound=1;
                                break;	//break out of whileloop
                            }

                        }
                        if (letterFound==0)
                        {
                            //System.out.println("A");
                            curNode.siblingNode = new Node(s.charAt(i));
                            curNode = curNode.siblingNode;
                            temp = i+1;
                            break; //break out of forloop
                        }
                        else
                        {
                            letterFound=0;
                        }
                    }
                    else //the only letter in this linked list is empty. we need to create a new node for the missing letter
                    {
                        //System.out.println("B");
                        curNode.siblingNode = new Node(s.charAt(i));
                        curNode=curNode.siblingNode;
                        temp = i+1;
                        break; //break out of forloop
                    }
                }

                curNode = curNode.childNode;
            }
            //If control reaches this point, we just need to finish building the word.
            if (temp>s.length()-1) //if temp is greater than the length of the word
            {
                LinkedList endList = new LinkedList(this.SENTINEL);
                curNode.childNode=endList.head;
                return true;
            }
            else if (temp==s.length()-1) //else if temp is the last letter in the word
            {
                LinkedList newList = new LinkedList(s.charAt(temp));
                curNode.childNode = newList.head;
                curNode = curNode.childNode;
                LinkedList endList = new LinkedList(this.SENTINEL);
                curNode.childNode=endList.head;
                return true;
            }
            else //else there are still letters in need of printing
            {
                //System.out.println(temp);
                for (int i = temp; i<=s.length()-1; i++) //for each remaining letter that needs built
                {
                    LinkedList newList = new LinkedList(s.charAt(i));
                    //System.out.println(s + " " + s.charAt(i) + newList.head.data);
                    curNode.childNode=newList.head;
                    curNode=curNode.childNode;
                }
                LinkedList endList = new LinkedList(this.SENTINEL);
                curNode.childNode=endList.head;
                return true;
            }
        }
    }
    public void DFS(Node tempNode) //test method; uses a Depth-First-Search-like algorithm to print out all contents of the DLB:
    {
        System.out.print(tempNode.data);
        if ((tempNode.childNode!=null))
        {
            DFS(tempNode.childNode);
        }
        if (tempNode.siblingNode!=null)
        {
            DFS(tempNode.siblingNode);
        }
    }

    public int searchPrefix(String s) {
        return this.searchPrefix(new StringBuilder(s));
    }

    public int searchPrefix(StringBuilder s)
    {
        iteratorList = this.root;
        iteratorNode = this.root.head;
        boolean isPrefix = false;
        boolean isWord = false;
        for (int i = 0; i<=s.length()-1; i++) //for loop iterates over entirety of word...once it exits simply analyze results
        {
            if ((i==s.length()-1) && (searchList(iteratorList, s.charAt(i))==true)) //if we're at the end of the word AND the letter is found
            {
                isPrefix=true;
                //System.out.println(s.charAt(i));
                //System.out.println(iteratorNode.childNode.data);
                iteratorNode = iteratorNode.childNode;
                iteratorList=iteratorNode.thisList;
                int iteratorCount=0;
                while (iteratorNode.siblingNode!=null)
                {
                    iteratorCount++;
                    //System.out.println("# of additional nodes in the list holding the sentinel of " + s + " = " + iteratorCount);
                    iteratorNode=iteratorNode.siblingNode;
                }
                if(findSentinel(iteratorList)==true)
                {
                    isWord=true;
                    if(iteratorCount<1)
                        isPrefix=false;
                    break;
                }
            }
            else if (searchList(iteratorList, s.charAt(i))==true) //else we're not at the end and we still need to iterate through the list
            {
                iteratorNode = iteratorNode.childNode;
                iteratorList = iteratorNode.thisList;
            }
        }
        if (isWord==true && isPrefix==true)
        {
            return 3;
        }
        else if (isWord==true && isPrefix==false)
        {
            return 2;
        }
        else if (isWord==false && isPrefix==true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int searchPrefix(String s, int start, int end) {
        return this.searchPrefix(new StringBuilder(s), start, end);
    }

    public int searchPrefix(StringBuilder s, int start, int end)
    {
        iteratorList = this.root;
        iteratorNode = this.root.head;
        boolean isPrefix = false;
        boolean isWord = false;
        for (int i = start; i<=end; i++) //for loop iterates over entirety of word...once it exits simply analyze results
        {
            if ((i==end) && (searchList(iteratorList, s.charAt(i))==true)) //if we're at the end of the word AND the letter is found
            {
                isPrefix=true;
                //System.out.println(s.charAt(i));
                //System.out.println(iteratorNode.childNode.data);
                iteratorNode = iteratorNode.childNode;
                iteratorList=iteratorNode.thisList;
                int iteratorCount=0;
                while (iteratorNode.siblingNode!=null)
                {
                    iteratorCount++;
                    //System.out.println("# of additional nodes in the list holding the sentinel of " + s + " = " + iteratorCount);
                    iteratorNode=iteratorNode.siblingNode;
                }
                if(findSentinel(iteratorList)==true)
                {
                    isWord=true;
                    if(iteratorCount<1)
                        isPrefix=false;
                    break;
                }
            }
            else if (searchList(iteratorList, s.charAt(i))==true) //else we're not at the end and we still need to iterate through the list
            {
                iteratorNode = iteratorNode.childNode;
                iteratorList = iteratorNode.thisList;
            }
        }
        if (isWord==true && isPrefix==true)
        {
            return 3;
        }
        else if (isWord==true && isPrefix==false)
        {
            return 2;
        }
        else if (isWord==false && isPrefix==true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /* added by Tyler on 9-29-2014 for CS1699 Project 2*/
    public boolean contains(String target) {
        boolean ret = false;
        LinkedList curList = this.root;
        Node iter = curList.head;
        int targetIndex = 0;
        while (iter != null) {
            //System.out.println("Current char: " + iter.data);
            if (iter.data == target.toCharArray()[targetIndex]) {
                if (targetIndex == target.length()-1 &&
                        iter.data == target.toCharArray()[target.length()-1] &&
                        isLastLetterInWord(iter)) {
                    //if last letter in word, set success & break
                    ret = true;
                    break;
                }
                else if (targetIndex == target.length()-1 &&
                        iter.data == target.toCharArray()[target.length()-1]) {
                    break;
                }
                else {
                    //else descend to another level
                    iter = iter.childNode;
                    curList = iter.getCurrentList();
                }
            }
            else {
                iter = iter.siblingNode;
            }
            targetIndex++;
        }
        return ret;
    }

    /* added by Tyler on 9-29-2014 for CS1699 Project 2*/
    public boolean remove(String target) {
        boolean ret = false;
        if (this.searchPrefix(new StringBuilder(target)) == IS_WORD_ONLY ||
                this.searchPrefix(new StringBuilder(target)) == IS_PREFIX_AND_WORD) {
            LinkedList curList = this.root;
            Node iter = curList.head;
            int targetIndex = 0;
            while (iter != null) {
                System.out.println(" char: " + iter.data);
                if (iter.data == target.toCharArray()[targetIndex]) {
                    if (targetIndex == target.length()-1 &&
                            iter.data == target.toCharArray()[target.length()-1] &&
                            isLastLetterInWord(iter)) {
                        //if last letter in word, delete sentinel
                        Node deletionTarget = iter.childNode;
                        Node prev = iter.childNode;
                        while (true) {
                            if (deletionTarget.data==SENTINEL) {
                                deletionTarget.data = ' ';
                                break;
                            }
                            prev = deletionTarget;
                            deletionTarget = deletionTarget.siblingNode;
                        }
                        ret = true;
                        break;
                    }
                    else {
                        //else descend to another level
                        iter = iter.childNode;
                        curList = iter.getCurrentList();
                    }
                }
                else {
                    iter = iter.siblingNode;
                }
                targetIndex++;
            }
        }
        else {

        }
        return ret;
    }

    /* added by Tyler on 9-29-2014 for CS1699 Project 2
			tests to see if theres a sentinel at the next level of the DLB from the passed-in node
    */
    private boolean isLastLetterInWord(Node node) {
        Node iter = new Node(node.childNode);
        boolean ret = false;
        while (iter != null) {
            if (iter.data == SENTINEL) {
                System.out.println("SENTINEL!!!");
                ret = true;
                break;
            }
            else {
                iter = iter.siblingNode;
            }
        }
        return ret;
    }

    protected boolean findSentinel(LinkedList curList)
    {
        iteratorList = curList;
        iteratorNode = curList.head;
        if (searchList(curList, this.SENTINEL)!=true)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    protected boolean searchList(LinkedList curList, char c)
    {
        if(iteratorList.head.data==c)
        {
            return true;
        }
        else
        {
            while (iteratorNode!=null)
            {
                if(iteratorNode.data==c)
                {
                    return true;
                }
                iteratorNode=iteratorNode.siblingNode;
            }
            return false;
        }
    }
    public void print(String s) //tests DLB structural integrity...
    {
        int endFlag=-1;
        Node curNode = this.root.head;
        LinkedList curList = this.root;

        //NOTE: the while loop iterates left-to-right, the for loop iterates top-to-bottom
        for (int i = 0; i<=s.length()-1;i++) //for each letter in the word
        {
            if (curNode!=null)
            {
                do
                {
                    //System.out.println("inside of do-while");
                    if (curNode.data==s.charAt(i)) //if the letter is found where its supposed to be...
                    {
                        System.out.print(s.charAt(i));
                        endFlag=0; //set the flag to represent the fact that we are jumping to the next level of LinkedList...
                        break;
                    }
                    else
                    {
                        //empty clause. if control reaches this block, the Do While should fail.
                    }
                    curNode=curNode.siblingNode;

                } while(curNode!=null);
                //if the above dowhile terminates on it's own condition, then at this point, the word wasn't found

            }
            if(endFlag==-1) //the DoWhile ended because it could not find the letter.
            {
                System.out.println("\nPrint() terminated with the following error: String \"" + s + "\" is not contained within DLB. (Search failed at character '" + s.charAt(i) + "', index " + i + ".)");
                break;
            }
            else if(endFlag==0) //the letter was found, so we jump to the next LinkedList...
            {
                curNode = curNode.childNode;
                endFlag=-1; //reset endFlag
            }
        }
    }
    protected void printRoot() //another test method, prints all letters in the root LinkedList.
    {
        if (this.isEmpty!=true)
        {
            System.out.println("The following characters are stored in the root Linked List: ");
            Node curNode = root.head;
            System.out.println(curNode.data);
            if (curNode.siblingNode!=null)
            {
                while (curNode.siblingNode!=null)
                {
                    curNode=curNode.siblingNode;
                    System.out.println(curNode.data);
                }
            }
        }
        else
        {
            System.out.println("DLB is empty. Unable to print root.");
        }
    }
    protected boolean insertSentinel(Node curNode) //goes to the next linkedlist, adds a new sentinel node
    {
        if(curNode.childNode.getData()!=this.SENTINEL)
        {
            curNode = curNode.childNode; //moves curNode to the next LinkedList
            while (curNode.siblingNode!=null) //iterates through the list until 'null' encountered:
            {
                curNode = curNode.siblingNode;
            }
            curNode.siblingNode = new Node(this.SENTINEL);
            return true;
        }
        else
        {
            return false;
        }
    }
    private void createList(char c) //instantiates a new Linked List object, initialized with a single node N containing char c
    {
        LinkedList newList = new LinkedList(c);
    }

    private void insert(char c, LinkedList curList) //for testing purposes
    {
        Node tempNode = curList.head;
        while (tempNode!=null)
        {
            tempNode = tempNode.siblingNode;
        }
        tempNode = new Node(c);
    }
}
	