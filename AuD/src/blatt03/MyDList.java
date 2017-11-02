package blatt03;
import aud.DList;
import java.util.*;

public class MyDList<T> extends DList<T> {
   public MyDList() {
      super();
   }
 //zum Verketten zweier Listen. Dabei soll die aufrufende Liste verändert werden, die Liste li aber nicht.
   public void append(MyDList<T> li) {
	   Iterator<T> iter;
	   
	   // if append a list to the same list, first copy the list and use an iterator over new list
	   // otherwise we end in infinit loop
	   if(this.equals(li)){
		   MyDList<T> list = new MyDList<T>();
		   // call .append, because if we append to an empty list, we copy it
		   list.append(li);
		   iter = list.iterator();
	   }
	   // we can iterate over parameter list, if we do not append this with this
	   else{
		   iter = li.iterator();
	   }
	   
	   // putting elements to the end of the list
	   while(iter.hasNext()){
		   this.push_back(iter.next());   
	   }
   }

 //zum Einbetten der Liste li in die aufrufende Liste ab Position n (nullbasiert).
   public void insert(int n, MyDList<T> li) {
	   // catching edge cases like n too big or too small
	   if(n >= this.size()){
		   this.append(li);
		   return;
	   }
	   
	   if(n < 0){
		   this.insert(0, li);
		   return;
	   }
	   
	   // same as in append, but we insert it at position n instead of just push it to the back  
	   Iterator<T> iter;

	   if(this.equals(li)){
		   MyDList<T> list = new MyDList<T>();
		   list.append(li);
		   iter = list.iterator();
	   }
	   else{
		   iter = li.iterator();
	   }
	   
	   while(iter.hasNext()){
		   this.insert(n, iter.next());
		   n++;
	   }
   }
   
   public static void main(String[] args) {
      // TODO: implementation
	   MyDList<Integer> list = new MyDList<Integer>();
	   list.push_back(0);
	   list.push_back(1);
	   list.push_back(2);
	   list.push_back(3);
	   
	   System.out.println(list);
	   
	   list.append(list);
	   
	   MyDList<Integer> list1 = new MyDList<Integer>();
	   list1.push_back(100);
	   list1.push_back(200);
	   
	   list.insert(3, list1);
	   
	   System.out.println(list);
	   
	   System.out.println(list1);

   }
}

