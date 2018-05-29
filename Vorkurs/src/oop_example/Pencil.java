package oop_example;

public class Pencil extends Pen{
	
	private boolean isPointy = true;
	private int durability = 100;
	
	public Pencil(){
		super(8, "Blue", "silverish");
	}
	
	public boolean isPointy() {
		return isPointy;
	}

	public void setPointy(boolean isPointy) {
		this.isPointy = isPointy;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	@Override
	public void write(String myText){
		if(durability > 0){
			System.out.println(myText);
			durability -= 5;
			if(durability < 50){
				isPointy = false;
				System.out.println("You have to sharpen your pencil. It's getting blunt.");
			}
		}
		else{
			System.out.println("You cannot write with your pencil anymore.");
		}
	}
}
