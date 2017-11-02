package blatt08;

import java.util.*;
public class MaxHeap<T extends Comparable<T>> {
	
   private ArrayList<T> heap;  // or Vector
   
   public MaxHeap() {
      heap = new ArrayList<T>();
   }

   public MaxHeap(T[] a) {
	  heap = new ArrayList<T>();
	  for(int i = 0; i < a.length; ++i){
		  heap.add(0, a[i]);
		  this.downHeap(0);
	  }
   }	
 
   public ArrayList<T> getHeap(){
	   return heap;
   }
   
   public int size() {
      return heap.size();
   }
   
   public boolean is_empty() {      
      return heap.size() == 0;
   }
   
   public void downHeap(int k) {
	   //k is 0-based index
	   //so can use k for getting date of node we want to insert and downheap
	   T x = heap.get(k);
	   //as long as k is smaller or equal half of the heapsize
	   while (k < heap.size()/2){
		   // getting the left child index (0-based)
		   int j =2*(k+1) -1;
		   // if there is also a right child, compare those two and use index of bigger one
		   if (j<heap.size()-1 && heap.get(j).compareTo(heap.get(j +1)) < 0){
			   j = j+1;
		   }
		   // if child is smaller than inserted data, break the loop, cause new position was find
		   if (x.compareTo(heap.get(j)) >= 0){
			   break;
		   }
		   // get the child in upper node
		   heap.set(k, heap.get(j));
		   // position of new data is former child's position
		   k = j;
	   }
	   // set child node new data
	   heap.set(k, x);
	   
   }
   
   public void insert(T obj) {
      heap.add(obj);
      upHeap(heap.size()-1);
   }
   
   private void upHeap(int k) {
	   // new inserted data on position k (0-based)
	   T x = heap.get(k);
	   // while k smaller than first index and new data is bigger than parent
	   while(k > 0 && x.compareTo(heap.get((k+1)/2-1)) > 0){
		   // pull parent node of new data down
		   heap.set(k, heap.get((k+1)/2-1));
		   // check grandparent
		   k = (k+1)/2 - 1;
	   }
	   // place new entry to right position
	   heap.set(k, x);
   }
	
   public String toString() {
	   String s = "";
	   for(T el : heap){
		   s += el.toString() + " ";
	   }
	   return s;
   }
   
   public static void main(String[] args) {
      String[] test = {"X", "T", "O", "G", "S", "M", "N", "A", "E", "R", "A" ,"I"};
      MaxHeap<String> heapdown = new MaxHeap<String>(test);
      System.out.println(heapdown.toString());
      
      MaxHeap<String> heapup = new MaxHeap<String>();
      for(int i = 0; i < test.length; ++i){
    	  heapup.insert(test[i]);
      }
      System.out.println(heapup.toString());
   }
}
