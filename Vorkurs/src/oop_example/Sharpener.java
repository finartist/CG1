package oop_example;

public class Sharpener {
	
	//only static method
	public static void sharpen(Pencil pencil){
		pencil.setDurability(pencil.getDurability() + 25);
		if(pencil.getDurability() > 50){
			pencil.setPointy(true);
		}
	}
}
