
public class Aufgabe_19 {

	
	public static void main(String[] args) {
		System.out.println("Aufgabe 19");
		int [] values = new int[2];
		values[0] = 5;
		values[1] = 10;
		
		System.out.println("Werte vor swap");
		System.out.println("Wert 0 = " + values[0]);
		System.out.println("Wert 1 = " + values[1]);
		swap(values);
		System.out.println("Werte nach swap");
		System.out.println("Wert 0 = " + values[0]);
		System.out.println("Wert 1 = " + values[1]);
		
	}
	public static void swap(int[] values){
		int temp = values[0];
		values[0] = values[1];
		values[1] = temp;
	}
}
