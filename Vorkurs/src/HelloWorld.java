
public class HelloWorld {
	// Jedes Programm hat mindestens eine Klasse, in der das Programm läuft, die Datei heißt so wie diese Klasse
	// die Klasse hat die Schlüsselwörter(lila) public und class; Schlüsselwörter sind vom Programm "reserviert"
	
	public static void main(String[] args) {
		// Dieses Programm gibt "Hello World" aus
		System.out.println("Hello World!");
		
		// bei Java muss jede Anweisung mit ; enden
		
		System.out.println("syso schreiben, dann STRG und Leertaste zusammen -> autovervollständigung");
		
		int testvariable; //deklaration
		testvariable = 42; // initialisierung
		// Syntax für Variablen -typ variablenname-, in Java müssen deklariert und initialisiert werden
		++testvariable; //die Variable wird um 1 erhöht
		// bei Variablentypen auf die Grenzen achten (z.B. byte -128 - 127) wird die Variable immer weiter erhöht, 
		// kommt es zum überlauf und der Wert beginnt wieder bei der unteren Grenze
		
		int foo;
		foo = (int) Math.pow(2, 8); //Exponentialfunktion 2^8
		
		/* dies
		 * ist
		 * ein
		 * mehrzeiliger
		 * Kommentar
		 */
		//STRG+Umschalt+c --> ausgewählter Text zu Kommentar
	}

}
