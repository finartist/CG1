
/**
 * @author Kai Friedrich
 */

public class Aufgabe5 {

	public static void main(String[] args) {
		System.out.println("Aufgabe11 ggt Berechnen");
		int zahl1 = 0;
		int zahl2 = 0;
		int ergebnis;

		do {
			zahl1 = WerteEinlesen.intEinlesen("gib eine ganze Zahl größer 0 ein");
		} while (zahl1 <= 0); // nur Zahlen größer null werden akzeptiert
		do {
			zahl2 =  WerteEinlesen.intEinlesen("gib die zweite Zahl größer 0 ein");
		} while (zahl2 <= 0);

		if (zahl1 < zahl2) { // die erste Zahl ist immer die größere
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
		System.out.println("der größte gemeinsame Teiler von " + zahl1 + " und " + zahl2 + " ist: " + ergebnis);
	}

	// ggT iterativ wobei x > y sein muss und beide größer 0
	public static int ggT(int x, int y) {
		while (y != 0) {
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}

	// ggT rekursiv wobei x > y sein muss und beide größer 0
	public static int ggT2(int x, int y) {
		if (y == 0) {
			return x;
		} else {
			return (ggT2(y, x % y));
		}
	}
}
