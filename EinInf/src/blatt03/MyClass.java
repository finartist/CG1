package blatt03;

public class MyClass {

	/**************************************
	 * a) Diese Methode beschreibt die Subtraktion zweier Zahlen y von x. 
	 * Bsp.
	 * x=9 , y=3 
	 * y!=0 -> x=8, y=2 
	 * y!=0 -> x=7, y=1 
	 * y!=0 -> x=6, y=0 
	 * y==0 -> Abbruch x=6 wird zurück gegeben
	 * 
	 * Bsp. x=3, y=0 
	 * y==0 -> Abbruch x=3 wird zurück gegeben
	 * 
	 * Bsp. x=2 y=3 
	 * y!=0 -> x=1, y=2 
	 * y!=0 -> x=0, y=1 
	 * y!=0 -> x=-1, y=0 
	 * y==0 -> Abbruch x=-1 wird zurück gegeben
	 * 
	 * Der Algorithmus terminiert nicht für y<0 bzw. er terminiert nur über den
	 * Overflow, da y bereits kleiner als 0 ist und die 0 nicht mehr erreicht 
	 * werden kann, wenn y verkleinert wird.
	 ***********************************************/

	public static void main(String[] args) {
		System.out.println(iter(8, -3));
		System.out.println(rek(9, -9));
		System.out.println(args[0]);

	}

	public static int rek(int x, int y) {
		if (y == 0) {
			return x;
		} else if (y < 0) {
			return rek(++x, ++y);
		} else {
			return rek(--x, --y);
		}
	}

	public static int iter(int x, int y) {
		while (y != 0) {
			if (y < 0) {
				x++;
				y++;
			} else {
				x--;
				y--;
			}
		}
		return x;

	}

}
