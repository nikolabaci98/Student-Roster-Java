package dataStructures;
import java.util.NoSuchElementException;

public class Queue {
	private Node front;
	private Node rear;
	private int length;
	
	public Queue(){
		front = null;
		rear = null;
		length = 0;
	}
	/**
	 * return length of queue
	 * @return
	 */
	public int getLength() {return length;}
	
	/**
	 * Returns front of queue. Used by Roster.java to
	 * access the queue, waitingList, to output students
	 * on the file.
	 * @return front
	 */
	public Node getQueueFront() {return front;}
 
	/**
	 * Adds to the end of the queue.
	 * If queue is empty, then update both front and rear.
	 * If queue is not empty, the update only rear.
	 * Runtime: O(1)
	 * @param s
	 */
	public void enQ(Student s) {
		if(front == null) {
			front = rear = new Node(s);
		}
		else {
			rear= rear.next = new Node(s);
		}
		length++;
	}
	/**
	 * Removes the front of the queue.
	 * If queue is empty, then throw an exception.
	 * If queue has only one node, return the node and update both front and rear.
	 * Otherwise update only rear.
	 * Runtime: O(1)
	 * @return student 
	 */
	public Student deQ() {
		if(isEmpty()) throw new NoSuchElementException("The waiting list is empty.");

		if(front == rear) {
			Student oldStudent = front.data;
			front = front.next;
			rear = null;
			return oldStudent;
		}

		Student oldStudent = front.data;
		front = front.next;
		return oldStudent;
	}

	/**
	 * Peeks in the front of the queue, returns the students, 
	 * but does not remove it from the queue.
	 * @return
	 */
	public Student peek() {
		if(isEmpty()) throw new NoSuchElementException("The waiting list is empty.");
		return front.data;
	}

	public boolean isEmpty() {
		return (front == null);
	}

	public void printQ() {
		Node currNode = front;
		while(currNode != null) {
			System.out.println(currNode.data.toString());
			currNode = currNode.next;
		}
		System.out.println("--------------");
	}
}
