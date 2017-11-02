package blatt07;

import aud.AVLTree;
import aud.RedBlackTree;
import aud.util.DotViewer;
import aud.util.SingleStepper;

public class RedBlackExample {
/**
 * a) Was ist ein Rot-Schwarz-Baum? Welche Eigenschaften hat er?
 *   zu d) Ein Rot-Schwarz Baum ist einem 234-Baum ähnlich: 
 *  ein 2 Knoten wäre ein schwarzer Knoten,
 *  ein 3 Knoten ein schwarzer Knoten mit einem roten Kind und 
 *  ein 4 Knoten ein schwarzer Knoten mit 2 roten Kindern.
 *   
 *  Ein roter Knoten darf nur schwarze Kinder haben, 
 *  ein schwarzer Knoten auch schwarze Kinder. 
 *  Die Wurzel und Blätter(dummi Knoten = null) sind immer schwarz. 
 *  Jeder Pfad von einem gegebenen Knoten zu einem erreichbaren Blatt enthält dieselbe Anzahl schwarzer Knoten.
 *  Neue Knoten werden als rote Knoten eingefügt.
 *   
 * b)Führen Sie von Hand schrittweise den Algorithmus zum Aufbau eines Rot-Schwarz-Baums mit nacheinander den Elementen 
 * der Liste [6,7,3,4,2,1] aus. Geben Sie alle Zwischenschritte an. Wann entstehen Rot-Rot-Verstösse? 
 * Welcher Baum entsteht?
 * 
 *                6(s)
 *                
 *                6(s)
 *                  \
 *                  7(r)
 * 
 *                6(s)   
 *               /   \             
 *              3(r)  7(r)
 *              
 *               6(s)                                   6(r)             6(s)
 *              /   \                                  /   \            /   \
 *            3(r)  7(r)   -> rot-rot Verstoss ->    3(s)  7(s)  ->   3(s)  7(s)
 *              \                                      \                \
 *              4(r)                                   4(r)             4(r)
 * 
 *               6(s)
 *              /   \
 *            3(s)  7(s)
 *           /  \
 *         2(r) 4(r)
 *         
 *               6(s)                                    6(s)
 *              /   \                                   /   \
 *            3(s)  7(s)                              3(r)   7(s)
 *           /  \          -> rot rot Verstoss ->    /  \
 *         2(r) 4(r)                               2(s) 4(s)
 *        /                                        /
 *       1(r)                                     1(r)
 *         
 *         
 * c) Führen Sie zum Vergleich den schrittweisen Aufbau eines AVL-Baums mit nacheinander 
 * den Elementen der Liste [6,7,3,4,2,1] aus. Vergleichen Sie mit a).
 *                6
 *                
 *                6
 *                 \
 *                  7
 * 
 *                6   
 *               /  \             
 *              3    7
 *              
 *               6   
 *              /  \     
 *             3    7
 *              \             
 *              4
 *              
 *               6   
 *              /  \     
 *             3    7
 *            / \             
 *           2   4
 *           
 *               6                3
 *              /  \             /  \
 *             3    7           2    6    
 *            / \        ->    /    / \
 *           2   4            1    4   7
 *          /
 *         1
 *         
 *  d) siehe oben
 */       
	
	public static void main(String[] args) {
		DotViewer v = DotViewer.displayWindow("", "RB Tree");
		   SingleStepper stepper = new SingleStepper("RB Tree");
		   
		   int[] array = {6,7,3,4,2,1};
		   
		   RedBlackTree<Integer, String> rbtree = new RedBlackTree<Integer, String>();
		   AVLTree<Integer, String> avl = new AVLTree<Integer, String>();
		   
		   for(int i = 0; i < array.length; ++i){
			   stepper.halt("insert " + array[i]);
			   rbtree.insert(array[i], null);
			   v.display(rbtree);
		   }
		   
		   for(int i = 0; i < array.length; ++i){
			   stepper.halt("insert " + array[i]);
			   avl.insert(array[i], null);
			   v.display(avl);
		   }
	}
}
