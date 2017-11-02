package xMas;

class Cuboid extends Present {
	double length; // in meter
	double width; // in meter
	double height; // in meter
	
	public Cuboid(double length, double width, double height){
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	public Cuboid(){
		this(0.5, 0.5, 0.5);
	}
	
	public double surface(){
		return 2*(this.length*this.height + this.length*this.width + this.height*this.width);
	}
	
	public double computeRibbonLength(){
		return 2*(this.length+this.height+2*this.width)+ 0.3;
	}
	
	public String toString(){
		return "A cuboid";
	}
	
	public String toString2(){
		return "A cuboid with " + this.height + "m x " + this.width + "m x " + this.length + "m. \n You will need "
				+ this.computePaperSize() + " m2 of paper and " + this.computeRibbonLength() + " m of ribbon.";
	}
	
	
}