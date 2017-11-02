package blatt03;
public class SpecialNumbers {

	public static void main(String[] args) {

		int[][] pairs = amicablePairs(9);
		for (int i = 0; i < pairs.length; i++) {
			System.out.println(i + 1 + ". Paar: " + pairs[i][0] + " " + pairs[i][1]);
		}

	}

	public static int[][] amicablePairs(int n) {
		// 2-Dimensionales Array mit den Paaren
		int[][] pairs = new int[n][2];
		int number = 0;

		for (int i = 0; i < pairs.length; i++) {
			// Man erhöht die Zahl um eins, solange sie keine befreundete Zahl
			// hat.
			do {
				number += 1;
			} while (!isAmicable(number));
			
			// Sobald eine gefunden wurde mit einem Gegenpart, wird sie
			// gespeichert
			pairs[i][0] = number;
			pairs[i][1] = sumOfDivisors(number);
			
			// Man macht bei dem Gegenpart +1 weiter mit Suchen, damit man kein
			// Paar doppelt hat
			number = sumOfDivisors(number) + 1;
		}
		return pairs;
	}

	// Hilfsfunktion, um die Summe der Divisor zu errechnen
	private static int sumOfDivisors(int number) {
		// Am Anfang ist die Summe natürlich noch 0
		int sum = 0;
		// Für jede Zahl von 1 bis zur Wurzel der Zahl wird geschaut, ob sie
		// Teiler ist
		for (int i = 1; i * i <= number; i++) {
			// ist sie Teiler, wird sie zur Summe dazu gerechnet
			if (number % i == 0) {
				sum += i;
				// wenn sie außerdem weder die Quadratwurzel, noch 1 ist, wird
				// auch der jeweilige größere Gegenpart zur Summe dazu gerechnet
				// (bei der Wurzel würde ein Teiler doppelt auftauchen, bei der
				// eins würde die Zahl selbst addiert, was nicht zulässig ist)
				if (i * i != number && i != 1) {
					sum += (number / i);
				}
			}
		}
		return sum;
	}

	// Hilfsfunktion, um herauszufinden, ob eine Zahl eine befreundete Zahl hat
	private static boolean isAmicable(int number) {
		// SumDiv ist die Summe der Divisor der Zahl
		int SumDiv = sumOfDivisors(number);

		// SumSumDiv ist die Summe der Divisor von SumDiv
		int SumSumDiv = sumOfDivisors(SumDiv);

		// wenn die Zahl und die Summe der Summe der Divisor der Zahl gleich ist
		// und die jeweiligen Summen nicht gleich sind, gibt es eine befreundete
		// Zahl zu number
		if (number == SumSumDiv && SumDiv != SumSumDiv) {
			return true;
		} else {
			return false;
		}
	}

}
