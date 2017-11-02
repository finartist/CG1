package blatt09;

import blatt05.Clock;

public class QuickSort {
	
	/*
	 * Wähle Pivotelement (z.b mitte des Arrays)
	 * sortiere alle Elemente > als Pivot nach rechts, alle < nach links
	 *  -> sortieren mit tauschen von Elementen
	 *  -> füge Pivot an richtige Stelle ein
	 * suche für rechte und linke Seite Pivot wdh. Schritt oben
	 */

	public static <T extends Comparable<T>> void quickSort(T[] a) {
		_quicksort(a, 0, a.length - 1); // [a0, . . . ,an-1]
	}

	static <T extends Comparable<T>> void _quicksort(T[] a, int l, int r) {
		if (r > l) { // [al, . . . ,ar]
			int m = partition(a, l, r); // pivot p = am
			_quicksort(a, l, m - 1);
			_quicksort(a, m + 1, r);
		}
	}

	static <T extends Comparable<T>> int partition(T[] a, int l, int r) {
		assert (l <= r);
		T p = a[r], t; // pivot
		int i = l - 1, j = r;
		do {
			do
				++i;
			while (a[i].compareTo(p) < 0); // find
			do
				--j;
			while (j > 0 && a[j].compareTo(p) > 0);
			t = a[i];
			a[i] = a[j];
			a[j] = t; // swap
		} while (i < j);
		a[j] = a[i];
		a[i] = a[r];
		a[r] = t;
		return i; // new index of pivot
	}

	public static void main(String[] args) {
		Clock[] randClocks = new Clock[30];
		for (int i = 0; i < 30; i++) {
			randClocks[i] = Clock.createRandom();
			System.out.print(" " + randClocks[i]);
		}

		quickSort(randClocks);
		System.out.println("");

		for (int i = 0; i < 30; i++) {
			System.out.print(" " + randClocks[i]);
		}
	}

}
