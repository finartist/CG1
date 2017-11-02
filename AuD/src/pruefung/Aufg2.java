package pruefung;

import aud.BinaryTree;
import aud.Queue;

public class Aufg2 {

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
	
}
