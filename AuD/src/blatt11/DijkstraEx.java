package blatt11;

public class DijkstraEx {
/*
 * a) Algorithmus von Dijkstra = Priority First Search
 * 
 *    -> finden des kürzesten Weges für positive Kantengewichte
 *    -> wie Breitensuche, aber mit Priority Queue (z.B Min-Heap)
 *    Ein schon erreichter aber noch nicht abgearbeiteter Knoten (in Front/PQ) könnte nochmals auf einem 
 *    kürzeren Weg erreicht werden! D.h. es muss möglich sein, Wegstrecken von beliebigen
 *    Knoten in PQ zu aktualisieren! Erweiterung des ADT PriorityQueue Realisierung einfach
 *    z.B. durch upheap (erniedrigen)/downheap (erhöhen)
 *    
 *    zuerst: alle Knoten haben Entfernung unendlich zu Startknoten s0 und als Parent -1
 *    Queue:
 *    |0[d:0,p:-1] -> Startknoten in priority queue packen. Rausnehmen, markieren und Adjazente in Queue mit jeweiliger Entferning zu s0
 *    |1[d:2,p:0], 3[d:3,p:0], 2[d:5,p:0] -> 1 raus, markieren, adjazente dazu (0 schon markiert)
 *    |3[d:3,p:0], 2[d:5,p:0], 5[d:6,p:1] -> 3 raus, markieren, 2 updaten, 5 bleibt
 *    |2[d:4,p:3], 5[d:6,p:1] -> 2 raus, markieren, adjazente dazu, 5 bleibt
 *    |5[d:6,p:1], 4[d:11,p:2] -> 5 raus, markieren, 4 bleibt
 *    |4[d:11,p:2] -> alles bleibt
 *    |-
 *    damit:0[d:0,p:-1], 1[d:2,p:0], 2[d:4,p:3], 3[d:3,p:0], 4[d:11,p:2], 5[d:6,p:1]
 *    
 * b)         0
 *       1/  2|   \3
 *       1    2    3
 *       6\  4|   /2
 *            4
 *            
 *            
 *        0
 *     6/   \2
 *      1    2
 *    -5\    /1
 *        3
 *        |1
 *        4
 */
}
