import java.util.Arrays;
import java.util.Scanner;

/**
 * Aufgabe 23:
 * Schreibe ein Java-Programm, welches so lange positive Zahlen annimmt,
 * bis es 100 sind, oder -1 eingegeben wurde. Im Anschluss ist der 
 * Durchschnitt der eingegebenen Zahlen zu berechnen und auszugeben. 
 * Danach sollen alle eingebenen Quadratzahlen ausgeben werden. 
 * Es folgt eine Liste der durch 17 teilbaren Zahlen. Die größte und 
 * die kleinste der eingegebenen Zahlen sind auszugeben. Danach sind 3 
 * weitere Zahlen vom Benutzer entgegen zu nehmen (a, b und c). Aus der 
 * Vorherigen Liste von Zahlen sollen nun alle gefunden werden, die die 
 * Gleichung ax² + bx + c = 0 erfüllen. Zu guter Letzt soll c noch als
 * Grad C aufgefasst werden und in Grad F umgerechnet werden.
 * @author Kai Friedrich
 */

public class Aufgabe_23 {

	/**
	 * Main Methode Start des Programms
	 * @param args nicht verwendet
	 */
	public static void main(String[] args) {
		System.out.println("Aufgabe 23");

		int[] eingegebeneZahlen = zahleneinlesen();
		System.out.println(Arrays.toString(eingegebeneZahlen));
		double durchschnitt = berechneDurchschnitt(eingegebeneZahlen);
		System.out.println("der Durschschnitt der Zahlen beträgt: " + durchschnitt);
		checkQuadratZahlen(eingegebeneZahlen);
		checkDurch17Teilbar(eingegebeneZahlen);
		System.out.println("Die größte Zahl ist: " + groessteZahl(eingegebeneZahlen));
		System.out.println("Die kleinste Zahl ist: " + kleinsteZahl(eingegebeneZahlen));

		double a = doubleEinlesen("Gib einen Wert für a ein");
		double b = doubleEinlesen("Gib einen Wert für b ein");
		double c = doubleEinlesen("Gib einen Wert für c ein");
		checkGleichungssystem(eingegebeneZahlen, a, b, c);

		System.out.println(c + " Grad Celsius sind " + celciusToFahrenheit(c) + " Grad Fahrenheit.");
	}

	/**
	 * Wandelt Grad Celcius in Fahrenheit um
	 * @param celcius Temperatur in Grad Celcius
	 * @return Temperatur in Fahrenheit
	 */
	public static double celciusToFahrenheit(double celcius) {
		double fahrenheit = celcius * 1.8 + 32d;
		return fahrenheit;
	}

	/**
	 * überprüft ob ax²+bx+c=0 und gibt die Werte für x auf Konsole aus
	 * @param eingegebeneZahlen
	 * @param a Wert für a
	 * @param b Wert für a
	 * @param c Wert für a
	 */
	public static void checkGleichungssystem(int[] eingegebeneZahlen, double a, double b, double c) {
		System.out.println(
				"folgende der eingegebenen Zahlen lösen das Gleichungssystem " + a + "x²+" + b + "x+" + c + "=0:");
		for (int i = 0; i < 100; i++) {
			if (eingegebeneZahlen[i] > -1) {
				if (testeGleichungsSystem(a, b, c, eingegebeneZahlen[i])) {
					System.out.print(eingegebeneZahlen[i] + " ; ");
				}
			} else {
				break;
			}
		}
		System.out.println();
	}

	/**
	 * testet ob ax²+bx+c=0 
	 * @param a Wert für a
	 * @param b Wert für b
	 * @param c Wert für c
	 * @param x Wert für x
	 * @return return true wenn ax²+bx+c=0 
	 */
	public static boolean testeGleichungsSystem(double a, double b, double c, int x) {
		if (((a * (x * x)) + (b * x) + c) == 0) {
			return true;
		} else
			return false;
	}

	
	/**
	 * durchsucht das Array der eingegeben Zahlen nach der größten Zahl
	 * @param eingegebeneZahlen alle vom Nutzer eingegeben Zahlen
	 * @return gibt die größte Zahl zurück
	 */
	public static int groessteZahl(int[] eingegebeneZahlen) {
		int groessteZahl = eingegebeneZahlen[0];
		for (int i = 0; i < 100; i++) {
			if (eingegebeneZahlen[i] > -1) {
				if (eingegebeneZahlen[i] > groessteZahl) {
					groessteZahl = eingegebeneZahlen[i];
				}
			} else {
				break;
			}
		}
		return groessteZahl;
	}

