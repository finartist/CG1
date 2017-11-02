package blatt09;

public class SelectionInsertion {

	//Selection Sort
	/* starte an Position 0 im Array und vergleiche mit allen anderen
	 * wenn das Minimum gefunden wurde im Rest -> Tausche Elemente aus (hier geht stabilität verloren!)
	 * wenn Pos 0 das Minimum -> mache weiter mit Pos 1
	 * BSP: [11a, 1, 11b, 0]
	 * Pos0 = 11a
	 *  vergleiche mit Pos1,2 und 3. Pos 3 hat Minimum -> Tausche aus: 
	 *  [0, 1, 11b, 11a]
	 * Pos1 = 1 -> ist Minimum
	 * POs2 = 11b -> ist Minimum
	 * Pos3 = 11a -> ist Minimum
	 * -> 11b vor 11a -> relative Reihenfolge verloren
	 */
	//InsertionSort
	/* vergleiche ersten beiden -> falls falsche Reihenfolge -> tauschen
	 * schaue Pos2 an: füge in richtige Position ein in bereits sortierte Folge von Pos0-1
	 * schaue Pos3 an: füge in richtige Position ein in bereits sortiertr Folge von Pos0-2
	 * ...
	 * Stabil, wenn von hinten vergleichen
	 */
	
}
