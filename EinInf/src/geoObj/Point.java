package geoObj;

class Point implements Movable {
	private int x;
	private int y;

	// Constructors
	public Point(){
		this(0, 0);
	}
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	// Getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// other methods
	public double distance(Point p) {
		double distance = Math.sqrt((p.x-this.x)*(p.x-this.x)+((p.y-this.y)*(p.y-this.y)));
		return distance;
	}

	public void translate(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public String toString() {
		return "x: " + this.x + " y: " + this.y;
	}
}
