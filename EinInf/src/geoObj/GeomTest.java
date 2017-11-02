package geoObj;

public class GeomTest {

	public static void main(String[] args) {
		// Point Test
		Point p1 = new Point(4, 5);
		Point p2 = new Point();
		Point p3 = new Point(-5, 9);
		
		System.out.println("Point 1 is " + p1.toString() + " and has a distance to Point 2 of " 
		+ p1.distance(p2));
		
		System.out.println("Distance p1 to p3: " + p1.distance(p3));
		System.out.println("Distance p2 to p3: " + p2.distance(p3));
		System.out.println("p2 before translation: " + p2.toString());
		p2.translate(5, -17);
		System.out.println("p2 after translation: " + p2.toString());
		System.out.println("");
		
		//Line Test
		Line lin1 = new Line(p1, p2);
		System.out.println("Line 1 " + lin1.toString());
		Line lin2 = new Line(p3);
		System.out.println("Line 2 before translation: " + lin2.toString());
		lin2.translate(5, 0);
		System.out.println("Line 2 after translation: " + lin2.toString());
		Line lin3 = new Line(p2,p3);
		System.out.println("Line 3 " + lin3.toString());
		System.out.println("");
		
		//Triangle Test
		Triangle t1 = new Triangle();
		System.out.println("t1 " + t1.toString());
		
		try{
		Triangle t2 = new Triangle(new Line(new Point(), new Point(1,1)), new Line(new Point(), new Point(2,2)));
		}
		catch(IllegalArgumentException e){
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
		
		Triangle t3 = new Triangle(p1, p2, p3);
		System.out.println("t3 before translation: " + t3.toString());
		t3.translate(-8, 3);
		System.out.println("t3 after translation: " + t3.toString());
	}

}
