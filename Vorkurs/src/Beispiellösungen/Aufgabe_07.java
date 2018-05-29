import java.util.Scanner;

/**
 * @author Kai Friedrich
 */
public class Aufgabe_07 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 7");
		int eingabe = intEinlesen("gib eine ganze Zahl ein");
		zahlTesten(eingabe);
	}

	public static Boolean zahlTesten(int x) {
		if (x < 0) {
			System.out.println("die Zahl ist negativ");
			return false;
		} else {
			if (x % 2 == 0) {
				System.out.println("die Zahl ist gerade");
				return false;
			} else {
				x = x * x;
				if (x > 100 && x < 500) {
					System.out.println("das Quadart der Zahl liegt zwischen 100 und 500");
					return true;
				} else {
					System.out.println("das Quadart der Zahl liegt nicht zwischen 100 und 500");
					return false;
				}
			}
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
