package Beispiellösungen;

import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_04 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 4 - Drei Zahlen sortieren");
		int zahl1 = intEinlesen("gib die erste Zahl ein");
		int zahl2 = intEinlesen("gib die gib die zweite Zahl ein");
		int zahl3 = intEinlesen("gib die gib die ditte Zahl ein");
		sortiereDrei(zahl1, zahl2, zahl3);
	}

	public static void sortiereDrei(int a, int b, int c) {
		if (a < b) {
			if (b < c) {
				ausgabe(a, b, c);
			} else if (a < c) {
				ausgabe(a, c, b);
			} else {
				ausgabe(c, a, b);
			}
		} else {
			if (b < c) {
				if (a < c) {
					ausgabe(b, a, c);
				} else {
					ausgabe(b, c, a);
				}
			} else {
				ausgabe(c, b, a);
			}
		}
	}

	public static void ausgabe(int kleinsteZahl, int zweitgroessteZahl, int groessteZahl) {
		System.out.println("Die kleinste Zahl ist: " + kleinsteZahl);
		System.out.println("Die zweitgrößte Zahl ist: " + zweitgroessteZahl);
		System.out.println("Die größte Zahl ist: " + groessteZahl);
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
