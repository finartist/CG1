
/**
 * @author Kai Friedrich
 */

public class Aufgabe_12 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 12");
		int ersteZahl = 1;
		int letzteZahl = 1000;
		int orignalErsteZahl = ersteZahl;
		for (; letzteZahl >= orignalErsteZahl; ersteZahl++, letzteZahl--) {
			System.out.println(ersteZahl + ":" + letzteZahl);
		}
	}
}
