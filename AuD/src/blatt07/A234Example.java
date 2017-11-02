package blatt07;

/*****************
answer a)Was ist ein 2-3-4 Baum?
Ein Baum, in dem jeder Knoten 2, 3, oder 4 Verzweigungen hat und dementsprechend 1, 2 oder 3 Keys 
in sortierter Reihenfolge enthält

answer b)
Zeigen Sie anhand folgenden Beispiels, dass die Reihenfolge des Einfügens von Werten 
für die Struktur des 2-3-4 Baumes entscheidend ist:

    Fügen Sie in folgender Reihenfolge die Zahlen 2, 6, 4, 14, 16, 8, 12, 20, 10 und 18 
    in einen 2-3-4 Baum sowohl mit der bottom-up als auch mit der top-down-Methode ein.
    
    bottom up                                       top down
          2                                             2
    
         2|6                                           2|6
         
        2|4|6                                         2|4|6
        
          4                                             4
        /    \                                        /   \
       2     6|16                                    2    6|16
       
          4                                             4
       /     \                                       /     \
      2     6|14|16                                 2      6|14|16
      
          4|14                                         4|14
       /   |   \                                    /   |   \
      2   6|8  16                                  2   6|8   16
      
          4|14                                           4|14
       /   |    \                                    /    |    \
      2  6|8|12  16                                 2   6|8|12  16
      
          4|14                                           4|14
       /   |    \                                    /    |    \
      2  6|8|12 16|20                               2   6|8|12 16|20
      
         4|8|14                                          4|8|14
      /   /  \   \                                    /   /  \   \
     2   6  10|12 16|20                              2   6  10|12 16|20
     
         4|8|14                                            8
      /   /  \   \                                      /     \
     2   6  10|12 16|18|20                             4        14
                                                      / \      /  \
                                                     2   6  10|12 16|18|20
                                                     
    Bauen Sie nun in der veränderten Reihenfolge 2, 4, 6, 8, 10, 12, 14, 16, 18 und 20 
    einen weiteren 2-3-4 Baum (wieder mit beiden Methoden) auf.

    bottom up                                       top down
          2                                             2
    
         2|4                                           2|4
         
        2|4|6                                         2|4|6
        
          4                                             4
        /    \                                        /   \
       2     6|8|10                                  2     6|8|10

         4|8                                           4|8
       /  |  \                                       /  |  \
      2   6   10|12                                 2   6  10|12
      
         4|8                                           4|8
       /  |  \                                       /  |  \
      2   6   10|12|14                              2   6  10|12|14
      
          4|8|12                                       4|8|12
       /  |  |   \                                   /  |  |  \
      2   6  10  14|16                              2   6  10  14|16
      
          4|8|12                                          8
       /  |  |   \                                     /     \
      2   6  10  14|16|18                             4      12
                                                     / \     / \
                                                    2   6   10  14|16|18
                                                    
           8                                              8
        /     \                                        /     \
       4     12|16                                    4      12|16
      / \    / |  \                                  / \     / |  \
     2   6  10 14 18|20                             2   6   10 14 18|20
*********************/
import aud.util.*;
import aud.A234Tree;
public class A234Example {
   public static void main(String[] args) {
      // TODO: Test with DotViewer and SingleStepper
	   DotViewer v = DotViewer.displayWindow("", "234Tree");
	   SingleStepper stepper = new SingleStepper("234Tree");
	   
	   int[] array1 = {2, 6, 4, 14, 16, 8, 12, 20, 10, 18};
	   int[] array2 = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
	   
	   A234Tree<Integer> tree1 = new A234Tree<Integer>();
	   A234Tree<Integer> tree2 = new A234Tree<Integer>();
	   
	   for(int i = 0; i < array1.length; ++i){
		   stepper.halt("insert " + array1[i]);
		   tree1.insert(array1[i]);
		   v.display(tree1);
	   }
	   
	   stepper.halt("OTHER ORDER");
	   for(int i = 0; i < array2.length; ++i){
		   stepper.halt("insert " + array2[i]);
		   tree2.insert(array2[i]);
		   v.display(tree2);
	   }
   }
}
