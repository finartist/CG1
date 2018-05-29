/**
 * 
 * @author Kai Friedrich
 *
 */

public class Aufgabe4 {

	public static void main(String[] args) {
		System.out.println("Rekursiv x mod y berechnen");
		int x;
		int y;
		int ergebnis;
		x=WerteEinlesen.intEinlesen("gib einen Wert für x ein");
		do{
			y=WerteEinlesen.intEinlesen("gib einen Wert für y ein");
			if(y==0){
				System.out.println("Fehler: Modulo 0 ist nicht definiert");
			}
		} while(y==0);
		ergebnis=modulo(x, y);
		System.out.println(ergebnis);
		
	}
	
	public static int modulo(int x,int y){
	//	if(x==0){
	//		return 0;
//		}
		if(x<0){
			return modulo(x+y,y);
		}
		if(x<y){
			return x;
		} else {
			return modulo(x-y,y);
		}
	}
}
