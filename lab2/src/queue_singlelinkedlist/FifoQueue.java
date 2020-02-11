package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		try {
			QueueNode<E> temp = new QueueNode<>(e);
			if (last != null) {
				temp.next = last.next;
				last.next = temp;
				last = temp;
			} else {
				last = temp;
				last.next = temp;
			}
			size++;
			return true;

		} catch (Exception errorororor) {

		}

		return false;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (last != null) {
			return last.next.element;
		} else {
			return null;
		}
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		E e = null;
		if (last != null) {
			e = last.next.element;
			QueueNode<E> temp = last.next;
			last.next = temp.next;
			size--;
			if(size == 0) {
				last = null;
			}
		}

		return e;
	}
	
	public void append(FifoQueue<E> q) {
		QueueNode<E> e = null;
		if(q != null) {
			if(q.size() == 0) {
				if(q.last == last) {
					throw new IllegalArgumentException();
				}
				return;
			}

			if(this.size == 0)
			{
				last = q.last;
				size = q.size;
				q.last = null;
				q.size = 0;
				return;
			}
			
			if(this.last != q.last) {
				size = size + q.size();
				
				e = last.next;
				last.next = q.last.next;
				last = q.last;
				last.next = e;
				q.last = null;
				q.size = 0;
				return;
			}
			
			else {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {

		private QueueNode<E> pos;
		int count = 0;
		
		public QueueIterator() {
			if(last != null) {
				pos = last.next;
			}else {
				pos = null;
			}
		}

		@Override
		public boolean hasNext() {
			
			if (count < size) {
				return true;
			}else {
				return false;
			}		

		}

		@Override
		public E next() {
			if(hasNext()) {
				E e = pos.element;
				pos = pos.next;
				count++;
				return e;
			} else {
				throw new NoSuchElementException();
			}

		}

	}

}
