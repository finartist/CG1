package blatt05;

import aud.Queue;
import aud.BinaryTree;
import aud.util.*; // because of DotViewer, SingleStepper;

public class IntBinTree extends BinaryTree<Integer> {

	public IntBinTree(int data) {
		super(data);
	}

	public IntBinTree(int data, IntBinTree left, IntBinTree right) {
		super(data, left, right);
	}

	// computes height of BinaryTree using level order
	public int height() {
		
		int height = 0;
		// having a mark on the queue to know, when level is done
		IntBinTree mark = new IntBinTree(1);
		Queue<BinaryTree<Integer>> queue = new Queue<BinaryTree<Integer>>();
		// this is the first level, so we pu this and a mark in the queue
		queue.enqueue(this);
		queue.enqueue(mark);
		BinaryTree<Integer> temp = this;
		
		while (!queue.is_empty()) {
			//getting a tree from the queue
			temp = queue.dequeue();
			//if we hit a mark, we know one level is done and all children are enqueued
			// so a mark is put on the queue
			if (temp.equals(mark)) {
				height += 1;
				// if queue is empty now, it was the last mark and we have our height
				// else we put the next mark in the queue
				if (!queue.is_empty()) {
					queue.enqueue(mark);
				}
			} 
			// if we have a null reference, we do nothing
			else if (temp == null) {}
			// if the children are not null, we enqueue them
			else {
				if (temp.getLeft() != null)
					queue.enqueue(temp.getLeft());
				if (temp.getRight() != null)
					queue.enqueue(temp.getRight());
			}
		}
		// through levelorder, we have our height by counting marks
		return height;
	}

	public int maxSum() {
		// using a help-function to compute subtree sum and return the max
		return Math.max(_sum(this.getLeft()), _sum(this.getRight()));
	}

	// computes sum of the subtree
	private int _sum(BinaryTree<Integer> tree) {
		// if we hit a null, it's not important for the sum, so we return 0
		if (tree == null) {
			return 0;
		} 
		// if we hit a leave, we return the data
		else if (tree.getLeft() == null && tree.getRight() == null) {
			return tree.getData();
		} 
		// if we hit an internal node, we sum up the data + sum of right subtree and left subtree
		else {
			return tree.getData() + _sum(tree.getLeft()) + _sum(tree.getRight());
		}
	}

	public int maxPath() {
		// using a help-function
		return _maxPath(this);
	}

	private int _maxPath(BinaryTree<Integer> tree) {
		// a null reference can be ignored for maxPath
		if (tree == null) {
			return 0;
		} 
		// if we hit a leave, return the data
		else if (tree.getLeft() == null && tree.getRight() == null) {
			return tree.getData();
		} 
		// only the sum of tree + right OR left path is returned
		// works similar to _sum, but only one side is added
		else {
			return Math.max(tree.getData() + _maxPath(tree.getLeft()), tree.getData() + _maxPath(tree.getRight()));
		}
	}
	
	public int maxLevelSum(){
		   Queue<BinaryTree<Integer>> queue = new Queue<BinaryTree<Integer>>();
		   queue.enqueue(this);
		   queue.enqueue(null);
		   int levelsummax = 0;
		   int levelsumcur = 0;
		   
		   while(!queue.is_empty()){
			   BinaryTree<Integer> curr = queue.dequeue();
			   levelsumcur += curr.getData();
			   if(curr.getLeft() != null){
				   queue.enqueue(curr.getLeft());
			   }
			   if(curr.getRight() != null){
				   queue.enqueue(curr.getRight());
			   }
			   
			   if(queue.front() == null){
				   levelsummax = levelsummax<levelsumcur ? levelsumcur : levelsummax;
				   levelsumcur = 0;
				   queue.dequeue();
				   if(!queue.is_empty()){
					   queue.enqueue(null);
				   }
			   }
		   }
		   return levelsummax;
	   }

	public static void main(String[] args) {

		IntBinTree l4 = new IntBinTree(200);
		IntBinTree l3 = new IntBinTree(-44);
		IntBinTree l2 = new IntBinTree(54);
		IntBinTree l1 = new IntBinTree(23);
		IntBinTree i3 = new IntBinTree(-2, l1, null);
		IntBinTree i2 = new IntBinTree(1, l3, l4);
		IntBinTree i1 = new IntBinTree(67, i3, l2);
		IntBinTree root = new IntBinTree(-2, i1, i2);

		DotViewer viewer = DotViewer.displayWindow(root, "IntBinTree");

		System.out.println(root.height());
		System.out.println(root.maxPath());
		System.out.println(root.maxSum());
		System.out.println(root.maxLevelSum());
	}
}
