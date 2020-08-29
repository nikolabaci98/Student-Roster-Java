package dataStructures;

public class BSTNode {
	Student data;
	BSTNode parent;
	BSTNode left;
	BSTNode right;
	
	BSTNode(){
		data = null;
		parent = null;
		left = null;
		right = null;
	}
	
	BSTNode(Student s){
		data = s;
		parent = null;
		left = null;
		right = null;
	}
}
