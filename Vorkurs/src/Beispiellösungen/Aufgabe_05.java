import java.util.Scanner;

public class Aufgabe_05 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 5");

		int zensur = intEinlesen("gib eine Zensur ein");

		if (zensur == 1) {
			System.out.println("Gratuliere");
		} else {
			System.out.println("Verbessern");
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
