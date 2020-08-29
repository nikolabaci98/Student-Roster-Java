package dataStructures;

import java.io.*;
import java.util.*;

public class Roster {
	
	BST treeRoster;
	final int maxSize = 10;		//max size of the ordered array
	static int treeCount = 0;	//the number of nodes in the Binary Search Tree
	static int count = 0; 		//the occupied size of the array
	Student[] arrayRoster;		//sorted Students
	Queue waitingList;			//stores students when BST/array is full

	/**
	 * Constructor. It creates a roster implementing Binary Search Tree form the 
	 * students in the provided text file. If the file has more than 10 
	 * students, then students are put in a waiting list implemented using a queue.
	 * Runtime: O(n) where n is the number of students in the file
	 * @param File fileName
	 * @throws FileNotFoundException
	 */
	public Roster(File fileName) throws FileNotFoundException {
		String studentName, studentSurname;
		int studentID;
		treeRoster =  new BST();
		waitingList = new Queue();
		Scanner in = new Scanner(fileName);											   //	+----------------------+
		while(in.hasNext()) {													   //	|       FileName       |
			studentName = in.next();											   //	|           	       |
			studentSurname = in.next();											   //	|john	doe	100    |
			studentID = Integer.parseInt(in.next()); 							                   //	|tom	smith	200    |
			if(treeCount < maxSize)	{											   //	|		       |			   |
				treeRoster.insert(new Student(studentName, studentSurname, studentID));                                    //   +----------------------+
				treeCount++;
			}																			  
			else
				waitingList.enQ(new Student(studentName, studentSurname, studentID));
		}																			
		in.close();																	
	}	
	
	/**
	 * Creates the array form the BST. Size of array is 10.
	 * If the BST is not empty, it calls the recursive 
	 * function toArray() which does in-order traversal
	 * Copies reference to the first 10 students (or less).
	 * Runtime: O(n) 
	 */
	public void createArrayRoster() {
		arrayRoster = new Student[maxSize];
		if(treeRoster.getRoot() != null)
			toArray(treeRoster.getRoot());
	}
	
	/**
	 * Accessible from createArrayRoster(). Recursive function that
	 * does in-order traversal. The elements in the array are stored
	 * in a sorted order.
	 * Runtime: O(n)
	 * @param bst
	 */
	private void toArray(BSTNode bst) {
		if(bst == null || count >= maxSize) {
			return;
		}
		toArray(bst.left);
		arrayRoster[count++] = bst.data;
		toArray(bst.right);
	}
	
	/**
	 * If the array is created, add the new student in the array
	 * and maintain the sorted order. If array is full, append in the
	 * waiting list.
	 * 
	 * Else if array is NOT created, add the new student in the BST.
	 * If BST is full (meaning it has 10 students), the add the student 
	 * the waiting list.
	 * 
	 * Runtime: Worst case is O(n) because of the for-loop
	 * 			Best case is O(1) when adding in the waiting list
	 * @param Student
	 */
	public void append(Student s) {
		
		if(arrayRoster != null) {
			if(count < maxSize) {
				int buffer = count;
				for (int i = 0; i < count ; i++) {
					if(s.compareTo(arrayRoster[i]) < 0) {
						buffer = i;
						break;
					}	
				}
			
				for (int tracer = count - 1; tracer >= buffer; tracer--) {
					arrayRoster[tracer+1] = arrayRoster[tracer];
				}
				arrayRoster[buffer] = s;
				count++;	
			
			}
			else
				waitingList.enQ(s);
		}
		else {
			if(treeCount < maxSize)
				treeRoster.insert(s);
			else
				waitingList.enQ(s);
		}
	}
	
