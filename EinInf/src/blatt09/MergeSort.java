package blatt09;

/***********************************************
a)
1 Teile Folge in zwei gleich große Teile
2 Sortiere beide Teile unabhängig voneinander
  - hier steckt ein rekursiver Aufruf dahinter
3 Zusammenführen der sortierten Teilfolgen
   - Vergleiche jeweils die beiden kleinsten Einträge
   - „Schiebe“ den kleineren ans Ende der neuen Folge
-> Folge wird in ihre Einzelglieder zerlegt und später sortiert zusammengefügt

5, 3, 4, 7, 1, 2
-> geteilt in           5, 3, 4     und     7, 1, 2
-> geteilt in      5, 3  und  4     und     7, 1  und  2
-> geteilt in   5 und 3  und  4     und     7 und 1  und  2
-> zusammenfügen zu
                3, 5     und  4     und     1, 7  und  2
-> zusammenfügen zu
                        3, 4, 5     und     1, 2, 7
-> zusammenfügen zu
1, 2, 3, 4, 5, 7

"divide and conquer" : die Folge wird soweit geteilt, bis die Teilfolgen trivial
sind (Länge 1) und damit schon sortiert sind. Jetzt können die schon sortierten 
Teilfolgen zusammengefügt werden.

b) in diesem Algorithmus wird nur mit einer Kopie des Ursprungsarrays
und Indizes gearbeitet, wohingegen in der Vorlesung für jedes Teilen 
ein neues Array erstellt wurde, was einen viel größeren Speicherbedarf bedeutet

c)Mergesort ist stabil, da bei Gleichheit der Schlüssel erst das linke Element
eingefügt wird und somit die relative Reihenfolge erhalten bleibt.
if (copy[i] <= a[j])
	a[k++] = copy[i++];
***********************************************/

import java.util.Comparator;
import java.lang.reflect.Array;
import java.util.Collections;

public class MergeSort {
	
	// algorithm used in exercise

	private static <T> void _mergeSort(T a[], T copy[], int start, int end, Comparator <T> c) {
		if (start < end) {
			int mid = (start + end) / 2;
			_mergeSort(a, copy, start, mid, c);
			_mergeSort(a, copy, mid + 1, end, c);
			merge(a, copy, start, mid, end, c);
		}
	}

	private static <T> void merge(T a[], T copy[], int start, int m, int end, Comparator <T> c) {
		int i = 0, j = start, k;
		while (j <= m){
			copy[i++] = a[j++];
		}
		i = 0;
		k = start;
		while (k < j && j <= end) {
			if (c.compare(copy[i], a[j]) <= 0){
				a[k++] = copy[i++];
			}
			else{
				a[k++] = a[j++];
			}
		}
		while (k < j){
			a[k++] = copy[i++];
		}
	}

	public static <T> void mergeSort(T[] a, Comparator<T> c) {
		// because it is forbidden to create generic Array(new T[]), use this
		//( T[] copy = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length); )
		// instead
		//.getComponentType() is needed, because else the Array will be a T[][]
		// and ArrayStoreException is thrown
		T[] copy = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length); 
		_mergeSort(a, copy, 0, a.length - 1, c);
	}

	public static void main(String[] args) {
		
		String[] ITs = {"Jen", "Roy", "Moss", "Richmond", "Bill", "Steve", "Ada", "Mark"};
		
		mergeSort(ITs, Collections.reverseOrder());
		
		for(int i = 0; i < ITs.length; i++){
			System.out.print(ITs[i] + " , ");
		}
		
		System.out.println("");
		mergeSort(ITs, Collections.reverseOrder(Collections.reverseOrder()));
		
		for(int i = 0; i < ITs.length; i++){
			System.out.print(ITs[i] + " , ");
		}
	}
}
