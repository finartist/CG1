
/**
 * @author Kai Friedrich
 */

public class Aufgabe_11 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 11");
		int n = 1000;
		for (int i = 1; i <= n; i++) {
			System.out.print("*");
			if (i % 50 == 0) {
				System.out.println(); // alle 50 Zeichen ein Zeilenumbruch
			}
		}
		System.out.println();
	}
}
