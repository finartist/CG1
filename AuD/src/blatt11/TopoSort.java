package blatt11;

public class TopoSort {
/*
 * 1a)          5           kanten nach unten gerichtet
 *         /    |    \
 *        4     13   15
 *         \    |    /
 *              7
 *              |
 *              16
 *        /  /  |  \  \
 *       11  1  2   8  14
 *        \  \  |   /  |
 *         \  \ |  /   6
 *          \  || /   /
 *              10
 *              |
 *              12
 *              |
 *              9
 *              |
 *              3
 * b) 5, 4, 13, 15, 7, 16, 11, 1, 2, 8, 14, 6, 10, 12, 9, 3
 *    Quelle = Knoten ohne eingehende Kanten, Senke = Knoten ohne ausgehende Kanten
 *    
 *    Finde alle Knoten ohne eingehenden Kanten (Quellen)
	  Gib diese Knoten aus und . . .
      . . . entferne sie zusammen mit allen ausgehenden Kanten
      Wiederholen bis Graph leer ist
      
      ALTERNATIVE: 
      Starte von allen Senken
      DFS in umgekehrter Richtung (entlang eingehender Kanten)
      Gib Knoten aus, wenn alle eingehenden Kanten abgearbeitet
      
      -> bei mehreren Quellen/Senken ist die Reihenfolge nicht eindeutig
 * 
 * 
 * 2. In Graph dürfen keine Zykel vorhanden sein, damit topologisch sortierbar (in Zykeln können Abhängigkeiten
 *    nicht in eindeutige Reihenfolge gebracht werden).
 *    -> es müssen mindestens 4 Kanten entfernt werden
 *    *    (z.B. Spock->Sissors
 *          Rock->Sissors
 *          Spock->Rock
 *          Lizard->paper)
 */
}
