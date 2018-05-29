import java.util.Scanner;

public class Aufgabe_16 {
	/**
	 * @author Kai Friedrich
	 */

	public static void main(String[] args) {
		System.out.println("Aufgabe 16");
		int zahl1 = intEinlesen("gibt die erste ganze Zahl ein");
		int zahl2 = intEinlesen("gibt die zweite ganze Zahl ein");
		char operationsZeichen = charEinlesen("gib das Operationszeichen (+,-,* oder /) ein");

		switch (operationsZeichen) {
		case '+':
			System.out.println(zahl1 + " + " + zahl2 + " = " + plus(zahl1, zahl2));
			break;
		case '-':
			System.out.println(zahl1 + " - " + zahl2 + " = " + minus(zahl1, zahl2));
			break;
		case '*':
			System.out.println(zahl1 + " * " + zahl2 + " = " + mal(zahl1, zahl2));
			break;
		case '/':
			System.out.println(zahl1 + " / " + zahl2 + " = " + geteilt(zahl1, zahl2));
			break;
		default:
			System.out.println("Fehler: Falsches Operationszeichen eingegeben");
		}
	}

	public static int plus(int zahl1, int zahl2) {
		return zahl1 + zahl2;
	}

	public static int minus(int zahl1, int zahl2) {
		return zahl1 - zahl2;
	}

	public static int mal(int zahl1, int zahl2) {
		return zahl1 * zahl2;
	}

	public static double geteilt(int zahl1, int zahl2) {
		if (zahl2 != 0) {
			return ((double) zahl1 / (double) zahl2);
		} else {
			System.out.println("Fehler: Division durch 0");
			return Double.NaN;
		}
	}

	public static char charEinlesen(String nutzerAufforderung) {
		Scanner scn = new Scanner(System.in);
		System.out.println(nutzerAufforderung);
		String operationszeichen;
		operationszeichen = scn.next();

		// scn.close();
		return operationszeichen.charAt(0);
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
