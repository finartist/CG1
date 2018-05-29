package Beispiellösungen;

import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_08 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 8");
		double fahrenheit = doubleEinlesen("Gib die Temperatur in Grad Fahrenheit ein!");
		double celsius = fahrendheitToCelsius(fahrenheit);
		System.out.println("die Temperatur entspricht" + celsius + " Grad Celsius");
	}

	public static double fahrendheitToCelsius(double fahrenheit) {
		double celsius = (5.0 / 9.0) * (fahrenheit - 32);
		celsius = Math.round((celsius * 100.0)) / 100.0; // rundet auf 2 Stellen
															// nach dem Komma
		return celsius;
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
