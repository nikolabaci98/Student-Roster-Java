package dataStructures;

public class BST {
	private BSTNode root;
	
	BST(){
		root = null;
	}
	
	/**
	 * Calls the recursive private method insert(BSTNode, Student),
	 * which inserts the tree node in the right place.
	 * @param s
	 */
	public void insert(Student s) {
		root = insert(root, s);
	}
	
	/**
	 * Recursive method that finds the right place to insert the
	 * new tree node
	 * Runtime: Worst case O(n)
	 * 			Best case O(lg n)
	 * @param t
	 * @param s
	 * @return
	 */
	private static BSTNode insert(BSTNode t, Student s) {
		if(t == null) {
			t = new BSTNode(s);
		}
		else {
			if(t.data.compareTo(s) > 0)
				t.left = insert(t.left, s);
			else if (t.data.compareTo(s) < 0)
				t.right = insert(t.right, s);
		}
		return t;
	}
	
	/**
	 * Removes the given node from the BST tree. It finds the node,
	 * the it determines the position of the node: is it internal node or
	 * a leaf or the root. Also it determines if the node has only one child
	 * left or right, and depending on the case, the node is removed accordingly.
	 * @param s
	 * @return
	 */
	public boolean remove(Student s) {
		BSTNode parent = null;
		BSTNode currNode = root;
		while(currNode != null) {
			if(currNode.data.compareTo(s) == 0) { //when the node is found, enter the if statement
				if(currNode.left == null && currNode.right == null) { //leaf-node case
					if(parent == null)
						root = null;
					else if(parent.left == currNode)
						parent.left = null;
					else
						parent.right = null;
				}
				else if(currNode.left != null && currNode.right == null) { //right-child-only case
					if(parent == null)
						root = currNode.left;
					else if(parent.left == currNode)
						parent.left = currNode.left;
					else
						parent.right = currNode.left;		
				}
				else if(currNode.left == null && currNode.right != null) { //left-child-only case
					if(parent == null)
						root = currNode.right;
					else if(parent.left == currNode)
						parent.left = currNode.right;
					else
						parent.right = currNode.right;		
				}
				else {								//internal-node case
					BSTNode suc = currNode.right;
					while(suc.left != null)
						suc = suc.left;
					Student sucData = suc.data;
					remove(suc.data);
					currNode.data = sucData;
				}
				return true;
			}
			else if(currNode.data.compareTo(s) > 0) {	//keep searching the node
				parent = currNode;
				currNode = currNode.left;
			}	
			else {
				parent = currNode;
				currNode = currNode.right;
			}
			
		}
		return false;
	}

	/**
	 * Returns the private instance variable, root of BST.
	 * @return
	 */
	public BSTNode getRoot() {return root;}
	
	/**
	 * Search the tree by the name of the student. It calls
	 * the searchResults(BSTNode, String, String, LinkedList) method,
	 * which is recursive and appends the students in the Linked List.
	 * Runtime: O(n)
	 * @param name
	 * @param lastName
	 * @return
	 */
	public LinkedList treeSearch(String name, String lastName) {
		LinkedList results = new LinkedList();
		return results = searchResults(root, name, lastName, results) ;
	}
	
	/**
	 * It searches the BST for the student(s) that match the name
	 * Searches recursively, using in-order traversal.
	 * Runtime: O(n)
	 * @param t
	 * @param name
	 * @param lastName
	 * @param results
	 * @return LinkedList
	 */
	private LinkedList searchResults(BSTNode t, String name, String lastName, LinkedList results) {
		if(t != null) {
			searchResults(t.left, name, lastName, results);
			if(t.data.compareByName(name, lastName) == 0)
				results.append(t.data);
			searchResults(t.right, name, lastName, results);
		}
		return results;
	}
	
	/**
	 * Calls printBST(BSTNode) method, which print 
	 * the BST recursively using in-order traversal.
	 */
	public void printBST() {
		if(root!= null) {
			printBST(root);
		}
	}
	/**
	 * Prints the BST recursively using in-order traversal.
	 * @param bst
	 */
	private void printBST(BSTNode bst) {
		if(bst != null) {
			printBST(bst.left);
			System.out.println(bst.data.toString());
			printBST(bst.right);
		}
	} 
}
