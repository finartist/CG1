package blatt03;

import java.util.NoSuchElementException;

public class MyCircle<T> {
		// of course we need nodes, just like in DList
	   class Node {
		T data_ = null;
		Node next_ = null;
		Node prev_ = null;

		Node(T obj, Node prv, Node nxt) {
			data_ = obj;
			prev_ = prv;
			next_ = nxt;
		}
	   }
	   protected Node head_ = null;
	   public MyCircle() {
	      // do not change
	      head_ = null;
	   }
	   public T front() {
	      return head_.data_;
	   }
	   public String toString() {
	      // do not change (because of backend-control)
	      if (empty())
		return "[]";
	      String rv = "[";
	      Node node = head_;
	      do{
		rv += node.data_.toString();
		if (node.next_ != head_)
		   rv += ",";
		node = node.next_;
	      }while(node != head_);
	      rv += "]";
	      return rv;
	   }
	   
	   // how many elements are in the circle
	   public int size() {
		   int n=0;
		   // beginning with the head_
		    Node node = head_;
		    //just if the node is not null and the next node is not the head
		    // we enter the loop, next node id not allowed to be head, because
		    // of infinite loop or only one element, where the head_ points to the head_
		    while (node != null && !node.next_.equals(head_)) {
		      node=node.next_;
		      ++n;
		    }
		    // if head is null, there is no other element in the circle
		    if(head_ == null){
		    	return n;
		    }
		    // if the head_ is the next element, we need to count the current node too
		    // (could be the head_ as the only element
		    if(node.next_.equals(head_)){
		    	++n;
		    }
		    return n;	
	   }
	   public boolean empty() {
		   // if head_ is null, we do not have any elements
	      return (head_ == null);
	   }
		
	   public void push_back(T obj) {
		   // if we start with the empty circle, the first element is the head_
		   // it points to the head_ in every direction we go
		   if(empty()){
			   head_ = new Node(obj, null, null);
			   head_.next_ = head_;
			   head_.prev_ = head_;
		   }
		   else{
			   // if we have just one element, the head points to the new element as next
			   // and as previous so it is head <-> new <-> head
			   if(size() == 1){
				   head_.prev_ = new Node(obj, head_, head_);
				   head_.next_ = head_.prev_;
			   }
			   // if we have more than 1 element, the one pointing to the head, have to point
			   // to the new as next and the previous one from the head_ has to be the new
			   // before: head.prev <-> head <-> next
			   // after:  head.prev <-> new <-> head
			   else{
			   head_.prev_.next_ = new Node(obj, head_.prev_, head_);
			   head_.prev_ = head_.prev_.next_;
			   }
		   }
	   }
	   
	   public void pop_front() {
		   // we cannot discard any non existent element
		   if(this.empty()){
			   throw new NoSuchElementException();
		   }
		   // if we have just the head, we set it null -> no more elements in the list after pop
		   else if(head_.next_ == head_){
			   head_ = null;
		   }
		   // we put the head out of the list, the next one from the head is the new head
		   // before head.prev <-> head <-> head.next
		   // after head.prev <-> head.next
		   else{
			   head_.prev_.next_ = head_.next_;
			   head_.next_.prev_ = head_.prev_;
			   head_ = head_.next_;
		   }
	   }
		
	   public static void main(String[] args) {
		   
		   // fun with circles
		
		   MyCircle<Integer> circle = new MyCircle<Integer>();
		   
		   circle.push_back(1);
		   circle.push_back(2);
		   circle.push_back(3);
		   circle.push_back(4);
		   circle.push_back(5);
		   
		   System.out.println(circle);
		   
		   circle.pop_front();
		   
		   System.out.println(circle);
		   
		   circle.push_back(6);
		   System.out.println(circle);
		   
		   circle.pop_front();
		   
		   System.out.println(circle);
		   
		   circle.pop_front();
		   System.out.println(circle);
		   circle.pop_front();
		   System.out.println(circle);
		   System.out.println(circle.size());
		   circle.pop_front();
		   circle.pop_front();
		   System.out.println(circle);
		   System.out.println(circle.size());
		   
		   
	   }
	}
