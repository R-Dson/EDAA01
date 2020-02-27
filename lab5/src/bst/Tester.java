package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class Tester {

	@Test
	public void EmptySizeTest() {
		BinarySearchTree tree = new BinarySearchTree();
		assertEquals("tree has correct size", tree.size(), 0);
	}
	
	@org.junit.jupiter.api.Test
	void NonEmptySizeTest(){
		BinarySearchTree tree = new BinarySearchTree();
		tree.add(1);
		tree.add(2);
		tree.add(0);
		assertEquals("tree has correct size", tree.size(), 3);
	}
	
	@org.junit.jupiter.api.Test
	void printReverseTree(){
		BinarySearchTree tree = new BinarySearchTree((x1, x2) -> ((int) x2)-((int) x1));
		tree.printTree();
		tree.add(1);
		tree.add(10);
		tree.add(8);
		tree.add(0);
		System.out.println("------");
		tree.printTree();
	}
	
	@org.junit.jupiter.api.Test
	void printTree(){
		BinarySearchTree tree = new BinarySearchTree();
		tree.printTree();
		tree.add(1);
		tree.add(10);
		tree.add(8);
		tree.add(0);
		System.out.println("------");
		tree.printTree();
	}
	
	@org.junit.jupiter.api.Test
	void clearTree() {
		BinarySearchTree tree = new BinarySearchTree();
		tree.add(1);
		tree.add(10);
		tree.add(8);
		tree.add(0);
		tree.clear();
		assertEquals("tree has root null", tree.root, null);
		assertEquals("tree has correct size", tree.size(), 0);
	}
	
	@org.junit.jupiter.api.Test
	void addDouble() {
		BinarySearchTree tree = new BinarySearchTree();
		tree.add(1);
		tree.add(1);
		tree.add(8);
		tree.add(8);
		assertEquals("tree has correct size", tree.size(), 2);
	}
	
	@org.junit.jupiter.api.Test
	void stringTesting() {
		BinarySearchTree tree = new BinarySearchTree();
		tree.add("hej");
		tree.add("ih");
		tree.add("zyx");
		tree.add("a");
		tree.add("a");
		System.out.println("------");
		tree.printTree();
		assertEquals("tree has correct size", tree.size(), 4);
	}

}
