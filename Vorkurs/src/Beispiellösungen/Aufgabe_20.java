import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_20 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 20 ggt Berechnen");
		int zahl1 = 0;
		int zahl2 = 0;
		int ergebnis;

		do {
			zahl1 = intEinlesen("gib eine ganze Zahl gr��er 0 ein");
		} while (zahl1 <= 0); // nur Zahlen gr��er null werden akzeptiert
		do {
			zahl2 = intEinlesen("gib die zweite Zahl gr��er 0 ein");
		} while (zahl2 <= 0);

		if (zahl1 < zahl2) { // die erste Zahl ist immer die gr��ere
			System.out.println("iterative Variante nach Euklid");
			ergebnis = ggT(zahl2, zahl1);
			ausgabe(zahl1, zahl2, ergebnis);
			System.out.println("rekursive Variante nach Euklid");
			ergebnis = ggT2(zahl2, zahl1);
			ausgabe(zahl1, zahl2, ergebnis);
		} else {
			System.out.println("iterative Variante nach Euklid");
			ergebnis = ggT(zahl1, zahl2);
			ausgabe(zahl1, zahl2, ergebnis);
			System.out.println("rekursive Variante nach Euklid");
			ergebnis = ggT2(zahl1, zahl2);
			ausgabe(zahl1, zahl2, ergebnis);
		}
	}

	public static void ausgabe(int zahl1, int zahl2, int ergebnis) {
		System.out.println("der gr��te gemeinsame Teiler von " + zahl1 + " und " + zahl2 + " ist: " + ergebnis);
	}

	// ggT iterativ wobei x > y sein muss und beide gr��er 0
	public static int ggT(int x, int y) {
		while (y != 0) {
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}

	// ggT rekursiv wobei x > y sein muss und beide gr��er 0
	public static int ggT2(int x, int y) {
		if (y == 0) {
			return x;
		} else {
			return (ggT2(y, x % y));
		}
	}

	public static int intEinlesen(String nutzerAufforderung) {
		boolean korrekteEingabe = false;
		int eingegebeneZahl = 0;
		Scanner scn = new Scanner(System.in);
		System.out.println(nutzerAufforderung);
		String falscheEingabe = "";
		do {
			try {
				eingegebeneZahl = scn.nextInt();
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
