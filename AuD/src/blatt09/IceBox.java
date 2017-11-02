package blatt09;

import java.util.ArrayList;

import blatt09.Food.Type;

public class IceBox{
		int[] primes = { 23, 47, 97, 197, 397, 797, 1597};
		int currPrimeIndex = 0;
	   // TODO: attributes and constructor
		// having lists in an array, because in a fridge can be more than one thing per bucket
		// array size of 5 because fridges normally do not have so much of space
		ArrayList<Food>[] box = (ArrayList<Food>[]) new ArrayList[11];
		private int size; // current number of elements

		public IceBox(){
			size = 0;
			for(int i = 0; i < box.length; ++i){
				box[i] = new ArrayList<Food>();
			}
		}
		
		private void resize(){
			// assuming 5 Elements per Bucket if hash function is very good
			// close to a real fridge
			if(size >= box.length*5){
				ArrayList<Food>[] newBox = (ArrayList<Food>[]) new ArrayList[primes[currPrimeIndex]];
				currPrimeIndex++;
				
				for(int i = 0; i < newBox.length; ++i){
					newBox[i] = new ArrayList<Food>();
				}
				for(int j = 0; j < box.length; ++j){
					for(Food f : box[j]){
						int code = f.hashCode()%box.length;
				    	newBox[code].add(f);
					}
				}
				box = newBox;
			}
		}
		
	    public void add(Food obj) {
	       // TODO: attributes and constructor
	    	resize();
	    	int code = obj.hashCode()%box.length;
	    	box[code].add(obj);
	    	size++;
	    }
	    
	    public boolean contains(Food obj) {
	    	int code = obj.hashCode()%box.length;
	       ArrayList<Food> bucket = box[code];
	       for(Food f : bucket){
	    	   if(f == obj){
	    		   return true;
	    	   }
	       }
	       return false;
	    }
	    
	    public int getSize(){
	    	return size;
	    }
	    
	    public String toString(){
	    	String s = "";
	    	for(int i = 0; i < box.length; ++i){
	    		ArrayList<Food> bucket = box[i];
	    		s += "Bucket " + i + " contains " + bucket.size() + " elements: " + bucket.toString() + "\n\n";
	    	}
	    	return s;
	    	
	    }
	    
	    public static void main(String[] args) {
	      // TODO: test
	    	Food melon = new Food(Type.FRUIT, "Melon", 50);
	    	Food zucchini = new Food(Type.VEGETABLES, "Zucchini", 30);
	    	Food mayonnaise = new Food(Type.DIVERSE, "Mayonnaise", 100);
	    	Food eggsalad = new Food(Type.EGGPRODUCTS, "Eggsalad", 83);
	    	Food milk = new Food(Type.MILKPRODUCTS, "Milk", 44);
	    	Food salami = new Food(Type.FLESH, "Salami", 73);
	    	Food fruitsalad = new Food(Type.FRUIT, "Fruitsalad", 56);
	    	Food tomato = new Food(Type.VEGETABLES, "Tomato", 30);
	    	Food pudding = new Food(Type.DIVERSE, "Pudding", 66);
	    	Food joghurt = new Food(Type.MILKPRODUCTS, "Joghurt", 46);
	    	
	    	IceBox b = new IceBox();
	    	b.add(melon);
	    	b.add(zucchini);
	    	b.add(mayonnaise);
	    	b.add(eggsalad);
	    	b.add(milk);
	    	b.add(salami);
	    	b.add(fruitsalad);
	    	b.add(tomato);
	    	b.add(pudding);
	    	b.add(joghurt);
	    	
	    	System.out.println(b.toString());
	    }
	}