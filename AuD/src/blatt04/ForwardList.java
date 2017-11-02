package blatt04;

import aud.Stack;

public class ForwardList<T> implements Iterable<T>{
	class Node {
		T data_;
		Node next_ = null;

		public Node(T data, Node next) {
			data_ = data;
			next_ = next;
		}
	}

	Node head_ = null;

	public ForwardList() {
	}

	public void push_front(T obj) {
		head_ = new Node(obj, head_);
	}
	
	public String toString(){
		Node n = head_;
		String res = "";
		while(n != null){
			res += n.data_ + " ";
			n = n.next_;
		}
		return res;
	}

	public void backTraverse(){
		String res = "";
		for(T elem : this){
			res += elem + " ";
		}
		System.out.println(res);
	}
	
	public class BackIterator implements java.util.Iterator<T> {
		
		Stack<T> stack = new Stack<T>();

		public BackIterator(Node node){
			Node n = node;
			while(n != null){
				stack.push(n.data_);
				n = n.next_;
			}
		}
		
		@Override
		public boolean hasNext() {
			return !stack.is_empty();
		}

		@Override
		public T next(){
			return stack.pop();
		}		
	}

	public BackIterator iterator() {
		return new BackIterator(head_);
	}
	
	public static void main(String[] args) {
		ForwardList<String> list = new ForwardList<String>();
		list.push_front("dir?");
		list.push_front("es");
		list.push_front("geht");
		list.push_front("Wie");
		
		System.out.println(list);
		list.backTraverse();
		
		ForwardList<Integer> list1 = new ForwardList<Integer>();
		list1.push_front(5);
		list1.push_front(4);
		list1.push_front(3);
		list1.push_front(2);
		list1.push_front(1);
		
		System.out.println(list1);
		for(Integer in : list1){
			System.out.println(in);
		}
	}
}

