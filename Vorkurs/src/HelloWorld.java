
public class HelloWorld {
	// Jedes Programm hat mindestens eine Klasse, in der das Programm l�uft, die Datei hei�t so wie diese Klasse
	// die Klasse hat die Schl�sselw�rter(lila) public und class; Schl�sselw�rter sind vom Programm "reserviert"
	
	public static void main(String[] args) {
		// Dieses Programm gibt "Hello World" aus
		System.out.println("Hello World!");
		
		// bei Java muss jede Anweisung mit ; enden
		
		System.out.println("syso schreiben, dann STRG und Leertaste zusammen -> autovervollst�ndigung");
		
		int testvariable; //deklaration
		testvariable = 42; // initialisierung
		// Syntax f�r Variablen -typ variablenname-, in Java m�ssen deklariert und initialisiert werden
		++testvariable; //die Variable wird um 1 erh�ht
		// bei Variablentypen auf die Grenzen achten (z.B. byte -128 - 127) wird die Variable immer weiter erh�ht, 
		// kommt es zum �berlauf und der Wert beginnt wieder bei der unteren Grenze
		
		int foo;
		foo = (int) Math.pow(2, 8); //Exponentialfunktion 2^8
		
		/* dies
		 * ist
		 * ein
		 * mehrzeiliger
		 * Kommentar
		 */
		//STRG+Umschalt+c --> ausgew�hlter Text zu Kommentar
	}

}
