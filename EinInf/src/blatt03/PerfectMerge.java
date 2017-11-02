package blatt03;
import java.util.Arrays;

public class PerfectMerge {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4};
		int[] b = {5, 6, 7, 8};
		System.out.println(Arrays.toString(interleave(a, b)));
		System.out.println(Arrays.toString(perfectMerge(a)));
		System.out.println(mergeNumber(52));
	}

	public static int[] interleave(int[] a1, int[] a2) {
		// Das gemischte Array hat die doppelte Länge eines Argumentarrays
		int[] mergedArray = new int[a1.length*2];
		// Aus jedem Array wird abwechselnd ein Element in das gemischte Array aufgenommen
		for(int i = 0; i < a1.length ; i++){
			mergedArray[i*2] = a1[i];
			mergedArray[i*2 + 1] = a2[i];
		}
		return mergedArray;
	}

	public static int[] perfectMerge(int[] a) {
		// Das übergebene Array wird in 2 gleiche Hälften geteilt
		// Dazu kann man die Methode Arrays.copyOfRange benutzen
		// mit der man einen Bereich eines Arrays kopieren kann
		int[] firstHalf = Arrays.copyOfRange(a, 0, a.length/2);
		int[] secondHalf = Arrays.copyOfRange(a, a.length/2, a.length);
		//Mit der bereits erstellten Methode kann man diese Hälften nun mischen
		int[] Merged = interleave(firstHalf, secondHalf);
		
		return Merged;
	}

	public static int mergeNumber (int n) {
		// ein Testarray der Länge n wird erstellt
		int [] testArray = new int[n];
		int counter = 0;
		// Das Testarray wird mit Zahlen gefüllt
		for(int i = 0; i < n; i++) {
			testArray[i] = i+1;		
		}
		// Das Testarray wird geclont, um ein Vergleichsarray zu haben
		int [] original = testArray.clone();
		// Es wird solange gemischt, bis die gleiche Reihenfolge wieder hergestellt wurde
		do{
			counter++;
			testArray = perfectMerge(testArray);
			System.out.println(counter + ". Mischvorgang:");
			System.out.println(Arrays.toString(original));
			System.out.println(Arrays.toString(testArray));
			System.out.println("");
		} while (!Arrays.equals(original, testArray));
		return counter;
	}
	
}
