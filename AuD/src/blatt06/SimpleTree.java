package blatt06;

import java.util.Stack;

public class SimpleTree<T extends Comparable<T>> implements Iterable<T>{
	// Node Class for SimpleTree Nodes
	private class Node {
		T    data_ = null;
		Node left_ = null;
		Node right_ = null;
		Node parent_ = null;

		public Node(T obj,
				Node left, Node right, Node parent) {
			data_=obj; left_=left; right_=right; parent_=parent;
		}

		public T getData(){
			return this.data_;
		}
	}
	
	// Root Node for Tree
	private Node root;
	
	// Constructor
	public SimpleTree() {	
		this.root = null;
	}
	
	// insert Node in Tree
	public void insert(T obj) {
		// simple case -> very first Node inserted is root
		if(root == null){
			root = new Node(obj, null, null, null);
		}
		// otherwise insert in binary search tree structure
		else{
			_insert(this.root, obj);
		}
	}

	// will insert a Node at correct spot in search tree as leaf
	private void _insert(Node n, T obj){
		// if it already exists, Node will not be inserted
		if(n.getData().compareTo(obj) == 0){
			System.out.println("Tree already contains " + obj.toString());		
		}
		// If insert data is smaller than Node value, go left 
		else if(n.getData().compareTo(obj) > 0){
			// if left child is empty(null) insert data there
			if(n.left_ == null){
				n.left_ = new Node(obj, null, null, n);
			}
			else{
				_insert(n.left_, obj);
			}
		}
		// If insert data is bigger than Node value, go right 
		else if(n.getData().compareTo(obj) < 0){
			//if right child is empty, insert there
			if(n.right_ == null){
				n.right_ = new Node(obj, null, null, n);
			}
			else{
				_insert(n.right_, obj);
			}
		}
	}
	
	// search for a value
	public boolean search(T obj) {	
		return _search(root, obj);
	}
	
	// works like insert, but just checking for values and do not insert new
	private boolean _search(Node n, T obj){
		if(n == null){
			return false;
		}
		else if(n.getData().compareTo(obj) > 0){
			return _search(n.left_, obj);
		}
		else if(n.getData().compareTo(obj) < 0){
			return _search(n.right_, obj);
		}
		else{
			return true;
		}
	}
	
	// iterate preorder and print it out
	public String toString() {
		String s = "";
		for(T i : this){
			s += i + ", ";
		}
		return s;
	}
	
	// iterator for preorder
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(root);
	}

	public class Iterator<T> implements java.util.Iterator<T>{

		Stack<Node> stack = new Stack<Node>();
		public Iterator(Node n){
			this.stack.push(n);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T  next() {
			Node node=stack.pop(); // may throw!
			if (node.right_!=null){
				stack.push(node.right_);
			}
			if (node.left_!=null){
				stack.push(node.left_);
			}
			return (T) node.data_;
		}
	}

	public static void main(String[] args) {
		SimpleTree<Integer> tree = new SimpleTree<Integer>();
		tree.insert(6);
		tree.insert(3);
		tree.insert(8);
		System.out.println(tree.toString());
		tree.insert(9);
		tree.insert(4);
		tree.insert(2);
		tree.insert(7);
		System.out.println(tree.toString());
		System.out.println(tree.search(3));
		System.out.println(tree.search(16));
		
		SimpleTree<Integer> unbalanced = new SimpleTree<Integer>();
		unbalanced.insert(20);
		unbalanced.insert(5);
		unbalanced.insert(25);
		unbalanced.insert(11);
		unbalanced.insert(10);
		unbalanced.insert(14);
		unbalanced.insert(17);
		unbalanced.insert(22);
		unbalanced.insert(27);
		unbalanced.insert(30);
		unbalanced.insert(33);
		System.out.println(unbalanced.toString());
	}
}
