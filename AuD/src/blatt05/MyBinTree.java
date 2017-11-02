package blatt05;

import aud.BinaryTree;
import aud.Queue;
import aud.util.*;  // because of DotViewer, SingleStepper

public class MyBinTree<T> extends BinaryTree<T>{

	public MyBinTree(T data) {
	super(data);
   }
	
   public MyBinTree(T data,MyBinTree<T> left,MyBinTree<T> right){
	super(data, left, right);
   }
   
   public int maxWidth(){
	   // maximum width
	   int maxW = 0;
	   // current level width
	   int tempW = 0;
	   // having a mark on the queue to know, when level is done
	   BinaryTree<T> mark = new BinaryTree<T>(null);
	   Queue<BinaryTree<T>> queue = new Queue<BinaryTree<T>>();
	   // this is the first level, so we put this and a mark in the queue
	   queue.enqueue(this);
	   queue.enqueue(mark);
	   BinaryTree<T> temp = this;

	   while (!queue.is_empty()) {
		   //getting a tree from the queue
		   temp = queue.dequeue();
		   //if we hit a mark, we know one level is done and all children are enqueued
		   // so a mark is put on the queue
		   if (temp == mark) {
			   // determine if current level is wider than former max Width
			   maxW = tempW > maxW ? tempW : maxW;
			   // clear tempW
			   tempW = 0;
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
			   ++tempW;
			   if (temp.getLeft() != null)
				   queue.enqueue(temp.getLeft());
			   if (temp.getRight() != null)
				   queue.enqueue(temp.getRight());
		   }
	   }
	   // through levelorder, we have our width by counting elements in one level
	   return maxW;
   }
   
   public static void main(String[] args) {
	   MyBinTree<Integer> l4 = new MyBinTree<Integer>(200);
		MyBinTree<Integer> l3 = new MyBinTree<Integer>(-44);
		MyBinTree<Integer> l2 = new MyBinTree<Integer>(54);
		MyBinTree<Integer> l1 = new MyBinTree<Integer>(23);
		MyBinTree<Integer> i3 = new MyBinTree<Integer>(-2, l1, null);
		MyBinTree<Integer> i2 = new MyBinTree<Integer>(1, l3, l4);
		MyBinTree<Integer> i1 = new MyBinTree<Integer>(67, i3, l2);
		MyBinTree<Integer> root = new MyBinTree<Integer>(-2, i1, i2);
		
		System.out.println(root.maxWidth());
		
   }
}

