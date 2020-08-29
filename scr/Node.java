package dataStructures;

public class Node {
	Student data;
	Node next;
	
	Node(){
		data = null;
		next = null;
	}
	
	Node(Student s){
		data = s;
		next = null;
	}
}
