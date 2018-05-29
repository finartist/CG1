/**
 * 
 * @author Kai Friedrich
 *
 */

public class Aufgabe3 {

	public static void main(String[] args) {
		System.out.println("Rekursiv x hoch y berechnen");
		int x;
		int y;
		int ergebnis;
		x = WerteEinlesen.intEinlesen("gib einen Wert für x ein");
		y = WerteEinlesen.intEinlesen("gib einen Wert für y ein");
		ergebnis = potenz(x, y);
		System.out.println(ergebnis);
	}

	private static int potenz(int x, int y) {
		if (y == 0) {
			return 1;
		} else {
			return (x * potenz(x, y - 1));
		}

	}
}
