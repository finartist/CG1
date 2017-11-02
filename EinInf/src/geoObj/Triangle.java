package geoObj;

class Triangle implements Movable {
	private Point a;
	private Point b;
	private Point c;

	// Constructors
	  //default
	public Triangle(){
		this(new Point(), new Point(0, 1), new Point(1, 0));
	}
	  //3 Points to Triangle
	public Triangle(Point a, Point b, Point c){
		// works just if the Points are different from each other
		if(a.equals(b)||a.equals(c)||b.equals(c)){
			throw new IllegalArgumentException("These three points cannot form a triangle.");
		}
		else{
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	  // from two Lines
	public Triangle(Line one, Line two){
		// works just if the Lines are not the same
		if(one.equals(two) || Math.abs(one.getSlope())== Math.abs(two.getSlope())){
			throw new IllegalArgumentException("These lines cannot form a triangle.");
		}
		else if(one.getStart().equals(two.getStart())){
			this.b = one.getStart();
			this.a = one.getEnd();
			this.c = two.getEnd();
		}
		else if(one.getStart().equals(two.getEnd())){
			this.b = one.getStart();
			this.a = one.getEnd();
			this.c = two.getStart();
		}
		else if(one.getEnd().equals(two.getStart())){
			this.b = one.getEnd();
			this.a = one.getStart();
			this.c = two.getEnd();
		}
		else if(one.getEnd().equals(two.getEnd())){
			this.b = one.getEnd();
			this.a = one.getStart();
			this.c = two.getStart();
		}
		else{
			throw new IllegalArgumentException("These lines cannot form a triangle.");
		}
	}
	
	// Getters
	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public Point getC() {
		return c;
	}

	// other methods
	public String toString() {
		return "This triangle has the point coordinates A:(" + this.a.toString() + ") B:("
				+ this.b.toString() + ") C:(" + this.c.toString() + ") and a perimeter of " 
				+ this.perimeter() + ".";
	}

	public double perimeter() {
		double perimeter = this.a.distance(this.b) + this.b.distance(this.c) + this.c.distance(this.a);
		return perimeter;
	}

	public void translate(int dx, int dy) {
		this.a.translate(dx, dy);
		this.b.translate(dx, dy);
		this.c.translate(dx, dy);
	}
}
