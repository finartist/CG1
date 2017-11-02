package pruefung;

public class Aufg12 {
	static int c(int n) { return n<2 ? 1 : c(n-1)+c(n-2); }
	
	static int cDyn(int n){
		if(n < 2){
			return 1;
		}
		else{
			int[] c = new int[n+1];
			c[0] = 1;
			c[1] = 1;
			for(int i = 2; i<= n; ++i){
				c[i] = c[i-1] + c[i-2];
			}
			return c[n];
		}
	}
	
	public static void main(String[] args) {
		System.out.println(c(12));
		System.out.println(cDyn(12));
	}
}
