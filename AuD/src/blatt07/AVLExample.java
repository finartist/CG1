package blatt07;

/*****************
answer a) Erkl�ren Sie den Begriff AVL-Baum. Weshalb ist es erforderlich, bin�re B�ume auszugleichen?
 Ein AVL Baum ist ein ausgeglichener Baum, bei dem f�r jeden Teilbaum gilt, die H�hendifferenz f�r
 den linken und rechten Teil ist h�chstens 1.
 Ein Bin�rbaum sollte ausgeglichen sein, damit Operationen in m�glichst geringer Komplexit�t 
 ausgef�hrt werden k�nnen (O(logn)).


answer b)
Erl�utern Sie den Algorithmus f�r das Einf�gen von Knoten in einen AVL-Baum am Beispiel des Einf�gens 
der nat�rlichen Zahlen 14, 15, 17, 7, 5, 10 und 16 in dieser Reihenfolge. 
Skizzieren Sie den AVL-Baum nach jedem Einf�gen eines Elementes

       14 // 14 ist erster Knoten und wird nur gesetzt
       
       14  // 15 wird als Blatt angeh�ngt -> AVL Eigenschaft bleibt bestehen -> OK
          \
           15
           
       15*   // 17 wird eingef�gt -> AVL Eigenschaft verletzt -> nach links rotieren* -> 15 neue Wurzel
     /    \
    14*    17
    
       15     // 7 wird eingef�gt -> AVL Eigenschaft bleibt bestehen -> OK
     /    \
    14     17
   /
  7
  
       15   // 5 wird eingef�gt -> AVL EIgenschaft verletzt -> rotieren nach rechts*
     /    \
    7*    17
   / \
  5   14*
  
       15               15              14          // 10 eingef�gt -> AVL Eigenschaft verletzt
     /    \           /    \          /    \          -> rotieren
    7     17         7     17        7     15
   / \         ->   / \         ->  / \      \
  5   14           5   10          5   10     17
     /                   \
    10                   14
    
      14             // 16 eingef�gt -> AVL EIgenschaft verletzt -> nach rechts rotieren, nach links rotieren
    /    \
   7      16
  / \    /  \
 5  10  15  17
********************/

import aud.util.*;
import aud.AVLTree;
public class AVLExample {
   public static void main(String[] args) {
      // TODO: Test with DotViewer and SingleStepper
	   DotViewer v = DotViewer.displayWindow("", "AVL Tree");
	   SingleStepper stepper = new SingleStepper("AVL Tree");
	   
	   int[] array = {14, 15, 17, 7, 5, 10, 16};
	   
	   AVLTree<Integer, String> avl = new AVLTree<Integer, String>();
	   
	   for(int i = 0; i < array.length; ++i){
		   stepper.halt("insert " + array[i]);
		   avl.insert(array[i], null);
		   v.display(avl);
	   }
   }
}
