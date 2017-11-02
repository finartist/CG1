package geoObj;

class Line implements Movable {
	private Point start;
	private Point end;
	private double slope;

	//Getters	
	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}

	public double getSlope(){
		return slope;
	}
	
	//Constructors
	public Line(){
		this(new Point(), new Point(1,0));
	}
	
	public Line(Point start, Point end){
		this.start = start;
		this.end = end;
		this.slope = (this.end.getY()-this.start.getY())/(this.end.getX()-this.start.getX());
	}
	
	public Line(Point end){
		this(new Point(), end);
	}

	// other methods
	public double length() {
		return this.start.distance(this.end);
	}

	public void translate(int dx, int dy) {
		this.start.translate(dx, dy);
		this.end.translate(dx, dy);
	}

	public String toString() {
		return "This line has the start point " + this.start.toString() + " and the end point " 
				+ this.end.toString() + ". It has a length of " + this.length() + ".";
	}
}
