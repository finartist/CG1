package animal;

abstract class Animal {
	public abstract void move(String s);
	public void move(int x){
		System.out.println("teleport to "+x);
	}	
}