	/**
	 * If the array is created, remove the given student from the array
	 * and maintain the sorted order. If array is not full and there are 
	 * students in the waiting list, add it in the array and maintain 
	 * the sorted order.
	 * 
	 * Else if array is NOT created, remove the given student from the BST.
	 * If BST is not full (meaning it less 10 students) and the waiting list
	 * has students, then  add the student from the waiting list to BST.
	 * 
	 * Runtime: Worst case is O(n) because of the for-loop or nodes in BST
	 * all go to the left or right.
	 * @param Student
	 */
	public void remove(Student s) {
		 if(arrayRoster != null) {
			 int i = 0;
			 while (i <= count && arrayRoster[i].compareTo(s) != 0) {
				 i++;
			 }
			 for(int j = i; j < count; j++) {
				 arrayRoster[j] = arrayRoster[j+1];
			 }
			 
			 if(!waitingList.isEmpty())
				 append(waitingList.deQ());
			 else {
				 arrayRoster[count] = null;
				 count--;
			 }
			  

		 }
		 else {
			 treeRoster.remove(s);
			 if(!waitingList.isEmpty())
				 treeRoster.insert(waitingList.deQ());
			 else
				 treeCount--;
		 }
			 
	}
	
	
	/**
	 * If array is created, search the array. All students with the
	 * same name are added in a linked list which is the returned.
	 * 
	 * If array is not created, search the BST. 
	 * 
	 * Runtime: O(n) traversing the array
	 * @param String first name
	 * @param String last name
	 * @return LinkedList of all students with this name
	 */
	public LinkedList searchStudent(String first, String last) {
		LinkedList matches = new LinkedList();
		if(arrayRoster == null) {
			if(treeRoster.getRoot() != null) {
				matches = treeRoster.treeSearch(first, last);
			} 
		}
		else {
			for(int i = 0; i < count; i++) {
				if(arrayRoster[i].getFirstName().compareTo(first) == 0 
						&& arrayRoster[i].getLastName().compareTo(last) == 0)
					matches.append(arrayRoster[i]);
			}
		}
		return matches;
	}
	
	/**
	 * Opens a dialog so that the user selects the output file.
	 * Output all the sorted students from the array to the text file.
	 * Runtime: O(1) (since array is of size 10)
	 * @return String path of the file
	 * @throws FileNotFoundException
	 */
	public void saveOnFile() throws FileNotFoundException {
		File rosterFile = new File("/users/nikolabaci/desktop/roster.txt");
		File waitingListFile = new File("/users/nikolabaci/desktop/waitingList.txt");
		
	
		PrintWriter out = new PrintWriter (rosterFile);
		for(int i = 0; i < count; i++) {
			out.println(arrayRoster[i].toString());
		}
		out.close();
		
		if(!waitingList.isEmpty()) {
			out = new PrintWriter (waitingListFile);
			Node currNode = waitingList.getQueueFront();
			while(currNode != null) {
				out.println(currNode.data.toString());
				currNode = currNode.next;
			}
			out.close();
		}
		
	}
	
	/**
	 * Print the sorted array (arrayRoster)
	 */
	public void printArray() {
		
		if(arrayRoster == null) {
			System.out.println("The sorted array is empty. Save the list then try again.");
		}
		else {
			System.out.println("Array:");
			for (int i = 0; i < count; i++) {
				System.out.println(arrayRoster[i].toString());
			}
		}
	}
	/**
	 * Calls the recursive private method printRoster(BSTNode).
	 * Method printRoster(BSTNode) will print the BST using
	 * in-order traversal.
	 */
	public void printRoster() {
		if(treeRoster.getRoot() != null) {
			printRoster(treeRoster.getRoot());
		}
	}
	/**
	 * Prints the BST using in-order traversal, recursively.
	 *
	 * @param bst
	 */
	private void printRoster(BSTNode bst) {
		if(bst != null) {
			printRoster(bst.left);
			System.out.println(bst.data.toString());
			printRoster(bst.right);
		}
	} 
	
	/** 
	 * Return the size of the linked list (listRoster)
	 * @return int
	 */
	public int getTreeSize() {
		return treeCount;
	}
	
	/**
	 * Return the occupied size of the array (arrayRoster)
	 * @return int
	 */
	public int getArraySize() {
		return count;
	}
	
	/**
	 * Prints the waiting list
	 */ 
	public void printWaitingList() {
		waitingList.printQ();
	}
}
