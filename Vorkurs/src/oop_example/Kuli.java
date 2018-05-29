package oop_example;

//child class
//"Kuli" class inherits from "Pen" class (keyword "extends")
public class Kuli extends Pen{
	
	//attributes
	  /*inherited attributes
	       length
	       colour
	       writingColour*/
	  //only for Kuli
	private int fillingStatus;
	private int totalFillingStatus;
	
	//constructor
	/*
	 * where is the default constructor? 
	 *   If we do not provide it, there is non.
	 *   Only if there isn't any constructor, the default constructor
	 *   of the base/super class is called
	 */
	public Kuli(int l, String col, String wCol){
		//calls constructor of Pen and sets inherited values like Pen does
		super(l, col, wCol);
		this.fillingStatus = 5*l;
		this.totalFillingStatus = 5*l;
	}
	
	//methods
	
	/*Wait, we already have a write method in "Pen"!
	 * But this method does something different than the one from Pen.
	 * That's what override means -> the head of the method looks the same
	 * but does something different. It's only possible if a class derives 
	 * from another.
	 */
	@Override
	public void write(String myText){
		if(fillingStatus > 0){
			System.out.println(myText);
			fillingStatus--;
		}
		else{
			System.out.println("Please refill Kuli.");		
		}
	}
	
	public void refill(){
		this.fillingStatus = 5*this.getLength();
	}
	
	public double getFillingStatus(){
		return (((double) this.fillingStatus)/ ((double) this.totalFillingStatus))*100.0;
	}
	
	

}
