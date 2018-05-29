package adventure;

public class Person {
	// keine main - Funktion, weil diese Klasse kein eigenständiges Programm ist
	
	public int alter = 0; //Attribut mit Standardwert
	public String name;
	
	public Person(){ //standardconstruktor
		System.out.println("Ich wurde erstellt");
	}
	
	public Person(int a, String name){ // mit diesem constructor wird die Person mit Parametern erstellt
		alter = a; // den Attributen wird ein Wert zugewiesen, wenn sie mit diesem constructor erstellt wird
		this.name = name; // heißt das attribut genauso wie der Parameter des constructors, muss ein this. davor stehen -> damit ist das Attribut gemeint
		//this.Attribut = Parameter
	}
	
	public void gruss(){ //Instanzmethode (diese hat kein static als Schlüsselwort, weil sich die Funktion je nach Objekt anders verhält
		System.out.println("Hallo, mein name ist " + this.name + ".");
	}
	
	public void gruss(Person p){
		System.out.println("Hallo, " + p.name);
	}
	
	public String toString(){ //diese Methode wird aufgerufen, wenn man z.B. syso(p1) ausgeben will
		return "name: " + this.name + "Alter: " + this.alter;
	}
}
