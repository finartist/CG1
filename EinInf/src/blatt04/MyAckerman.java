package blatt04;
/*
 * 3.
 * Bei der Ackerman-Zahl kann es durch die Rekursion zum StackOverflow kommen.
 * Z.B beim Aufruf der Funktion mit Ackerman(3, 10)
 */

import java.math.BigInteger;

public class MyAckerman {

	public static void main(String[] args) {
		// 2.
		System.out.println(ackermann(BigInteger.valueOf(1), BigInteger.valueOf(10))); // 1024
		System.out.println(ackermann(BigInteger.valueOf(2), BigInteger.valueOf(4))); // 65536
		System.out.println(ackermann(BigInteger.valueOf(3), BigInteger.valueOf(3))); // 65536
		System.out.println(ackermann(BigInteger.valueOf(3), BigInteger.valueOf(10))); // StackOverflowError

	}

	public static BigInteger ackermann(BigInteger x, BigInteger y) {
		/*
		 * if (y = 0) then 0 
		 * if (x = 0) then 2*y 
		 * if (y = 1) then 2 
		 * else Ackermann(x-1, Ackermann(x, y-1))
		 */
		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger two = new BigInteger("2");
		// Alternative zum Vergleichen
		// a.compareTo(b) == 0 => Gleichheit
		// a.compareTo(b) == -1 => a kleiner als b
		// a.compareTo(b) == 1 => a größer als b
		if (y.equals(zero)) {
			return zero;
		} else if (x.equals(zero)) {
			return y.multiply(two);
		} else if (y.equals(one)) {
			return two;
		} else {
			return ackermann(x.subtract(one), ackermann(x, y.subtract(one)));
		}
	}

}
