package blatt06;

import java.util.Arrays;
import java.util.Random;

public class Food implements Comparable<Food> {

	private static final String[] TYPES = { "Apple", "Pear", "Cookie" };
	
	private int calories;
	private String type;

	public Food(String type, int calories) {
		// Test if valid type
		for(int i = 0; i < 3; i++){
			if(type.equals(Food.TYPES[i])){
				this.type = type;
			}
		}
		// if no valid type, default food is a cookie
		if(this.type == null){
			System.out.println("You have not entered a valid type. This is now a cookie.");
			this.type = "Cookie";
		}
		// calories are always positive
		this.calories = Math.abs(calories);
	}

	public String toString() {
		return "This is food of the type " + this.type + " with " + this.calories + " calories.";
	}

	public int compareTo(Food o) {
		int thisFood = this.calories*this.type.length();
		int compareFood = o.getCalories()*o.getType().length();
		
		if(thisFood < compareFood){
			return -1;
		}
		if(thisFood == compareFood){
			return 0;
		}
		else{
			return 1;
		}
	}
	
	// Getter
	public String getType() {
		return this.type;

	}

	public int getCalories() {
		return this.calories;
	}

	public static Food[] createSortedRandomList(int n) {
		Random rng = new Random();
		Food[] randFood = new Food[n];
		for(int i = 0; i < n; i++){
			int randIndex = rng.nextInt(3);
			int randCal = rng.nextInt(1000)+1;
			randFood[i] = new Food(Food.TYPES[randIndex], randCal);
		}
		Arrays.sort(randFood);
		return randFood;
	}

	public static void main(String[] args) {
		Food pear = new Food("Pear", 200);
		System.out.println(pear.toString());
		Food notValid = new Food("Rice", 800);
		System.out.println(notValid.toString());
		Food apple = new Food("Apple", 160);
		
		System.out.println(pear.compareTo(notValid));
		System.out.println(notValid.compareTo(pear));
		System.out.println(apple.compareTo(pear));
		
		Food[] foodList = Food.createSortedRandomList(4);
		for(int i = 0; i < foodList.length; i++){
			System.out.println(foodList[i].toString());
		}
		
	}

}
