package oop_example;

//base class 
public class Pen {
	
	//attributes
	private int length;
	private String colour;
	private String writingColour;
	  //"static" means it's an attribute of the class itself!
	  //It exists, even if there is no object of the class!
	private static int numberOfPens = 0;
	
	//constructors
	 //default constructor (no parameters)
	 //if no default constructor written, JVM generates an empty default constructor
	 /*
	  * would look like this:
	  *       public Pen(){
	  *       
	  *       }
	  * and set all attributes to default values 
	  * (int: 0, String: null, boolean: false,...)
	  */
	public Pen(){
		this.length = 1;
		this.colour = "silver";
		this.writingColour = "blue";
		numberOfPens++;
	}
	 //parameterized constructor (constructor with parameters)
	public Pen(int l, String col, String wCol){
		this.length = l;
		this.colour = col;
		this.writingColour = wCol;
		numberOfPens++;
	}
	
	//methods
	public void write(String myText){
		System.out.println(myText);
	}
	
	public static int getNumberOfPens(){
		return Pen.numberOfPens;
	}
	
	public int getLength() {
		return length;
	}
	public String getWritingColour() {
		return writingColour;
	}
	public void setWritingColour(String writingColour) {
		this.writingColour = writingColour;
	}
	public String getColour() {
		return colour;
	}
	
}
