import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_22 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 22");
		System.out.println("Ich berechne die L�sungen des Gleichungssystem ax�+bx+c=0");
		double a = doubleEinlesen("Gib einen Wert f�r a ein.");
		double b = doubleEinlesen("Gib einen Wert f�r b ein.");
		double c = doubleEinlesen("Gib einen Wert f�r c ein.");
		System.out.println("berechne die L�sung f�r " + a + "x�+" + b + "x+" + c + "=0");
		loeseGleichungsSystem(a, b, c);
	}

	public static void loeseGleichungsSystem(double a, double b, double c) {
		double x1;
		double x2;
		if (a == 0) { // a = 0
			if (b == 0) { // a = 0, b = 0
				if (c == 0) { // a,b,c = 0
					System.out.println("da a, b und c = 0 sind gibt es unendlich viele L�sungen");
				} else { // a,b = 0; c != 0;
					System.out.println("da a und b = 0 sind und c != 0 gibt es keine L�sung");
				}
			} else { // a = 0, b != 0
						// zu l�sen bx + c = 0;
				x1 = -c / b;
				System.out.println("x1= " + x1);
			}
		} else { // a ungleich 0
					// Berechnung nach Mitternachtsformel (siehe Matheheft Seite
					// 26)
			double wertUnterWurzel = (b * b) - (4 * a * c);
			if (wertUnterWurzel > 0) { // es gibt 2 L�sungen
				x1 = ((b * -1) + (Math.sqrt(wertUnterWurzel))) / (2 * a);
				x2 = ((b * -1) - (Math.sqrt(wertUnterWurzel))) / (2 * a);
				System.out.println("x1=" + x1 + " x2=" + x2);
			}
			if (wertUnterWurzel == 0) { // es gibt eine L�sung
				x1 = ((b * -1) / (2 * a));
				System.out.println("x1=" + x1);
			}
			if (wertUnterWurzel < 0) { // es gibt keine L�sung
				System.out.println("Es gibt keine L�sung");
			}
		}
	}

	public static double doubleEinlesen(String nutzerAufforderung) {
		boolean korrekteEingabe = false;
		double eingegebeneZahl = 0;
		Scanner scn = new Scanner(System.in);
		System.out.println(nutzerAufforderung);
		String falscheEingabe = "";
		do {
			try {
				eingegebeneZahl = scn.nextDouble();
				korrekteEingabe = true;
			} catch (Exception e) {
				falscheEingabe = scn.nextLine();
				System.out.println("falsche Eingabe:" + falscheEingabe);
				System.out.println(nutzerAufforderung);
			}
		} while (!korrekteEingabe);

		// scn.close();
		return eingegebeneZahl;
	}

}
