import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_21 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 21");
		int zahl = intEinlesen("Gib eine ganze Zahl ein");
		zahlausgeben(zahl);
		System.out.println(funktion(zahl));
	}

	public static int funktion(int x) {
		return (3 * x * x) + (14 * x) - 5;
	}

	public static void zahlausgeben(int x) {
		for (int i = x; i > 0; i--) {
			System.out.println(x);
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
