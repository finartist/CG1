package blatt03;

import java.util.NoSuchElementException;

public class MySList implements Iterable<Integer> {
	class Node {
		int data_;
		Node next_ = null;

		public Node(int data, Node next) {
			data_ = data;
			next_ = next;
		}
		
		public String toString(){
			return "" + data_;
		}
	}

	Node head_ = null;

	public MySList() {
	}

	public int size() {
		int n = 0;
		Node node = head_;
		while (node != null) {
			node = node.next_;
			++n;
		}
		return n;
	}

	public boolean empty() {
		return head_ == null;
	}

	public void push_front(int obj) {
		head_ = new Node(obj, head_);
	}

	public void push_back(int obj) {
		if (head_ == null) // special case!
			head_ = new Node(obj, null);
		else {
			Node node = head_;
			while (node.next_ != null)
				node = node.next_;
			node.next_ = new Node(obj, null);
		}
	}

	public class Iterator implements java.util.Iterator<Integer> {
		Node node_ = null;

		Iterator(Node node) {
			node_ = node;
		}

		public boolean hasNext() {
			Node node = node_;
			// the next node is null OR is uneven
			while(node != null && node.data_%2 == 0){
				node = node.next_;
			}
			
			return node != null;
		}

		public Integer next() {
			Node node = node_;
			if (node_ == null)
				throw new NoSuchElementException();
			// next() gives back the CURRENT data
			int data = node_.data_;
			// we already know, this node has an uneven number, but what is the next?
			node = node.next_;
			// we iterate as long as we hit a node, that has an uneven number or is null
			while(node != null && node.data_%2 == 0){
				node = node.next_;
			}
			// if we found one, we have it as our next node
			node_ = node;
			
			return data;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public boolean equals(Object other) {
			return node_ == ((Iterator) other).node_;
		}
	}

	public Iterator iterator() {
		// also we start with the head, we want the first element to be one with an uneven number
		// the head can also be even or maybe we do not even have an uneven element
		Node node = head_;
		while(node != null && node.data_ %2 == 0){
			node = node.next_;
		}
		return new Iterator(node);
	}

	public static void main(String[] args) {
		MySList list = new MySList();
		list.push_back(1);
		list.push_back(2);
		list.push_back(3);
		list.push_back(4);
		list.push_back(5);
		list.push_back(6);
		list.push_back(7);
		list.push_back(8);
		list.push_back(9);
		list.push_back(11);
		list.push_back(13);
		
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}
}
