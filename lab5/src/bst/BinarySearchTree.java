package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E extends Comparable<E>> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	private int size; // Används också i BSTVisaulizer
	private Comparator<E> ccomparator;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;

	}

	public static void main(String[] args) {
		BSTVisualizer visual = new BSTVisualizer("tree baby", 1000, 500);
		ArrayList list = new ArrayList();
		/*BinarySearchTree tree = new BinarySearchTree();
		tree.add("hej");
		tree.add("ih");
		tree.add("zyx");
		tree.add("a");
		tree.add("a");
		BinarySearchTree tree2 = new BinarySearchTree();
		
		for(int i = 0; i < 8; i++) {
			tree2.add(i);
		}
		for (int i = 0; i < 100; i = i + 3) {
			tree2.add((int) (Math.random() * 100));
		}
		tree2.toArray(tree2.root, list);
		tree2.buildTree(list, 0, list.size()-1);
		visual.drawTree(tree2);*/
		
		BinarySearchTree<Integer> tree3 = new BinarySearchTree<>();
		for(int i = 8; i < 26; i++) {
			tree3.add(i);
		}
		for(int i = -10; i < 8; i++) {
			tree3.add(i);
		}
		tree3.toArray(tree3.root, list);
		tree3.buildTree(list, 0, list.size()-1);
		visual.drawTree(tree3);
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		ccomparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (x == null)
			return false;
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		return compareNodes(root, x);
	}

	private boolean compareNodes(BinaryNode<E> n, E x) {
		int compare = 0;
		if (ccomparator == null)
			compare = ((Comparable<E>) x).compareTo(n.element);
		else
			compare = ccomparator.compare(x, n.element);
		boolean temp = false;

		if (compare == 0) {
			return temp;
		}

		if (compare < 0) {
			if (n.left != null) {
				temp = compareNodes(n.left, x);
			} else {
				n.left = new BinaryNode<E>(x);
				temp = true;
				size++;
			}
		} else {
			if (n.right != null) {
				temp = compareNodes(n.right, x);
			} else {
				n.right = new BinaryNode<E>(x);
				temp = true;
				size++;
			}
		}

		return temp;

	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		int i = 0;
		if (root != null) {
			i = treeCounter(root, i);
		}
		return i;
	}

	private int treeCounter(BinaryNode<E> n, int count) {
		int count1 = 1;
		int count2 = 1;

		if (n.left != null) {
			count1 = treeCounter(n.left, count + 1);
		}
		if (n.right != null) {
			count2 = treeCounter(n.left, count + 1);
		}

		return Math.max(count1, count2);
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
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
		size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if (root == null)
			return;
		printNode(root);
	}

	private void printNode(BinaryNode<E> n) {
		if (n.left != null) {
			printNode(n.left);
		}
		System.out.println(n.element);
		if (n.right != null) {
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
		if (n.left != null) {
			toArray(n.left, sorted);
		}
		sorted.add(n.element);
		if (n.right != null) {
			toArray(n.right, sorted);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (sorted != null) {
			root = buildTreeRec(sorted, first, last);
		}

		return root;
	}

	private BinaryNode<E> buildTreeRec(ArrayList<E> sorted, int first, int last) {
		BinaryNode<E> temp = new BinaryNode<E>(sorted.get((last-first)/2 + first));
		if(last-first == 1) {
			temp.right = new BinaryNode<E>(sorted.get(last));
		}
		if (last - first > 1) {
			temp.left = buildTreeRec(sorted, first, (last - first) / 2 - 1 + first);
			temp.right = buildTreeRec(sorted, (last - first) / 2 + 1 + first, last);
		}
		return temp;
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
