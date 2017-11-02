package blatt01;

public class TailRecursive {

	public static void main(String[] args) {
		System.out.println(pot2TailRec(5));
		System.out.println(pot2TailRec(1));
		
		System.out.println(sumFacTailRec(21));
		System.out.println(sumFacTailRec(734));

	}
	
	public static int pot2TailRec(int n) {
		return _pot2Tail(n, 2);
	}
	
	public static int _pot2Tail(int n, int number){
		//Abbruchbedingung: wenn n kleiner gleich 1, 
		//wird die n-te Potenz von 2 zurückgegeben (number)
		if(n<=1){
			return number;
		}
		else{
			// solange n>1 wird die number*2 gerechnet
			return _pot2Tail(n-1, number*2);
		}
	}
	
	public static int sumFacTailRec(int n){
		return _sumFacTailRec(n, 1, 2);
	}

	public static int _sumFacTailRec(int n, int sum, int div){
		// wenn div größer als die Wurzel von n, wird sum zurückgeben,
		// da keine neuen Divisoren mehr gefunden werden können
		if(div*div > n){
			return sum;
		}
		// wenn ein Divisor gefunden wurde, der nicht 1 oder n ist, 
		// wird der Divisor und der größere Gegenpart zur Summe addiert
		// und div um 1 erhöht
		else if(n%div == 0 && div*div!=n && div!=1){
			return _sumFacTailRec(n, sum+div+n/div, div+1);
		}
		// wenn ein Divisor gefunden wurde wird der Divisor zur Summe addiert
		// div wird um 1 erhöht
		else if(n%div == 0){
			return _sumFacTailRec(n, sum+div, div+1);
		}
		// wird kein divisor gefunden, wird div um 1 erhöht
		else{
			return _sumFacTailRec(n, sum, div+1);
		}
	}
	

}
