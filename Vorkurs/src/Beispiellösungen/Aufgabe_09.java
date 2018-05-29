/**
 * @author Kai Friedrich
 */

public class Aufgabe_09 {
	
	public final static int ANZAHL = 10;

	public static void main(String[] args) {
		System.out.println("Aufgabe 9");
		int i = 1;
		System.out.println("while Schleife");
		while (i <= ANZAHL) {
			System.out.println("Hallo");
			i++;
		}

		/********************************************/
		System.out.println("do while Schleife");
		i = 1;
		do {
			System.out.println("Hallo");
			i++;
		} while (i <= ANZAHL);
		forSchleife("Hallo",ANZAHL);
		
	}
	
	
	public static void forSchleife(String text, int anzahlZeilen){
		/********************************************/
		System.out.println("For Schleife");
		for (int i = 1; i <= anzahlZeilen; i++) {
			System.out.println(text);
		}

	}

}
