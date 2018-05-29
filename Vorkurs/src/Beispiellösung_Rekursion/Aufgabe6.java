
/**
 * 
 * @author Kai Friedrich
 *
 */

public class Aufgabe6 {
	public static void main(String[] args) {
		int n;
		System.out.println("Rekursion Aufgabe 6.");
		System.out.println("dieses Programm berechnet n!");
		n = WerteEinlesen.intEinlesen("gib einen Wert für n ein!");
		System.out.println(fakulaet(n));
	}

	private static int fakulaet(int i) {
		if (i == 0 || i == 1) {
			return 1;
		}
		return mult(i, fakulaet(i - 1));
	}

	private static int mult(int a, int b) {
		if (a == 1) {
			return b;
		}
		if (b == 1) {
			return a;
		}
		return plus(b, mult(a - 1, b));
	}

	private static int plus(int a, int b) {
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		return plus(a - 1, b) + 1;
	}

}
