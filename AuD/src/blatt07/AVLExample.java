package blatt07;

/*****************
answer a) Erklären Sie den Begriff AVL-Baum. Weshalb ist es erforderlich, binäre Bäume auszugleichen?
 Ein AVL Baum ist ein ausgeglichener Baum, bei dem für jeden Teilbaum gilt, die Höhendifferenz für
 den linken und rechten Teil ist höchstens 1.
 Ein Binärbaum sollte ausgeglichen sein, damit Operationen in möglichst geringer Komplexität 
 ausgeführt werden können (O(logn)).


answer b)
Erläutern Sie den Algorithmus für das Einfügen von Knoten in einen AVL-Baum am Beispiel des Einfügens 
der natürlichen Zahlen 14, 15, 17, 7, 5, 10 und 16 in dieser Reihenfolge. 
Skizzieren Sie den AVL-Baum nach jedem Einfügen eines Elementes

       14 // 14 ist erster Knoten und wird nur gesetzt
       
       14  // 15 wird als Blatt angehängt -> AVL Eigenschaft bleibt bestehen -> OK
          \
           15
           
       15*   // 17 wird eingefügt -> AVL Eigenschaft verletzt -> nach links rotieren* -> 15 neue Wurzel
     /    \
    14*    17
    
       15     // 7 wird eingefügt -> AVL Eigenschaft bleibt bestehen -> OK
     /    \
    14     17
   /
  7
  
       15   // 5 wird eingefügt -> AVL EIgenschaft verletzt -> rotieren nach rechts*
     /    \
    7*    17
   / \
  5   14*
  
       15               15              14          // 10 eingefügt -> AVL Eigenschaft verletzt
     /    \           /    \          /    \          -> rotieren
    7     17         7     17        7     15
   / \         ->   / \         ->  / \      \
  5   14           5   10          5   10     17
     /                   \
    10                   14
    
      14             // 16 eingefügt -> AVL EIgenschaft verletzt -> nach rechts rotieren, nach links rotieren
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
