package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test
	void EmptySizeTest() {
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

}
