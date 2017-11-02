package blatt10;

import java.util.*;
public class AdjMatrix{

	public  static int inDegree(int k, int[][] m) throws Exception{
		if(k>= m.length){
			throw new Exception("Invalid Node entered");
		}
		int deg = 0;
		for(int i = 0; i < m.length; ++i){
			if(m[i][k] != 0){
				deg++;
			}
		}
		return deg;
	}

	public  static int outDegree(int k, int[][] m) throws Exception{  
		if(k>= m.length){
			throw new Exception("Invalid Node entered");
		}
		int deg = 0;
		for(int i = 0; i < m.length; ++i){
			if(m[k][i] != 0){
				deg++;
			}
		}
		return deg;
	}

	public static List<Integer> adjacent(int k, int[][] m) throws Exception{  
		List<Integer> ads = new ArrayList<Integer>();
		if(k>= m.length){
			throw new Exception("Invalid Node entered");
		}
		for(int i = 0; i < m.length; ++i){
			if(m[k][i] != 0){
				ads.add(i);
			}
			if(m[i][k] != 0 && !ads.contains(m[i][k])){
				ads.add(i);
			}
		}
		return ads;
	}
 
	/*      0 1 2 3 4
	 * 
	 *    | 0 1 0 0 1 |  0
          | 0 0 0 1 0 |  1   1->3->2->1 Ist Dreieck
      A = | 0 1 0 0 0 |  2
          | 0 0 1 0 0 |  3
          | 0 0 0 1 0 |  4
	 */
	
   public static boolean hasTriangle(int[][] m) {
	   //looping through matrix
	   for(int i = 0; i < m.length; ++i){
		   for(int j = 0; j < m.length; ++j){
			   // if a place is found, where an outgoing edge exists -> find an edge from the destination node
			   if(m[i][j] != 0){
				   for(int h = 0; h < m.length; h++){
					   //if destination node has outgoing edge -> check if destination node has outgoing edge to very first node
					   if(m[j][h] != 0 && h != j){
						   if(m[h][i] != 0){
							   System.out.println("triangle with nodes: " + i + "-> " + j + "-> " + h);
							   return true;
						   }
					   }
				   }
			   }
		   }
	   }
	   return false;
   }
   // other possibility by matrix multiplication, therefore delete 1-cycle first before multiplication
   // if a node reaches itself over 3 edges -> triangle (on position matrix[i][i] != 0)
   // through multiplication n-times we have on position ij how many possibilities we have, to go from i to j over n edges
   
   public static void main(String args[]) throws Exception {
	   int[][] test = {
			   {0, 1, 0, 0, 1},
			   {0, 1, 0, 1, 0},
			   {0, 1, 0, 0, 0},
			   {0, 0, 1, 0, 0},
			   {0, 0, 0, 1, 0}};
	   
	   int[][] test2 = {
			   {0, 0, 0, 0, 0, 1, 1},
			   {1, 0, 0, 0, 0, 1, 0},
			   {0, 1, 0, 0, 0, 0, 0},
			   {0, 1, 0, 0, 0, 0, 0},
			   {0, 0, 0, 1, 0, 0, 0},
			   {0, 0, 0, 1, 0, 0, 1},
			   {0, 0, 0, 0, 0, 0, 0}};
	   
	   System.out.println(hasTriangle(test));
	   System.out.println(hasTriangle(test2));
	   
	   //System.out.println(inDegree(6, test));
	   System.out.println(outDegree(3, test2));
	   System.out.println(inDegree(3, test2));
   }
}