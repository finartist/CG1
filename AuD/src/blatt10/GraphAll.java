package blatt10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphAll{
/*
 * a)Was ist ein Graph? Erläutern Sie die unterschiedlichen Arten von Graphen.
 * 
 * Ein Graph ist ein beliebiges Netzwerk von Knoten, die über Kanten verbunden sind.
 *  ungerichteter Graph: Knoten sind über Kanten verbunden
 *                       Kante ist in beide Richtungen definiert(also ist sie sowohl aus- als auch eingehend, Menge von 2 Knoten)
 *                       keine Schleifen
 *                       
 *  gerichteter Graph:   Knoten sind über Kanten verbunden
 *                       Kanten haben eine Richtung (Kante ist geordnetes Paar von Knoten)
 *                       Kann Schleifen geben
 *                       
 *  gewichteter Graph:   kann sowohl gerichtet, als auch ungerichtet sein
 *                       Kanten haben Gewicht (z.B. Kapazität, Länge, etc
 * 
 * V = {1, 2, 3, 4, 5},
 * E = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 4}, {2, 5}, {3, 4}, {4, 5}} 
 * c) Notieren Sie für diesen Graphen

    die Kantenliste,
    int[] edgelist = {5, 8, 1, 2, 1, 3, 1, 4, 1, 5, 2, 4, 2, 5, 3, 4, 4, 5}
	Erste zwei Einträge bezeichnen die Anzahl Knoten (5) und Kanten (8)
    Je zwei aufeinanderfolgende Einträge bezeichnen eine Kante

    die Knotenliste,
    int[] nodelist = { 5,8, 4,2,3,4,5  3,1,4,5,  3,1,2,4, 4,1,2,3,5,  2,1,4 };
	Erste zwei Einträge bezeichnen die Anzahl Knoten (5) und Kanten (8)
    Nächste Zahl speichert Anzahl Kanten
    Es folgt Liste der Nachbarknoten
    
    oder
    Nachbarschaften für alle Knoten, Liste der Startpositionen in Nachbarschaftsliste
	int[] neighborhoods = { 2,3,4,5  1,4,5, 1,2,4, 1,2,3,5, 1,2,4};
	// von index 0 bis 4-1 hat knoten 1 Nachbarshaften
	// von index 4 bis 7-1 hat knoten nachbarschaften
	int[] nodeNhd = { 0, 4, 7, 10, 14, 17 };
    
    die Adjazenzmatrix,
    int[][] = {        ->ausgehend Spalten/Zeilenindex = Knotennummer
    
    {0, 1, 1, 1, 1},   |
    {1, 0, 0, 1, 1},   v eingehend
    {1, 0, 0, 1, 0},
    {1, 1, 1, 0, 1},
    {1, 1, 0, 1, 0}
    }
    
    die Adjazenzliste
    1 - 2, 3, 4, 5
    2 - 1, 4, 5
    3 - 1, 2, 4
    4 - 1, 2, 3, 5
    5 - 1, 2, 4
    -> z.B als Array von Listen oder Listen von Listen implementierbar	
 * und geben Sie die entsprechenden Datenstrukturen in Java an.
 */
 // d) Geben Sie die Algorithmen (in Java) an, die die Überführung

   // einer Kantenliste in eine Adjazenzmatrix,
    public static int[][] toMatrix(int[] edges){
        int[][] matrix = new int[edges[0]][edges[0]];
        for(int i = 2; i < edges.length; i+=2){
            matrix[edges[i]][edges[i+1]] = 1;
            matrix[edges[i+1]][edges[i]] = 1;
        }
        return matrix;
    }
    
    //eine Kantenliste in eine Adjazenzliste und
	public static LinkedList<Integer>[] toList(int[] edges){
		LinkedList<Integer>[] list = new LinkedList[edges[0]];
		for(int i = 0; i < list.length; i++){
			list[i] = new LinkedList<Integer>();
		}
		for(int i = 2; i < edges.length; i+=2){
            list[edges[i]].add(edges[i+1]);
            list[edges[i+1]].add(edges[i]);
        }
		System.out.println(list[1].toString());
        return list;
    }
	
	public static void main(String[] args) {
		int[] edges = {3, 4, 0,1, 2, 1, 1, 1, 0, 2};
		LinkedList<Integer>[] adjlist = toList(edges);
		int[][] adjmatrix = toMatrix(edges);
		System.out.println(adjmatrix[1][1]);
	}
	
/*	realisieren. Wie hoch ist jeweils der Aufwand in O-Notation? 
 * 
 * Komplexität: O(Kantenanzahl) (+Knotenzahl für Listeninitialisierung, verschachteltes Array vllt besser) (jede Kante muss angefasst werden, hinzufügen zu matrix/liste in O(1))
 */
}
