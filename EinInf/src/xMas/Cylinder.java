package xMas;

class Cylinder extends Present {
	double height; // in meter
	double diameter; // in meter
	
	public Cylinder(double height, double diameter){
		this.height = height;
		this.diameter = diameter;
	}
	
	public Cylinder(){
		this(1, 0.5);
	}
	
	public double surface(){
		return 2*Math.PI*(this.diameter/2)*(this.diameter/2 + this.height);
	}
	
	public double computeRibbonLength(){
		return 2*Math.PI*(this.diameter/2) + 0.3;
	}
	
	public String toString(){
		return "A cylinder";
	}
	
	public String toString2(){
		return "A cylinder with " + this.height + "m heigth and a diameter of " 
				+ this.diameter + "m. \n You will need " + this.computePaperSize() 
				+ " m2 of paper and " + this.computeRibbonLength() + " m of ribbon.";
	}
	
}
