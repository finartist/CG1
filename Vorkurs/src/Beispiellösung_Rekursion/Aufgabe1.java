package Beispiellösung_Rekursion;
/**
 * @author Kai Friedrich
 */

public class Aufgabe1 {

	public static void main(String[] args) {
		int anfang = 1;
		int ende = 10;
		
		System.out.println("iterativ:");
		iterativ(anfang,ende);
		System.out.println("rekursiv:");
		rekursiv(anfang,ende);
	}

	public static void rekursiv(int aktuellerWert, int ende){
		System.out.println(aktuellerWert);
		if(aktuellerWert<ende){
			rekursiv(aktuellerWert+1,ende);
		}
		System.out.println(aktuellerWert);
	}
	
	public static void iterativ(int anfang, int ende) {
		for (int i = anfang; i <= ende; i++) {
			System.out.println(i);
		}
		for (int i = ende; i >= anfang; i--) {
			System.out.println(i);
		}
	}

}
