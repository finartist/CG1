package adventure;

public class Rechteck {
	private int a; // die Attribute k�nnen nichtmehr direkt aufgerufen oder 
	//ge�ndert werden durch das Schl�sselwort private -> deswegen m�ssen sie durch eine Funktion aufgerufen werden (getA(), setA(), ... )
	private int b;
	
	public int getA() { // einfacher Weg diese Getters und Setters zu generieren ist bei Eclipse -> 
						//Source -> generate Getters and Setters -> Variablen ausw�hlen -> OK
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public Rechteck(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	public static void info(){
		System.out.println("Die Fl�che ist a*b");
	}
	
	public void flaeche(){
		System.out.println(a*b);
	}
}
