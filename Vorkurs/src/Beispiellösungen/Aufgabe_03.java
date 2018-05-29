
public class Aufgabe_03 {
	public static void main(String[] args) {

		System.out.println("Aufgabe 3");

		int a = 3;
		int b = 5;

		int temp;

		temp = a;
		a = b;
		b = temp;

		System.out.println("a hat den Wert " + a);
		System.out.println("b hat den Wert " + b);

		// Zusatz ohne Hilfsvariable
		System.out.println("Zusatz ohne Hilfsvarible");
		a = 3;
		b = 5;

		a = a + b;
		b = a - b;
		a = a - b;

		System.out.println("a hat den Wert " + a);
		System.out.println("b hat den Wert " + b);
	}
}
