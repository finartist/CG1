package animal;

class Bird extends Animal {
	public void move(String dest){
		System.out.println("fly "+dest);
	}	
	public void move(int x, int y){
		System.out.println("fly "+x+y);
	}
}
