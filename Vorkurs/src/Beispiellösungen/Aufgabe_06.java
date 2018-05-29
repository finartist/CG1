import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_06 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 6");
		int eingabe = intEinlesen("gib eine Zensur ein");
		switch (eingabe) {
		case 1: {
			System.out.println("sehr gut");
			
		}
		case 2: {
			System.out.println("gut");
			
		}
		case 3: {
			System.out.println("befriedigend");
			
		}
		case 4: {
			System.out.println("ausreichend");
			
		}
		case 5: {
			System.out.println("mangelhaft");
			
		}
		case 6: {
			System.out.println("ungenügend");
			
		}
		default: {
			System.out.println("das ist keine Zensur");
			
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
