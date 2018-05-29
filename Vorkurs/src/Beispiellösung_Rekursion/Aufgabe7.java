/**
 * Die Türme von Hanoi
 *
 * @author Roland Illig <1illig@informatik.uni-hamburg.de>
 */
public class Aufgabe7 {

	/**
	 * Bewegt n Scheiben von Turm a nach Turm c und benutzt als Zwischenspeicher
	 * Turm b.
	 */
	private static void bewege(char a, char b, char c, int n) {
		if (n == 1)
			System.out.println("Lege die oberste Scheibe von " + "Turm " + a + " auf Turm " + c + ".");
		else {
			bewege(a, c, b, n - 1);
			bewege(a, b, c, 1);
			bewege(b, a, c, n - 1);
		}
	}

	public static void main(String[] args) {
		bewege('a', 'b', 'c', 5);
	}

}