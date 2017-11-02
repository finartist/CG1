package blatt05;

public class Dispute {

	public static void main(String[] args) {
		System.out.println(dispute(7)); // True -> Streit
		System.out.println(dispute(25)); // False -> Frieden

		int[] disputes = differenceDisputes(10);

		for (int e : disputes) {
			System.out.println(e);
		}

		// Wenn man annimmt, dass Homer und Marge 100 Jahre verheiratet sind,
		// dann verbessert sich ihr Verhältnis.
		// Differenzen:
		/*
		 * Die Differenz zwischen den Jahren entspricht der Fibonacci-Folge.
		 *
		 * 1, 1, 2, 3, 5, 8, 13, 21, 34
		 */

	}

	public static int marge(int n) {

		if (n == 0) {
			return 1;
		} else {
			return n - homer(marge(n - 1));
		}
	}

	public static int homer(int n) {

		if (n == 0) {
			return 0;
		} else {
			return n - marge(homer(n - 1));
		}
	}

	public static boolean dispute(int n) {
		return !(marge(n) == homer(n));
	}

	public static int[] differenceDisputes(int x) {
		
		int[] differences = new int[x];
		// differences[x-1] = 0;
		int lastDY=0;
		int currentDY=0;
		int index = 0;
		for(int year = 0; differences[x-1]==0; year++){
			if(dispute(year)){
				lastDY = currentDY;
				currentDY = year;
				if(!(lastDY == currentDY)){
					differences[index] = currentDY-lastDY;
					index++;
				}
			}
		}		

		return differences;
	}

}
