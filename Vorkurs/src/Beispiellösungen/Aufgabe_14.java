
public class Aufgabe_14 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 14");
		int anzahlZeilen = 0;

		System.out.println("for Schleife");
		for (int i = 0; i < anzahlZeilen; i++) {
			System.out.println("!");
		}

		System.out.println("while Schleife");
		int j = 0;
		while (j < anzahlZeilen) {
			System.out.println("!");
			j++;
		}

		System.out.println("Do While Schleife");

		if (anzahlZeilen > 0) {
			int k = 0;
			do {
				System.out.println("!");
				k++;
			} while (k < anzahlZeilen);
		}

	}
}
