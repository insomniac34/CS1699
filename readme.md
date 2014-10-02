Anagram Generator with de la Briandais Data Structure

Tyler Raborn and Justin Rushin III

CS1699 - DELIVERABLE 2: Unit Testing and Code Coverage

We chose to test this project because it was code that had been written previously for a class that we both have taken. The fact that the program and data structures deal with strings, nodes, and linked lists gave us a good amount of code to cover.

Overview of Objects:

Anagram.java
*insert description of Anagram.java*

MyDictionary.java
*insert description of MyDictionary.java*

DLB.java
*insert description of DLB.java*

LinkedList.java
*insert description of LinkedList.java*

Node.java
*insert description of Node.java*

Concerns
*description of issues faced when writing these tests*
*issues expected going forward based on experiences*
*detail all failing tests in concerns section*

Failing Tests:
DLBTest.testFindSentinel ->java.lang.NullPointerException
DLBTest.warandPeaceTest -> java.lang.NullPointerException
DLBTest.testFalseRemoval -> java.lang.AssertionError
DLBTest.testNullContains -> java.lang.NullPointerException
DLBTest.testWordRemoval -> java.lang.AssertionError
DLBTest.testNullSearch -> java.lang.NullPointerException
DLBTest.testSixFigString -> java.lang.Exception: test timed out after 100000 milliseconds
DLBTest.testPrefixRemoval -> java.lang.AssertionError
DLBTest.testNullAdd -> java.lang.NullPointerException

LinkedListTest.testContainsHeadCaseSensitive -> java.lang.AssertionError
This test fails because the linked list, when searched for a different case of a char value that it contains, does not search for the char value in a case insensitive way.
LinkedListTest.LinkedListGrowth -> java.lang.AssertionError

NodeTest.testGetCurrentList -> java.lang.AssertionError

All of our code and accompanying files are located in our github repo:
https://github.com/insomniac34/CS1699

Executed Unit Tests
![Image of Executed Tests](https://github.com/insomniac34/CS1699/blob/master/tests.png)
Not *all* the executed unit tests display in this screenshot. To see the exported results of the unit tests, see file in repo "Test Results - ALl _in_CS1699.html".

Code Coverage

To determine the code coverage of our tests, we used the code coverage analysis tool that is included with the IntelliJ IDEA.

![Image of Code Coverage](https://github.com/insomniac34/CS1699/blob/master/codecoverage.png)

Here is the table from the screenshot:

|	Element |Class % | Method %  | Line %  |
|---------------|--------|-----------|---------|
|	     DLB|   100% | 	43%  |   55%   |
|        DLBTest|   100% | 	100% |   84%   |
|     LinkedList|   100% |      100% |   93%   |
| LinkedListTest|   100% |      100% |   93%   |
|   MyDictionary|   100% |      75%  |   48%   |
|   	    Node|   100% |      80%  |   86%   |
|   	NodeTest|   100% |      100% |   88%   |
