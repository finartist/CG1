package blatt12;

public class GreedyvsDyn {
/*
 * Objekt 	Größe 	Wert  Profitdichte
     a1       3      3         1
     a2       4      5         1,25
     a3       6      8         1,3333333
     a4       7      9         1,2857
     
*  Rucksack Größe: 15
*  nach Greedy Prinzp: Kriterium Wert
*   nimm Gegenstand a4, a3 -> Wert 17, Größe 13
*                      Kriterium Größe
*   nimm Gegenstand a1, a2, a3 -> Wert 16, Größe 13
*                      Kriterium Profitdichte
*   nimm Gegenstand a3, a4 -> Wert 17, Größe 13
*   
*   -> man erkennt, dass z.B. die Sortierung nach Größe nicht optimal ist
*   
*   
*   dynamische Programmierung:
*    Das Problem ist rekursiv lösbar. Bei einer rekursiven Berechnung werden Teillösungen aber mehrfach berechnet,
*    deshalb werden Teillösungen in einer Tabelle über 2 Indices (Größe und Gegenstand) gespeichert. Diese Tabelle wird
*    iterativ gefüllt. Optimale Teillösungen sollten sich überlappen, d.h. voneinander unabhängige Teile können wiederum
*    gemeinsame Teilprobleme enthalten (im Gegensatz zu divide and conquer Ansatz) Jede Teillösung wird einmal berechnet 
*    und gespeichert
*    
*       g
*    k  0   1   2   3   4   5   6   7   8   9   10   11   12   13   14   15
*    0  0   0   0   0   0   0   0   0   0   0   0    0    0    0    0    0
*    a1 0   0   0   3   3   3   3   3   3   3   3    3    3    3    3    3
*    a2 0   0   0   3   5   5   5   5   8   8   8    8    8    8    8    8
*    a3 0   0   0   3   5   5  (8)  8   8   8   8    8    8    8    8    8
*    a4 0   0   0   3   5   5   8   9   9   11  12   14   14  (17)  17   17  
*    -> schau in letzter Zeile, wo höchste Zahl (17)
*      erstmals aufgetreten
*      kommt nicht aus Zeile darüber -> gehe um Größe zurück
*      und 1 Zeile höher
*                                                                               
*    -> an dieser Stelle steht (8) -> kommt nicht aus Zeile darüber
*    -> gehe um Größe zurück -> da null
*                                                                              
*    -> Gegenstand 4 und 3 sind in dem Rucksack
*                                                                               
 */
}
