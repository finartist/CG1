package blatt02;
import java.util.Arrays;
import java.util.Random;

public class MyIntArray {

	public static void main(String[] args) {
		int[] randomArray;

		// Test mit 5 Arrays
		for (int array = 0; array < 5; array++) {
			// zufälliges Arrays wird mit zufälliger Länge (0-20) erstellt
			Random rng = new Random();
			int arrayLength = rng.nextInt(21);
			randomArray = createRandom(arrayLength);
			// testen sämtlicher erstellter Funktionen
			System.out.println("Test Array: " + (array + 1));
			System.out.println(toString(randomArray));
			System.out.println(Arrays.toString(randomArray));
			System.out.println("Die kleinste Zahl befindet sich beim Index " + posMin(randomArray));
			swap(randomArray);
			System.out.println(toString(randomArray));
			System.out.println("");
		}

	}

	public static int[] createRandom(int n) {
		int[] rArray;
		// falls eingegebene Zahl kleiner oder gleich 0, erstelle ein Array der
		// Länge 1
		if (n <= 0) {
			rArray = new int[1];
		}
		// sonst ein Array der Länge n erstellen
		else {
			rArray = new int[n];
		}
		// Schleife durchläuft jeden Index und ordnet ihm einen zufälligen Wert
		// zu
		for (int i = 0; i < rArray.length; i++) {
			rArray[i] = (int) (95 * Math.random() + 5);
		}
		return rArray;
	}

	public static String toString(int[] a) {
		// leerer String, in dem das Array als String gespeichert werden soll
		String out = "";
		// jedes Element des Arrays wird an den String angehaengt
		for (int i = 0; i < a.length; i++) {
			// zur schönen Darstellung wird an das letzte Element kein Komma
			// mehr angehängt
			if (i < (a.length - 1)) {
				out += a[i] + ", ";
			} else {
				out += a[i];
			}
		}
		return out;
	}

	public static int posMin(int[] a) {
		// das erste Element wird zuerst als kleinstes Element angenommen
		int min = a[0];
		int pos = 0;
		// sobald ein kleineres Element gefunden wurde, wird es ersetzt
		for (int i = 1; i < a.length; i++) {
			if (a[i] < min) {
				min = a[i];
				pos = i;
			}
		}
		return pos;
	}

	public static void swap(int[] a) {
		// der Wert des letzten Elements wird ersteinmal zwischengespeichert
		int temp = a[a.length - 1];
		// der Wert des letzten Elements wird durch den des ersten ersetzt
		a[a.length - 1] = a[0];
		// der Wert des ersten wird durch den Wert des letzten ersetzt
		a[0] = temp;
	}

}
