import java.util.Random;
import java.util.Scanner;

public class MethodenAufgaben {

	public static void main(String[] args) {
		/* 1. Schreibe eine Funktion, die ein zufälliges Array erzeugt und ausgibt.
		 * 2. Modifiziere die Funktion aus Aufgabe 1 so, dass das erzeugte Array nicht ausgegeben, sondern zurückgegeben wird.
		 * 3. Folien 6.1 -6.4 (zu 6.1: übergebt die zu tauschenden Werte in einem Array)
		 * public static int[] swap(int[] toSwap)
		 * 4. Überlade die Funktion aus Aufgabe 1, die 3 Parameter entgegen nimmt: die mindeste und höhste Größe des Arrays, 
		 *    sowie die größte Zahl, die im Array stehen kann.
		 * 5. Geht eure fertigen Aufgaben durch und gliedert sinnvolle Codeabschnitte in Funktionen aus.
		 */
		Scanner scn = new Scanner(System.in);
		int minlength = scn.nextInt();
		int maxlength = scn.nextInt();
		int maxnumber = scn.nextInt();
		
		randomArr(minlength, maxlength, maxnumber);
		
	}

	//1., 2., 4.
	
	public static int[] randomArr(int min, int max, int maxnum){
		Random rng = new Random();
		int[] randomArray = new int[rng.nextInt(max-min+1)+min];
		
		for(int i = 0; i < randomArray.length; i++){
			randomArray[i] = rng.nextInt(maxnum + 1);
			System.out.print(randomArray[i] + " ");
		}

		return randomArray;		
	}
	
	
	
}
