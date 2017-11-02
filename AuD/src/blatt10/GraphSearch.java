package blatt10;

import aud.example.graph.BreadthFirstSearch;
import aud.example.graph.DepthFirstSearch;
import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;
import aud.util.DotViewer;
import aud.util.SingleStepper;

/*
 * a) Geben Sie an, in welcher Reihenfolge Sie bei einer Breitensuche den obigen Graphen die einzelnen Knoten 
 * (mit den dazugehörigen Distanzen) erreichen, wenn Sie im Knoten 8 starten. Wenn mehrere Knoten als nächstes 
 * besucht werden können, wählen Sie jeweils den mit dem wertmäßig kleinsten Wert. 
 * Beschreiben Sie dabei kurz den zugrunde liegende Algorithmus. 
 * 
 *    5-------------------------7         Queue: 8 (0)
 *    |\ \____                  |                1, 2, 3, 5 (1)
 *    | \      8---3--------    |                2, 3, 5, (1) 6, 7 (2)
 *    |   \    |\_______    |   |                3, 5, (1) 6, 7 (2) 
 *    |     \  2_       \   |   |                5,(1) 6, 7 (2)
 *    |       \  \-----\ | /    |                6, 7, 4(2)
 *    4---------6--------1------/                7, 4(2)
 *                                               4(2)
 *                                               -
 *                                               -> 8 (0) 1, 2, 3, 5 (1) 6, 7, 4 (2)
 * Breitensuche (BFS): Startknoten wird in Queue gelegt und markiert. 
 * Knoten aus Queue geholt -> angrenzende Knoten in Queue gelegt und markiert, außer Knoten ist bereits markiert
 * -> solange durchführen, bis alle Knoten markiert.
 * 
 * b)Beschreiben Sie den Algorithmus zur Tiefensuche am obigen Beispiel. Welche Reihenfolge ergibt sich 
 * bei einer Tiefensuche, wenn Sie im Knoten 8 starten?
 * 
 *  Stack: 8
 *         5, 3, 2, 1
 *         7, 6, 4, 3, 2, 1
 *         6, 4, 3, 2, 1
 *         4, 3, 2, 1
 *         3, 2, 1
 *         2, 1
 *         1
 *         -
 *         -> 8, 5, 7, 4, 6, 3, 2, 1
 * Tiefensuche (DFS): Startknoten auf Stack gepackt und markiert. Knoten von Stack nehmen und angrenzende Knoten auf Stack
 * packen und markieren-> Knoten von Stack holen etc. außer Knoten bereits markiert
 * 
 * c) Erzeugen und visualisieren Sie für den den obigen Graphen die Breiten- und Tiefensuche 
 * mit Hilfe der Klassen MyGraph, BreadthFirstSearch, DepthFirstSearch, ... 
 */

public class GraphSearch{
	public static void main(String[] args) {
		MyGraph myGraph = new MyGraph(false);
		
		MyNode a = myGraph.addNode();
		a.setLabel("1");
		MyNode b = myGraph.addNode();
		b.setLabel("2");
		MyNode c = myGraph.addNode();
		c.setLabel("3");
		MyNode d = myGraph.addNode();
		d.setLabel("4");
		MyNode e = myGraph.addNode();
		e.setLabel("5");
		MyNode f = myGraph.addNode();
		f.setLabel("6");
		MyNode g = myGraph.addNode();
		g.setLabel("7");
		MyNode h = myGraph.addNode();
		h.setLabel("8");
		
		myGraph.addEdge(a, b);
		myGraph.addEdge(a, c);
		myGraph.addEdge(a, h);
		myGraph.addEdge(a, f);
		myGraph.addEdge(a, g);
		myGraph.addEdge(b, h);
		myGraph.addEdge(c, h);
		myGraph.addEdge(d, e);
		myGraph.addEdge(d, f);
		myGraph.addEdge(e, f);
		myGraph.addEdge(e, g);
		
		DotViewer v = DotViewer.displayWindow(myGraph, "Graph");
		SingleStepper s = new SingleStepper("Graph");
		
		BreadthFirstSearch bfs = new BreadthFirstSearch(myGraph);
		bfs.singlestepper = s;
		bfs.start(h);
		
		DepthFirstSearch dfs = new DepthFirstSearch(myGraph);
		dfs.singlestepper = s;
		dfs.start(h);
	}
}