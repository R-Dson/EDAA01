package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> ccomparator;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		ccomparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(x == null)
			return false;
		if(root == null) {
			root = new BinaryNode<E>(x);
			return true;
		}
		return compareNodes(root, x);
	}
	
	private boolean compareNodes(BinaryNode<E> n, E x) {

		int compare = ccomparator.compare(x, n.element);
		boolean temp = false;
		
		if(compare == 0) {
			return temp;
		}
		
		if(compare < 0) {
			if(n.left != null) {
				temp = compareNodes(n.left, x);
			}
			else {
				n.left = new BinaryNode<E>(x);
				temp = true;
			}
		}
		else {
			if(n.right != null) {
				temp = compareNodes(n.right, x);
			}
			else{
				n.right = new BinaryNode<E>(x);
				temp = true;
			}
		}
		
		return temp;
		
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		int i = 0;
		if(root != null) {
			i = treeCounter(root, i);
		}
		return i;
	}
	
	private int treeCounter(BinaryNode<E> n, int count) {
		int count1 = 1;
		int count2 = 1;
		
		if(n.left != null) {
			count1 = treeCounter(n.left, count + 1);
		}
		if(n.right != null) {
			count2 = treeCounter(n.left, count + 1);
		}
		
		return Math.max(count1, count2);
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if(root == null)
			return;
		printNode(root);
	}
	
	private void printNode(BinaryNode<E> n) {
		if(n.left != null) {
			printNode(n.left);
		}
		System.out.println(n.element);
		if(n.right != null) {
			printNode(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
	
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		return null;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
