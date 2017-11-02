package blatt08;

import aud.BTree;
import aud.util.DotViewer;
import aud.util.SingleStepper;

/* a)Wieviel Schlüssel können in einem B-Baum 
 * mit m = 2 und der Höhe 3 gespeichert werden? Geben Sie die miminale und maximale Anzahl an.
 * 
 * Jeder Knoten hat höchstens 2m+1 Kinder.
   Jeder Knoten hat mindestens m+1 Kinder – mit Ausnahme
   der Wurzel: sie hat mindestens 2 Kinder – oder ist ein Blatt.
   Alle Blätter liegen in der gleichen Ebene.
   Für alle Knoten außer der Wurzel gilt m <= Anzahl Keys <= 2m
 *   minimal: 17 Knoten (Wurzel 1Key, 2 Kinder mit je 2 Keys, deren 2*3 Kinder mit je 2 Keys = 1 + 2*2 + 2*3*2)
 *   maximal: 124 Knoten (Wurzel 4Keys, 5 Kinder der Wurzel 4Keys, deren 25 Kinder je 4 Keys = 4 + 5*4 + 5*5*4)
 */

public class BTreeEx<T extends Comparable<T>> extends BTree<T> {

	private int numOfKeys;
	
	public BTreeEx(int m) {
		super(m);
		numOfKeys = 0;
	}
	
	@Override
	public boolean insert(T key){
		numOfKeys++;
		return super.insert(key);
	}
	
	public int height(){
		/*
		 * min number of nodes
		 * Level 1 = 1
		 * level 2 = 2
		 * Level 3 = 2 * (1/2(order-1)+1)
		 * Level 4 = 2 * (1/2(order-1)+1)^2
		 * Level n = 2 * (1/2(order-1)+1)^n-2
		 * -> summation of levels: 1 + 2 + 2*(1/2(order-1)+1) + ....
		 * 
		 * for the whole tree: minN = 1 + 2*((1/2(order-1)+1)^(h-1)-1)/((1/2(order-1)+1)-1))
		 *                          = 1 + 2*((m+1)^(h-1) -1)/m
		 * 
		 * evey node has min of m = (1/2(order-1)) keys except the root can have only one key
		 * so mink = 2 * (m+1)^(h-1) -1
		 * 		 
		 */
		// min number of children in level deriving
		// from one node = m+1
		int o = (this.getOrder()-1)/2+1;
		// height
		int h = 0;
		// min number of nodes for that height
		int mink = 0;
		// compute new min number of height
		while(numOfKeys > mink){
			h+=1;
			mink = 1;
			// setting mink = (m+1)^(h-1)
			for(int i = 0; i < h-1; ++i){
				mink*=o;
			}
			// using formula
			mink=2*mink-1;
		}
		return h;
	}

	public static void main(String[] args) {
		/*
		 * Fügen Sie nacheinander in dieser Reihenfolge die Integer-Werte 7,19, 23, 4, 12, 17, 8, 11, 2 , 9 und 13 
		 * mit insert in einen anfangs leeren BTree mit m=2 ein.
		 */
//		SingleStepper s = new SingleStepper("BTree");
//		DotViewer v = DotViewer.displayWindow("", "Btree");
		
//		BTree<Integer> myTree = new BTree<Integer>(2);
//		int[] keys = {7, 19, 23, 4, 12, 17, 8, 11, 2, 9, 13};
//		
//		for(int i = 0; i < keys.length; ++i){
//			s.halt("inser " + keys[i]);
//			myTree.insert(keys[i]);
//			v.display(myTree);
//		}
//		
		BTreeEx<Integer> tree = new BTreeEx<Integer>(2);
		for(int i = 0; i < 17; ++i){
			//s.halt();
			tree.insert(i);
			//v.display(tree);
		}
		System.out.println(tree.height());
		
		BTreeEx<Double> doubleTree = new BTreeEx<Double>(2);
		double num = 0;
		for(int i = 0; i < 1000000; ++i){
			num = Math.random()*1000;
			doubleTree.insert(num);
		}
		System.out.println(doubleTree.height());
	}
}
