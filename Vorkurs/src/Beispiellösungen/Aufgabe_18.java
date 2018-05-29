import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_18 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 18");

		int zahl = intEinlesen("Gib eine ganze Zahl ein!");
		if (zahl % 2 == 0) {
			System.out.println(zahl * 3);
		} else {
			System.out.println(zahl * zahl);

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
