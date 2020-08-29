package dataStructures;

public class LinkedList {

	
	
	private Node head;		//		+---------+     +---------+
	private Node tail;		//		|   head  |	    |   tail  |
	private int length;		//		+---------+	    +---------+
							//				\ 		  /
							//				+---------+
							//				|dummyhead|
							//				+---------+
	
	/**
	 * Instantiates the head and tail to the dummynode.
	 * Instantiates the length to zero.
	 */
	LinkedList(){
		Node dummynode = new Node();
		head = tail = dummynode;
		length = 0;
	}
	
	/**
	 * A method used from the iterator object to gain access to the list
	 * @return node (head of list)
	 */
	public Node getHead() {
		return head;
	}
	
	
	/**
	 * @return length of list (int)
	 */
	public int getLength() {return length;}

	/**
	 * Appends at the end of the list. Updates tail's pointer to the new node.
	 * Constant time: O(1)
	 * @param student
	 */
	public void append(Student s) {
		Node newNode = new Node(s);
		tail.next = newNode;
		tail = newNode;
		length++;
	}
	
	/**
	 * Prepends at the beginning of the list. Updates head's pointer to the new node.
	 * Constant time: O(1)
	 * @param student
	 */
	public void prepend(Student s) {
		Node newNode = new Node(s);
		if(head.next == null) {
			head.next = newNode;
			tail = newNode;
		}
		else {
			newNode.next = head.next;
			head.next = newNode;
		}
		length++;
	}
	
	/**
	 * Prints the content of this linked list.
	 * printList() traverses the entire list: O(n)
	 */
	public void printList() {
		Node currNode = head.next;
		if(currNode == null)
			System.out.println("List is empty. The student was not found.");
		else {
			while(currNode != null) {
				System.out.println(currNode.data.toString());
				currNode = currNode.next;
			}
		}
	}
	
	/**
	 * Removes the student with the corresponding ID from the list
	 * Worst case runtime is: O(n)
	 * @param id (int)
	 */
	
	public void remove(int id) {
		Node prevNode = head;
		Node currNode = head.next;
		while(currNode != null && currNode.data.getID() != id) {
			prevNode = currNode;
			currNode = currNode.next;
		}
		
		if(currNode != null) {
			prevNode.next = currNode.next;
			length--;
		}
	}
	
	/**
	 * Removes the student object passed as parameter
	 * Worst case runtime: O(n)
	 * @param Student
	 * @return boolean (as a means to decide to search/remove from the array or not)
	 */
	public boolean remove(Student s) {
		Node prevNode = head;
		Node currNode = head.next;
		while(currNode != null && currNode.data.compareTo(s) != 0) {
			prevNode = currNode;
			currNode = currNode.next;
		}
		
		if(currNode != null) {
			if(currNode == tail) tail = prevNode; //if removed node is the tail, then update tail to the previous node
			prevNode.next = currNode.next;
			length--;
			return true;
		}
		return false;
	}
}
