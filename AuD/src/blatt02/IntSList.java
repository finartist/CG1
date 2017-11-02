package blatt02;

import aud.SList;
//import blatt02.Predicate;

public class IntSList {
	SList<Integer> li;

	public IntSList() {
		li = new SList<Integer>();
	}
	
	public IntSList(int... args){
		li = new SList<Integer>();
		for(int i = 0; i < args.length; i++){
			li.push_back(args[i]);
		}
	}

	public String toString() {
		return li.toString();
	}

	public void push_front(int obj) {
		li.push_front(obj);
	}

	public IntSList filter(Predicate p) {
		IntSList back = new IntSList();
		
		for(int i = 0; i < li.size(); i++){
			if(p.test(li.at(i))){
				back.push_front(li.at(i));
			}
		}
		
		return back;
	}

	public static void main(String args[]) {
		Predicate p = new Predicate()
	    {
	      public boolean test(int el)
	      {
	        return el % 2 == 0;
	      }
	    };
	    
	    Predicate e = new Predicate(){
	    	public boolean test(int n){
	    		if(n == 1){
	    			return false;
	    		}
	    		else if(n == 2){
	    			return true;
	    		}
	    		else if(n % 2 == 0){
	    			return false;
	    		}
	    		else if(n == 3){
	    			return true;
	    		}
	    		else{
	    			// die Schleife muss nur bis ca. zur Hälfte von n gehen, 
	    			// da größere Zahlen als die Hälfte niemals Teiler sein können, 
	    			// ohne einen kleineren Teiler, der schon erfasst worden wäre
	    			for(long i = 3; i <= n/2 + 1; i += 2){
	    				if(n % i == 0){
	    					return false;
	    				}
	    			}
	    			return true;
	    		}
	    	}
	    };
	    
	    IntSList list = new IntSList(6, 85, 72, 93, 81, 74, 42);
	    IntSList list2 = new IntSList(123, 213409, 2871, 2838, 37, 28, 4, 12, 993, 7, 23);
	    
	    System.out.println(list.filter(p).toString());
	    System.out.println(list2.filter(e).toString());
	}
}
