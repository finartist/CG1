
public class Rekursion {

	public static void main(String[] args) {
		// 1. fakultät(x) --> x! = 1*2*3* ... *x
		System.out.println(fakultät(5));
		//2. minus(x, y) nur über Inkrement und Dekrement
		System.out.println(minus(3, 3));
		//3. mal
		System.out.println(mal(3, 9));
		//4. hoch
		System.out.println(hoch(2, 4));
		//5. mod
		System.out.println(mod(9, 7));
		//6. div
		div(16, 7);
		//7. reverse String
		System.out.println(reverse("hallo"));
		//8. summe array
		int[] array = {0, 1, 2, 3, 3, 3};
		System.out.println("summe Array: " + summeArray(array));
		//9. ggt
		System.out.println("ggt " + ggT(18, 6));
		//10. fibonacci f(0) = f(1) = 1, f(n) = f(n-1) + f(n-2)
		//11. collatz-folge
		System.out.println("collatz");
		collatz(10930494);
		//12. pascalsches dreieck
		/*
		 * Lösungsansatz
		 * pascal(a,b) = falls a=0 -> 1
		 * 				 falls b=0 -> 1
		 * 				 falls b=a -> 1
		 * 				 sonst pascal(a-1, b-1) + pascal(a-1, b)  ineffizient! -> einige Zahlen werden mehrfach berechnet
		 * 
		 * effizientere Lösung
		 * p(n): gibt n'te Zeile zurück z.B. p(4) = 1 3 3 1
		 * int[] p(n){
		 * 	if(n <= 1)
		 * 		syso[1]
		 * 		return[1]
		 * 	else
		 * 		lz = p(n-1)
		 * 		nz = new int[n]
		 * 		nz[0] = 1
		 * 		nz[n-1] = 1
		 * 		for(i = 1, i<n-1, i++)
		 * 			nz[i] = lz[i-1] + lz[i]
		 * 		syso nz
		 * 		return nz
		 */
	}
	
	public static int fakultät(int x){
		if (x == 1){
			return 1;
		}
		else{
			return x*fakultät(x-1);
		}
	}
		
	public static int minus(int x, int y){
		if (y == 0){
			return x;
		}
		else if(y<0){
			return minus(++x, ++y);
		}
		else{
			return minus(--x, --y);
		}
	}
	
	public static int mal(int x, int y){
		if(y == 0 || x == 0){
			return 0;
		}
		else if(y == 1){
			return x;
		}
		else{
			return x + mal(x, y-1);
		}
		
	}

	public static int hoch(int x, int y){
		if(x == 0){
			return 0;
		}
		else if(y == 0){
			return 1;
		}
		else if(y == 1){
			return x;
		}
		else{
			return mal(x,hoch(x, --y));
		}
	}
	
	public static int mod(int x, int y){
		if(y == 0){
			return x;
		}
		else if( x == y){
			return 0;
		}
		else if(x < y){
			return x;
		}
		else{
			return mod(x-y, y);
		}
	}
	
	public static void div(int x, int y){
		if(y == 0){
			System.out.println("Keine Division durch 0.");
		}
		else{
			System.out.println(divint(x, y));
		}
	}
	private static int divint(int x, int y){
		if (x == 0){
			return 0;
		}
		else if(y == 1){
			return x;
		}
		else if(x < y){
			return 0;
		}
		else{
			return 1 + divint(x - y, y);
		}
	}
	
	public static String reverse(String text){
		String rev = "";
		int i = text.length()-1;
		int z = 0;
		if (i == 0){
			return rev;
		}
		else{
			return rev + text.charAt(revchar(i));
		}
	}
	private static int revchar(int i){
			if(i == 0){
				return 0;
			}
			else{
				return revchar(i-1);
			}
	}
	
	public static int ggT(int x, int y){

		if( y == 0 ){
			return x;
		}
		else{
			return ggT(y, x % y);
		}
	}
	
	public static int summeArray(int [] array){
		int n = array.length;
		return summe(array, n);
		
	}
	private static int summe(int [] array, int indexlength){
		if (indexlength == 0){
			return array[0];
		}
		else{
			return summe(array, indexlength-1) + array[indexlength-1];
		}
	}
	
	public static void collatz(int n){
		System.out.println(n);
		if(n == 1){
			return;
		}
		else if(n%2 == 0){
			collatz(n/2);
		}
		else{
			collatz(3*n+1);
		}
	}
}

