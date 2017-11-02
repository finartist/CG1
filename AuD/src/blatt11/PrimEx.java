package blatt11;

import aud.example.graph.MyEdge;
import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;
import aud.example.graph.PrimMinimumSpanningTree;
import aud.example.graph.Traversal;
import aud.example.graph.TraversalExample;
import aud.util.DotViewer;
import aud.util.SingleStepper;

public class PrimEx {
/*
 * MST = Minimum Spanning Tree = minimaler Spannbaum
 * Algorithmus ähnlich zu Dijkstra, aber Priorität ist minimale Kantenlänge
 * 
 * Queue:
 * |0[d:0,p:-1]
 * |1[d:2,p:0], 3[d:3,p:0], 2[d:5,p:0]
 * |3[d:3,p:0], 5[d:4,p:1], 2[d:5,p:0]
 * |2[d:1,p:3], 5[d:4,p:1]
 * |5[d:4,p:1], 4[d:7,p:2]
 * |4[d:7,p:2]
 * |-
 * 
 *              0
 *           2/   \3
 *           1     3
 *          4|    1| 
 *           5     2
 *                7|
 *                 4
 * -> Summe der Kantengewichte: 17
 */
	public static void main(String[] args) {
		
		MyGraph graph = new MyGraph(false);
		MyNode a = graph.addNode();
		a.setLabel("0");
		MyNode b = graph.addNode();
		b.setLabel("1");
		MyNode c = graph.addNode();
		c.setLabel("2");
		MyNode d = graph.addNode();
		d.setLabel("3");
		MyNode e = graph.addNode();
		e.setLabel("4");
		MyNode f = graph.addNode();
		f.setLabel("5");
		
		MyEdge ab = graph.addEdge(a, b);
		ab.setWeight(2);
		MyEdge ac = graph.addEdge(a, c);
		ac.setWeight(5);
		MyEdge ad = graph.addEdge(a, d);
		ad.setWeight(3);
		MyEdge cd = graph.addEdge(c, d);
		cd.setWeight(1);
		MyEdge ce = graph.addEdge(c, e);
		ce.setWeight(7);
		MyEdge cf = graph.addEdge(c, f);
		cf.setWeight(8);
		MyEdge df = graph.addEdge(d, f);
		df.setWeight(6);
		MyEdge ef = graph.addEdge(e, f);
		ef.setWeight(9);
		MyEdge bf = graph.addEdge(b, f);
		bf.setWeight(4);
		
		DotViewer v = DotViewer.displayWindow(graph, "Graph");
		Traversal algor = new PrimMinimumSpanningTree(graph);
		TraversalExample t = new TraversalExample();
		t.traversal = algor;
		algor.singlestepper = t;
		algor.start(a);
	}
}
