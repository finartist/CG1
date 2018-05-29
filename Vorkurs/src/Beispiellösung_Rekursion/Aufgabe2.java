
/**
 * 
 * @author Kai Friedrich
 *
 */

public class Aufgabe2 {
	
	public static void main(String[] args) {
		System.out.println("Rekursiv a-b berechnen");
		
		int minuend;
		int subtrahend;
		int differenz;
		minuend=WerteEinlesen.intEinlesen("gib einen Wert für a ein");
		subtrahend=WerteEinlesen.intEinlesen("gib einen Wert für b ein");
		
		differenz =minus(minuend,subtrahend);
		System.out.println(minuend+"-"+subtrahend+"=" + differenz );
	}
	
	public static int minus(int minuend, int subtrahend){
		if(subtrahend==0){
			return minuend;
		}
		if(subtrahend>0){
			return minus(minuend-1,subtrahend-1);
		}
		else{ //negativer Subtrahend
			return minus(minuend+1,subtrahend+1);
		}
	}
}