	/**
	 * durchsucht das Array der eingegeben Zahlen nach der kleinsten Zahl
	 * @param eingegebeneZahlen alle vom Nutzer eingegeben Zahlen
	 * @return gibt die kleinste Zahl zurück
	 */
	public static int kleinsteZahl(int[] eingegebeneZahlen) {
		int kleinsteZahl = eingegebeneZahlen[0];
		for (int i = 0; i < 100; i++) {
			if (eingegebeneZahlen[i] > -1) {
				if (eingegebeneZahlen[i] < kleinsteZahl) {
					kleinsteZahl = eingegebeneZahlen[i];
				}
			} else {
				break;
			}
		}
		return kleinsteZahl;
	}

	/**
	 * gibt alle Zahlen aus die durch 17 Teilbar sind
	 * @param eingegebeneZahlen alle Eingegebenen zahlen
	 */
	public static void checkDurch17Teilbar(int[] eingegebeneZahlen) {
		System.out.print("Folgende Zahlen sind durch 17 teilbar: ");
		for (int i = 0; i < 100; i++) {
			if (eingegebeneZahlen[i] != -1) {
				if (eingegebeneZahlen[i] % 17 == 0) {
					System.out.print(eingegebeneZahlen[i] + " ");
				}
			} else {
				break;
			}
		}
		System.out.println();
	}

	/**
	 * Schaut welche Zahlen Qudartzahlen sind und gibt diese aus.
	 * @param eingegebeneZahlen Alle vom Nutzer eingegebenen Zahlen
	 */
	public static void checkQuadratZahlen(int[] eingegebeneZahlen) {
		System.out.print("Quadratzahlen sind: ");
		for (int i = 0; i < 100; i++) {
			if (eingegebeneZahlen[i] != -1) {
				if (checkQuadrat(eingegebeneZahlen[i]))
					System.out.print(eingegebeneZahlen[i] + " ");
			} else {
				break;
			}
		}
		System.out.println();
	}

	/**
	 * Checkt ob eine Zahl eine Quadratzahl
	 * @param zahl zu überprüfende Zahl
	 * @return Gibt true aus wenn die Zahl eine Quadratzahl war
	 */
	private static boolean checkQuadrat(int zahl) {
		double wurzel = Math.sqrt(zahl);
		int wurzelInt = (int) (wurzel);
		if (wurzel == wurzelInt) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Berechnet den Durchschnitt alle eingegeben Zahlen
	 * @param eingegebeneZahlen alle vom Nutzer eingegebenen Zahlen
	 * @return Durchschnitt der eingegeben Zahlen
	 */
	public static double berechneDurchschnitt(int[] eingegebeneZahlen) {
		int i = 0;
		double summe = 0;
		double durchschnitt = 0;
		System.out.println("Durschschnitt wird berechnet");
		for (; i < 100; i++) {
			if (eingegebeneZahlen[i] != -1) {
				summe = summe + eingegebeneZahlen[i];
			} else {
				break;
			}
		}
		durchschnitt = (summe / (double) (i));
		return durchschnitt;
	}

	/**
	 * Liest bis zu 100 positive Zahlen ein. Bricht bei -1 ab 
	 * @return alle vom Nutzer eingegebene Zahlen
	 */
	public static int[] zahleneinlesen() {
		int[] eingegebeneZahlen = new int[100];
		int eingebeneZahl;
		for (int i = 0; i < 100; i++) {
			eingebeneZahl = intEinlesen("Gib die " + (i + 1) + "te positive ganze zahl ein! (-1 zum Abbrechen)");
			if (eingebeneZahl == -1) {
				System.out.println("Eingabe beendet.");
				eingegebeneZahlen[i] = eingebeneZahl;
				break;
			} else {
				eingegebeneZahlen[i] = eingebeneZahl;
			}
		}

		return eingegebeneZahlen;
	}

	/**
	 * Liest einen double von der Konsole System.in ein
	 * @param nutzerAufforderung Nutzeraufforderung um von den Nutzer etwas eingeben zu lassen 
	 * @return die Eingelesene Zahl
	 */
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
	
	/**
	 * Liest ein int von der Konsole System.in ein
	 * @param nutzerAufforderung Nutzeraufforderung um von den Nutzer etwas eingeben zu lassen 
	 * @return die Eingelesene Zahl
	 */
 	public static int intEinlesen(String nutzerAufforderung) {
		boolean korrekteEingabe = false;
		int eingegebeneZahl = 0;
		Scanner scn = new Scanner(System.in);
		System.out.println(nutzerAufforderung);
		String falscheEingabe = "";
		do {
			try {
				eingegebeneZahl = scn.nextInt();
				if (eingegebeneZahl >= -1) {
					korrekteEingabe = true;
				} else {
					System.out.println("Fehler die Zahl war kleiner -1");
				}
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
