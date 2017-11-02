package xMas;

class Ball extends Present {
	double radius; // in meter
	
	public Ball(double radius){
		this.radius = radius;
	}
	
	public Ball(){
		this.radius = 0.3;
	}
	
	public double surface(){
		return 4*Math.PI*this.radius*this.radius;
	}
	
	public double computeRibbonLength(){
		return 2*Math.PI*this.radius + 0.3;
	}
	
	public String toString(){
		return "A ball";
	}
	
	public String toString2(){
		return "A ball with " + this.radius + "m radius. \n You will need " + this.computePaperSize() 
				+ " m2 of paper and " + this.computeRibbonLength() + " m of ribbon.";
	}
}
