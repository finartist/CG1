package oop_example;

public class Program {
	public static void main(String[] args) {
		
		System.out.println("We created: " + Pen.getNumberOfPens() + " pen(s).");
		
		Kuli myFavouriteKuli = new Kuli(6, "Red", "Black");
		System.out.println("We created: " + Pen.getNumberOfPens() + " pen(s).");
		
		System.out.println("My Kuli is filled " + myFavouriteKuli.getFillingStatus() + "%.");
		myFavouriteKuli.write("I love writing with my favourite Kuli.");
		myFavouriteKuli.write("My Kuli is a kind of pen.");
		System.out.println("My Kuli is filled " + myFavouriteKuli.getFillingStatus() + "%.");
		
		myFavouriteKuli.refill();
		System.out.println("My Kuli is filled " + myFavouriteKuli.getFillingStatus() + "% after refilling.");
		
		Pencil myMathPencil = new Pencil();
		System.out.println("We created: " + Pen.getNumberOfPens() + " pen(s).");
		
		while(myMathPencil.isPointy()){
			myMathPencil.write("I like drawing diagrams with my pointy pencil.");
		}
		System.out.println("This pencil is pointy: " + myMathPencil.isPointy());
		
		//no object required to call sharpen
		Sharpener.sharpen(myMathPencil);
		System.out.println("This pencil is pointy: " + myMathPencil.isPointy());
	}
}
