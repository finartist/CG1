package blatt12;

public class GreedyvsDyn {
/*
 * Objekt 	Gr��e 	Wert  Profitdichte
     a1       3      3         1
     a2       4      5         1,25
     a3       6      8         1,3333333
     a4       7      9         1,2857
     
*  Rucksack Gr��e: 15
*  nach Greedy Prinzp: Kriterium Wert
*   nimm Gegenstand a4, a3 -> Wert 17, Gr��e 13
*                      Kriterium Gr��e
*   nimm Gegenstand a1, a2, a3 -> Wert 16, Gr��e 13
*                      Kriterium Profitdichte
*   nimm Gegenstand a3, a4 -> Wert 17, Gr��e 13
*   
*   -> man erkennt, dass z.B. die Sortierung nach Gr��e nicht optimal ist
*   
*   
*   dynamische Programmierung:
*    Das Problem ist rekursiv l�sbar. Bei einer rekursiven Berechnung werden Teill�sungen aber mehrfach berechnet,
*    deshalb werden Teill�sungen in einer Tabelle �ber 2 Indices (Gr��e und Gegenstand) gespeichert. Diese Tabelle wird
*    iterativ gef�llt. Optimale Teill�sungen sollten sich �berlappen, d.h. voneinander unabh�ngige Teile k�nnen wiederum
*    gemeinsame Teilprobleme enthalten (im Gegensatz zu divide and conquer Ansatz) Jede Teill�sung wird einmal berechnet 
*    und gespeichert
*    
*       g
*    k  0   1   2   3   4   5   6   7   8   9   10   11   12   13   14   15
*    0  0   0   0   0   0   0   0   0   0   0   0    0    0    0    0    0
*    a1 0   0   0   3   3   3   3   3   3   3   3    3    3    3    3    3
*    a2 0   0   0   3   5   5   5   5   8   8   8    8    8    8    8    8
*    a3 0   0   0   3   5   5  (8)  8   8   8   8    8    8    8    8    8
*    a4 0   0   0   3   5   5   8   9   9   11  12   14   14  (17)  17   17  
*    -> schau in letzter Zeile, wo h�chste Zahl (17)
*      erstmals aufgetreten
*      kommt nicht aus Zeile dar�ber -> gehe um Gr��e zur�ck
*      und 1 Zeile h�her
*                                                                               
*    -> an dieser Stelle steht (8) -> kommt nicht aus Zeile dar�ber
*    -> gehe um Gr��e zur�ck -> da null
*                                                                              
*    -> Gegenstand 4 und 3 sind in dem Rucksack
*                                                                               
 */
}
