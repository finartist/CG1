
public class ScopesundInkrement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a = 3;
		
		if(a<5) {
			// do something
			System.out.println(a);
			int instabil = 4;
			System.out.println(instabil);
		}

		
		// System.out.println(instabil); // instabil ist ein scope, also eine lokale Variable, die nur innerhalb des if-Statements existiert
		// das gleiche passiert bei Variablen innerhalb von Funktionen, Schleifen, etc.
		
		// Inkrement und Dekrement
		a += 3; // == a = a+3 erh�ht um 3
		a++; // erh�ht um 1
		++a;
		a = a+1;
		// alles �quivalent
		
		a--; // verringert um 1
		--a;
		a = a-1;
		a -= 1;
		
		int x = 3;
		int y = 5;
		
		// geht, wird von rechts nach links ausgef�hrt
		x = y = x+3;
		
		// syntax error
		// x = y+3 = x+3;
		
		// hier ist ein Unterschied
		x = y++; // x = y, y = y+1
		
		x = ++y; // y = y+1, x=y
		
		//break und continue
		 // break - > bricht aus der kleinsten ihn umgebenden Schleife aus (Schleife wird nicht weiter ausgef�hrt)
		 // continue -> bricht aktuellen Schleifendurchlauf ab (Schleife wird mit n�chstem Durchlauf fortgesetzt)
	}

}
