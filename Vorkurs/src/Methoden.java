
public class Methoden {

	public static void main(String[] args) {
		// Methoden = Funktionen
		// Im Debugger : step over -> f�hrt funktionen einfach aus
		// 			 step into -> springt in die Funktion und f�hrt diese Schritt f�r Schritt aus
		// Schl�sselw�rter: void -> keine R�ckgabe (bei diesem Typ kann man nur return schreiben, um die Funktion abzubrechen), int -> int-Wert zur�ckgeben, 
		//String -> String als R�ckgabe, public -> die Funktion ist �berall sichtbar, private -> Funktionen k�nnen nur aus der eigenen Klasse aufgerufen werden
		//protected �hnlich private, ohne public/private/protected -> packageprivate (Funktion kann nur im eigenen Package aufgerufen werden)
		// main-Funktion existiert 1x pro Programm, sie muss aber nicht zwingend existieren, z.B. bei einer Funktionssammlung, in der kein Programm ausgef�hrt werden soll
		gruss();
		quadrat(5);
	}
	
	public static void gruss(){ // bis zu 3 Schl�sselw�rter Funktionsname(Parameter)
		
		System.out.println("Hallo");
	}
	
	public static void quadrat (int x){
		
		System.out.println(x*x);
		// in der Funktion sind nur Parameter der Funktion(und dort �bergebene Variablen) und Variablen der Funktion sichtbar, es sei denn eine Variable ist global
	}
	
	public static String grussRueck() {
		return "Hallo";
		// alles nach return wird nicht ausgef�hrt!
		// System.out.println("Das wird nicht mehr ausgef�hrt");
	}
	
	public static int summe(int a, int b){
		return a + b;
	}
	
	// Funktionen d�rfen gleich hei�en, wenn sich Anzahl, Typ oder beides der Parameter unterscheiden
	// Funktionen k�nnen importiert werden in andere Klassen mit Klassenname.Funktion(); bzw. aus einem anderen Package Packagename.Klassenname.Funktionsname();
}
