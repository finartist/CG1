class Hanoi2 {
	static int starthoehe;
	static int bewegungen = 0;

	public static void bewege(char von, char nach) {
		System.out.println(" Scheibe  von  " + von + "  nach  " + nach);
		bewegungen++;
	}

	public static void turm(char start, char ziel, char ablage, int hoehe) {
		if (hoehe > 1) {
			turm(start, ablage, ziel, (hoehe - 1));
		}
		bewege(start, ziel);
		if (hoehe > 1) {
			turm(ablage, ziel, start, (hoehe - 1));
		}
	}

	public static void main(String[] args) {
		
		starthoehe = WerteEinlesen.positivenIntEinlesen("Wie hoch soll der \"Turm von Hanoi\" sein ? ");
		System.out.println(" Die Ausgabe f�r einen \"Turm von Hanoi \" " + "der H�he " + starthoehe + ".");
		System.out.println(" Die St�be sind mit A, B und C bezeichnet. ");
		System.out.println(" Der Turm soll von A nach C transportiert werden.");
		turm('A', 'C', 'B', starthoehe);
		// von A nach C ueber B
		System.out.println(" Es waren " + bewegungen + " Bewegungen f�r die Turmhoehe " + starthoehe + " n�tig.");
		System.out.println("Dies kann auch durch (2^n)-1 berechnet werden.");
	}
}