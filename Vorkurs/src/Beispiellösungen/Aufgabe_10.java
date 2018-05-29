
/**
 * @author Kai Friedrich
 */

public class Aufgabe_10 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 10");
		iterativ();
		System.out.println("Rekursive Version");
		rekursiv(10);
	}

	public static void rekursiv(int i) {
		System.out.println(11 - i);
		if (i > 1) {
			rekursiv(i - 1);
		}
		System.out.println(11 - i);
	}

	public static void iterativ() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
		}
		for (int i = 10; i > 0; i--) {
			System.out.println(i);
		}
	}

}
