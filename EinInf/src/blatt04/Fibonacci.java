package blatt04;

import java.math.*;

public class Fibonacci {
	
	// Zählvariable für fib1
	static int fib1Counter = 0;

	private static BigInteger fib1(int n){
		fib1Counter += 1;
		if (n == 0 || n == 1) {
			return BigInteger.valueOf(1);
		} else {
			return fib1(n - 2).add(fib1(n - 1));
		}

	}

	public static BigInteger fib2(int n) {
		// Fibonacci Zahl
		int fib = 0;
		// Erster Vorgänger
		int pre = 1;
		// Zweiter Vorgänger
		int pre2 = 0;
		// Zählvariable fib2
		int counter = 0;
		if(n == 0){
			System.out.println("Durchläufe Fib2: 1");
			return BigInteger.valueOf(1);
		}
		for (int i = 1; i <= n; i++) {
			// Fibonacci Zahl ist die Summe der Vorgänger
			fib = pre + pre2;
			// Der zweite Vorgänger wird gleich dem ersten gesetzt
			pre2 = pre;
			// Der erste Vorgänger wird gleich der Fibonacci Zahl gesetzt
			pre = fib;
			counter++;
		}
		System.out.println("Durchläufe Fib2: " + counter);
		return BigInteger.valueOf(fib);
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 15; i++) {
			System.out.println("Fib1 " + fib1(i) + " Fib2 " + fib2(i));
			System.out.println("Durchläufe Fib1: " + fib1Counter);
			System.out.println("");
			fib1Counter = 0;
		}
		System.out.println(fib1(23));
		System.out.println("Durchläufe Fib1: " + fib1Counter);
		System.out.println(fib2(23));
		
	}

}
/************************************************
 * b) fib1(5)
 * 
 * =                               fib(4)                    +                           fib(3)
 * 
 * =                    fib(3)       +        fib(2)              +                 fib(2)   +     fib(1)
 * 
 * =          fib(2)    +   fib(1)    +    fib(1) + fib(0)         +           fib(1) + fib(0)
 * 
 * =      fib(1) + fib(0)
 * 
 * =       1     +   1   +    1      +      1    +   1            +           1     +    +    1
 * 
 * = 8
 * 
 * c) fib2(5)
 * 
 * fib = 1 + 0   // 1
 * pre2 = 1
 * pre = 1
 * 
 * fib = 1 + 1   // 2
 * pre2 = 1
 * pre = 2
 * 
 * fib = 2 + 1   // 3
 * pre2 = 2
 * pre = 3
 * 
 * fib = 3 + 2   // 5
 * pre2 = 3
 * pre = 5
 * 
 * fib = 5 + 3   // 8
 * 
 * im Gegensatz zur rekursiven Variante wird fib2 
 * nur mit 5 Schleifendurchläufen abgeschlossen,
 * wohingegen in der Rekursion die Funktion 12 mal aufgerufen
 **************************************************/