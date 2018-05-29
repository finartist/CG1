import java.util.Scanner;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_24 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 24");
		Scanner scn = new Scanner(System.in);
		System.out.println("Würfelprogramm");
		System.out.println("Zum Würfeln gib \"wuerfeln\" ein");
		System.out.println("Zum Programm beenden gib \"exit\" ein");
		Boolean beenden = false;
		do {
			String eingabe = scn.next();
			if (eingabe.equalsIgnoreCase("wuerfeln") || eingabe.equalsIgnoreCase("würfeln")) {
				System.out.println("Du hast eine " + wurfeln() + " gewürfelt");
			} else {
				if (eingabe.equalsIgnoreCase("exit")) {
					beenden = true;
				} else {
					System.out.println("deine Eingabe verstehe ich nicht.");
				}
			}
		} while (!beenden);
		System.out.println("programm wird beendet");
	}

	/**
	 * 
	 * @return
	 */
	public static int wurfeln() {
		return (int) ((Math.random() * 6) + 1);
	}
}
