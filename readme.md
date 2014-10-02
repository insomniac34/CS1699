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

Difficulty writing the tests occurred when we wanted to test the Node and LinkedList implementations. Originally, they were inner classes of the DLB class and this made mocking, stubbing, and general testing difficult. We changed the implementation of the original program to make them separate classes so that we could better test them. It was also difficult to make any mocking or stubbing tests for the DLB class because few objects are used in it, just char and String arguments and parameters.

The results of our test show that the DLB is flawed and functions properly in some but not all cases. It works well in the context that it is used in Anagram.java but functionality or patterns of use outside of those encountered in that context result in exceptions or incorrect return values. Particularly, null handling needs to be incorporated into the DLB. Additionally, the remove, contains, and searchPrefix functions - all have unique errors especially when used in conjunction with each other.

Failing Tests:

DLBTest.testFindSentinel ->java.lang.NullPointerException


DLBTest.warandPeaceTest -> java.lang.NullPointerException

This test correctly adds every word from the novel War and Peace into the DLB structure and correctly finds some of them in the DLB in the lookup phase but fails to find all the words it added. It is not entirely clear why this occurs, it is possible that memory errors could be the cause or edge case words could be causing the problem.

DLBTest.testFalseRemoval -> java.lang.AssertionError

The DLB does not return true when attempting to remove a word that has been added to it. It is likely that the DLB.remove method is flawed.

DLBTest.testNullContains -> java.lang.NullPointerException

This test fails because the DLB.contains method does not correctly handle null values passed to it.

DLBTest.testWordRemoval -> java.lang.AssertionError

This test fails because the DLB.searchPrefix is still indicating by int value code that a word that has been removed is still a prefix. This is likely due to a flaw in the DLB.remove method that fails to remove prefixes from the structure.

DLBTest.testNullSearch -> java.lang.NullPointerException

This test fails because the DLB.searchPrefix method fails to correctly handle null values that are passed to it.

DLBTest.testSixFigString -> java.lang.Exception: test timed out after 100000 milliseconds

Interestingly, this test times out after 100 seconds. The test attempts to pass a Stringbuilder of six-figure length to the searchPrefix method after adding it. This is interesting because stringbuilders of smaller lengths cause no comparable delay. It would appear as though we have found an edge case.

DLBTest.testPrefixRemoval -> java.lang.AssertionError

This test removes the prefix of a word that is already contained in the dictionary after also adding that prefix. This test likely fails because the DLB.contains method fails to correctly return after a string on the same path has been removed.

DLBTest.testNullAdd -> java.lang.NullPointerException

This test fails because the DLB.add fails to correctly handle nulls that are passed to it.

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
