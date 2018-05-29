
public class Methoden {

	public static void main(String[] args) {
		// Methoden = Funktionen
		// Im Debugger : step over -> führt funktionen einfach aus
		// 			 step into -> springt in die Funktion und führt diese Schritt für Schritt aus
		// Schlüsselwörter: void -> keine Rückgabe (bei diesem Typ kann man nur return schreiben, um die Funktion abzubrechen), int -> int-Wert zurückgeben, 
		//String -> String als Rückgabe, public -> die Funktion ist überall sichtbar, private -> Funktionen können nur aus der eigenen Klasse aufgerufen werden
		//protected ähnlich private, ohne public/private/protected -> packageprivate (Funktion kann nur im eigenen Package aufgerufen werden)
		// main-Funktion existiert 1x pro Programm, sie muss aber nicht zwingend existieren, z.B. bei einer Funktionssammlung, in der kein Programm ausgeführt werden soll
		gruss();
		quadrat(5);
	}
	
	public static void gruss(){ // bis zu 3 Schlüsselwörter Funktionsname(Parameter)
		
		System.out.println("Hallo");
	}
	
	public static void quadrat (int x){
		
		System.out.println(x*x);
		// in der Funktion sind nur Parameter der Funktion(und dort übergebene Variablen) und Variablen der Funktion sichtbar, es sei denn eine Variable ist global
	}
	
	public static String grussRueck() {
		return "Hallo";
		// alles nach return wird nicht ausgeführt!
		// System.out.println("Das wird nicht mehr ausgeführt");
	}
	
	public static int summe(int a, int b){
		return a + b;
	}
	
	// Funktionen dürfen gleich heißen, wenn sich Anzahl, Typ oder beides der Parameter unterscheiden
	// Funktionen können importiert werden in andere Klassen mit Klassenname.Funktion(); bzw. aus einem anderen Package Packagename.Klassenname.Funktionsname();
}
