package dataStructures;

public class Iterator {

	Node iter;
	/**
	 * Instantiates the iterator to the dummynode
	 * 
	 * @param LinkedList
	 */
	public Iterator(LinkedList list) {
		iter = list.getHead(); 
	}

	public Iterator(Node front) {
		iter = front;
	}
	/**
	 * Checks if the list has another node after the current one.
	 * @return boolean
	 */
	public boolean hasNext() {
		if(iter.next == null) return false;
		return true;
	}
	
	/**
	 * Moves to the next node and returns the data of the node. Data of node is a student object.
	 * If iterator is at the end (null) then it returns null
	 * Runtime: O(1)
	 * @return Student object or null
	 */
	public Student next() { 
		if(hasNext()) {
			iter = iter.next;
			return iter.data;
		}
		return null;
	}
}
