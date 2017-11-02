package blatt08;

import java.util.Arrays;
import java.util.Random;

public class TernarySearch {
	static final int UNDEF = -1;

	public static int find(int[] a, int x) {
		return _find(a, 0, a.length - 1, x, 1, 0);
	}

	private static int _find(int[] a, int l, int r, int x, int count, int comp) {
		if (l > r){
			System.out.println("Calls binary: " + count);
			System.out.println("Comparions binary: " + comp);
			return UNDEF;
		}
		int m = (l + r) / 2;
		comp++;
		if (x == a[m]){
			System.out.println("Calls binary: " + count);
			System.out.println("Comparsions binary: " + comp);
			return m;
		}
		comp++;
		if (x < a[m])
			return _find(a, l, m - 1, x, ++count, comp);
		else
			return _find(a, m + 1, r, x, ++count, comp);
	}

	public static int ternaryRec(int a[], int x) {
		return _ternaryRec(a, 0, a.length - 1, x, 1, 0);
	}
	
	private static int _ternaryRec(int[] a, int l, int r, int x, int count, int comp) {
		if (l > r){
			System.out.println("Calls ternary: " + count);
			System.out.println("Comparsions ternary: " + comp);
			return UNDEF;
		}
		int m1 = (l + r) / 3; //(r-l)/3 +l
		int m2 = 2*(l+r) / 3; //2*(r-l)/3 +l
		
		comp++;
		if (m1 < l || m2 > r){
			m1 = l;
			m2 = r;
		}
		comp++;
		if (x == a[m1]){
			System.out.println("Calls ternary: " + count);
			System.out.println("Comparsions ternary: " + comp);
			return m1;
		}
		comp++;
		if(x == a[m2]){
			System.out.println("Calls ternary: " + count);
			System.out.println("Comparsions ternary: " + comp);
			return m2;
		}
		comp++;
		if (x < a[m1]){			
			return _ternaryRec(a, l, m1 - 1, x, ++count, comp);
		}
		comp++;
		if(x > a[m1] && x < a[m2]){
			return _ternaryRec(a, m1 + 1, m2 - 1, x, ++count, comp);
		}
		else{
			return _ternaryRec(a, m2 + 1, r, x, ++count, comp);
		}
	}

	public static void main(String[] args) {
		Random rnd = new Random();
		int[] a1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		int[] a2 = {2, 4, 5, 6, 7, 9, 2};
		int[] a3 = new int[10000];
		for(int i = 0; i < a3.length; i++){
			a3[i] = rnd.nextInt(10000);
		}
		Arrays.sort(a3);
		
		System.out.println(find(a1, 3));
		System.out.println(ternaryRec(a1, 3));
		System.out.println("");
		
		System.out.println(find(a1, 4));
		System.out.println(ternaryRec(a1, 4));
		System.out.println("");
		
		System.out.println(find(a2, 5));
		System.out.println(ternaryRec(a2, 5));
		System.out.println("");
		
		System.out.println(Arrays.toString(a3));
		System.out.println(find(a3, 19));
		System.out.println(ternaryRec(a3, 19));
	}
	
	/* c) Die Tenäre Suche macht erst bei sehr großen n Sinn, da die binäre Suche hier 
	 * in einem Stackoverflow enden könnte und es Sinn macht, ein Suchverfahren mit weniger Aufrufen 
	 * zu nutzen wie die tenäre Suche.
	 */
}
