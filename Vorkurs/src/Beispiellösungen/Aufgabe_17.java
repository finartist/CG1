import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_17 {
	// Umfang 2PI r
	// Fläche PI r²
	// Volumen Kugel 4/3 PI r³
	public static void main(String[] args) {
		System.out.println("Aufgabe 17");
		double radius = doubleEinlesen("gib den Radius in cm ein");
		System.out.println("Der Umfang des Kreises beträgt " + umfang(radius) + "cm");
		System.out.println("Der Flächeninhalt des Kreises beträgt " + flaeche(radius) + "cm²");
		System.out.println("Das Volumen der Kugel beträgt " + volumen(radius) + "cm³");
	}

	public static double volumen(double radius) {
		double volumen = 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
		volumen = Math.round(volumen * 100.0) / 100.0; // rundet auf 2 Stellen
														// nach dem Komma
		return volumen;
	}

	public static double umfang(double radius) {
		double umfang = 2.0 * Math.PI * radius;
		umfang = Math.round(umfang * 100.0) / 100.0; // rundet auf 2 Stellen
														// nach dem Komma
		return umfang;
	}

	public static double flaeche(double radius) {
		double flaeche = Math.PI * radius * radius;
		flaeche = Math.round(flaeche * 100.0) / 100.0; // rundet auf 2 Stellen
														// nach dem Komma
		return flaeche;
	}

	public static double doubleEinlesen(String nutzerAufforderung) {
		boolean korrekteEingabe = false;
		double eingegebeneZahl = 0;
		Scanner scn = new Scanner(System.in);
		System.out.println(nutzerAufforderung);
		String falscheEingabe = "";
		do {
			try {
				eingegebeneZahl = scn.nextDouble();
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
