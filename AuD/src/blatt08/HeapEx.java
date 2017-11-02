package blatt08;


/*
 * a) Erläutern Sie die Datenstruktur Heap (MinHeap, wie in der Vorlesung vorgestellt) an einem Beispiel und 
 * nennen Sie die Eigenschaften des Heap. Zeigen Sie die Darstellung des Heap in einem Feld. 
 * 
 * Ein Heap ist ein binärer Baum, der nicht geordnet ist. Der Elternknoten muss im Min-Heap immer kleiner sein
 * als seine Kinder und der Heap muss linksbündig gefüllt sein, wenn er nicht voll ist. Nur die letzte Ebene
 * darf damit nicht ganz gefüllt sein. Somit kann man ihn auf ein Feld abbilden. Der kleinste Eintrag ist die Wurzel.
 * 
 * Bsp:        3
 *          /     \                                                   Index 1  2  3  4  5  6
 *         4       8    -> In einem Feld mit ersten Index 1 wäre das  Wert  3  4  8  5  19 9
 *        / \     /        Denn: Index des Elternknotens = Knotenindex/2
 *       5   19  9               Index der Kindknoten = Knotenindex * 2 (+1)
 *        
 * b) In einem MinHeap seien alle Elemente verschieden. Wo befindet sich das größte Element?
 *  Das größte Element ist ein Blattknoten. Da der Heap nicht sortiert ist, kann nicht mehr gesagt werden.
 *    Ist ein Feld, das in aufsteigend sortierter Reihenfolge vorliegt, ein Heap?
 *    Ja, denn somit ist der vorhergehende Eintrag immer kleiner als ein nachfolgender -> Elternknoten < Kindknoten
 *    
 * c) Beschreiben Sie das Vorgehen, um ein ungeordnetes Feld in einen MinHeap zu überführen an folgendem Beispiel:
 *    1  2  3  4  5  6  7  8  9 10 11 12
 *    E, A, S, Y, Q, U, E, S, T, I, O, N
 *    
 *    Heap-Konstruktion Bottom-up
 *     n = 12
 *     Ebene (Index von - bis)
 *     1	  1
 *     2	  2 - 3
 *     3      4 - 7
 *     4      8 - 12
 *     
 *     fangen unten an, also Indices 8 bis 12 -> Annhame sind schon Heaps, da Blattknoten
 *     S T I O N
 *     dritte Ebene Indices 4 - 7
 *      zu Index 7(E) gehören keine Indices
 *      zu Index 6(U) gehört 12(N) U > N -> Vertauschung -> 6(N) 12(U)
 *      zu Index 5(Q) gehören 10(I) und 11(O) -> Vertauschung I < O also mit I tauschen -> 5(I) 10(Q) 11(O)
 *      zu Index 4(Y) gehören 8(S) und 9(T) -> Vertauschung S < T also Y mit S tauschen -> 4(S) 8(Y) 9(T)
 *      
 *    1  2  3  4  5  6  7  8  9 10 11 12
 *    E, A, S, S, I, N, E, Y, T, Q, O, N
 *      
 *     zweite Ebene Indices 2 - 3
 *      zu Index 3(S) gehören 6(N) und 7(E) -> Vertauschung 3(E)6(N) 7(S) -> da 7 keine Kinder -> keine weiteren Vertauschungen
 *      zu Index 2(A) gehören 4(S) und 5(I) -> passt
 *     
 *    1  2  3  4  5  6  7  8  9 10 11 12
 *    E, A, E, S, I, N, S, Y, T, Q, O, N      
 *      
 *     erste Ebene Index 1
 *      zu Index 1(E) gehören 2(A) und 3(E) -> Vertauschung -> 1(A) 2(E) 3(E) -> Heap Eigenschaft der unteren Ebene bleibt erhalten
 *    Endergebnis  
 *    1  2  3  4  5  6  7  8  9 10 11 12
 *    A, E, E, S, I, N, S, Y, T, Q, O, N          
 *      
 *    Wie groß ist die Komplexität Ihres Vorgehens in O-Notation im schlechtesten Fall?
 *     Im schlechtesten Fall O(n)
 *     8*0 + 4*1 + 2*2 + 1*4 = 12 = n Vertauschungen
 *     -> Anzahl Knoten auf Blattebene*0 + Anzahl Knoten auf nächster Ebene*1 + ... + Wurzelknoten(=1)*Höhe des Baums <= n
 */
public class HeapEx {

}
