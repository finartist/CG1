package blatt02;
import java.util.Arrays;
import java.util.Random;

public class Median {

	public static void main(String[] args) {
		Random rng = new Random();
		for (int i = 0; i < 5; i++) {
			int a = rng.nextInt(101);
			int b = rng.nextInt(101);
			int c = rng.nextInt(101);
			System.out.println(median(a, b, c));
			System.out.println(median2(a, b, c));
			System.out.println("");
			System.out.println(median(5, 18, 9));
		}
	}

	public static int median(int a, int b, int c) {
		// b liegt zwischen a und c oder ist gleich eines der beiden
		if ((a <= b && b <= c) || (c <= b && b <= a)) {
			return b;
		}
		// a liegt zwischen b und c oder ist gleich eines der beiden
		else if ((b <= a && a <= c) || (c <= a && a <= b)) {
			return a;
		}
		// wenn weder a noch b zwischen den jeweiligen anderen Variablen liegt,
		// ist c der mittlere Wert
		else {
			return c;
		}
	}

	public static int median2(int a, int b, int c) {
		// bequeme Methode
		int[] valuesSorted = { a, b, c };
		Arrays.sort(valuesSorted);
		return valuesSorted[1];
	}

}
