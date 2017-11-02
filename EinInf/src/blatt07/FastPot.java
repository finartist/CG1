package blatt07;

/********************************************************
 * x = 2, n= 13
 * 
 * 2^13 = 2 * 2^12 = 2 * (2*2)^6 = 2 * ((2*2)*(2*2))^3 = 2*(4*4)^3 = 2*16^3 = 2
 * * 16 * 16^2 = 32 * 16^2 = 32 * 16 * 16 = 8192
 * 
 * 
 ********************************************************/

public class FastPot {
	static int rekursions = 0;

	public static double fastPotRek(double x, int n) {
		rekursions++;
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		} else if (n % 2 == 0) {
			return fastPotRek(x * x, n / 2);
		} else {
			return x * fastPotRek(x, n - 1);
		}

	}

	public static double fastPotIter(double x, int n) {
		double result = 1;
		int iterations = 0;
		while (n > 0) {
			result *= x;
			n--;
			iterations++;
		}
		System.out.println("Iterations: " + iterations);
		return result;
	}

	public static void main(String[] args) {
		System.out.println(fastPotRek(3, 6));
		System.out.println("Rekursion: " + rekursions);
		rekursions = 0;
		System.out.println(fastPotIter(3, 6));
		System.out.println("");
		// Rekursion: 4
		// Iterations: 6

		System.out.println(fastPotRek(2, 15));
		System.out.println("Rekursion: " + rekursions);
		rekursions = 0;
		System.out.println(fastPotIter(2, 15));
		System.out.println("");
		// Rekursion: 7
		// Iterations: 15

		System.out.println(fastPotRek(5, 3));
		System.out.println("Rekursion: " + rekursions);
		rekursions = 0;
		System.out.println(fastPotIter(5, 3));
		System.out.println("");
		// Rekursion: 3
		// Iterations: 3

		System.out.println(fastPotRek(12, 6));
		System.out.println("Rekursion: " + rekursions);
		rekursions = 0;
		System.out.println(fastPotIter(12, 6));
		System.out.println("");
		// Rekursion: 4
		// Iterations: 6

		System.out.println(fastPotRek(4, 19));
		System.out.println("Rekursion: " + rekursions);
		rekursions = 0;
		System.out.println(fastPotIter(4, 19));
		// Rekursion: 7
		// Iterations: 19
	}
}