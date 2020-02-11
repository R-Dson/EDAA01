package testqueue;

import static org.junit.Assert.*;

import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {

	@Test
	public void test() {
		//TwoEmptyQueues();
		OneEmptyQueueAppend();
	}
	
	public void TwoEmptyQueues(){
		FifoQueue<Integer> q1 = new FifoQueue<>();
		FifoQueue<Integer> q2 = new FifoQueue<>();
		q1.append(q2);
		assertTrue(q1.size() == 0);
	}

	public void OneEmptyQueueAppend() {
		FifoQueue<Integer> q1 = new FifoQueue<>();
		FifoQueue<Integer> q2 = new FifoQueue<>();
		for(int i = 1; i <= 5; i++) {
			q2.offer(i);
		}
		q1.append(q2);
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
 	}
	
}
