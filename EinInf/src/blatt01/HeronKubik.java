package blatt01;

public class HeronKubik {

	public static void main(String[] args) {
		double x1 = 1;
		double x0 = 1;
		
		//number i want to comute the cubic root of
		double v = 8; //(Bsp)

		while(x1*x1*x1 >= (v+0.001) || x1*x1*x1 <= (v-0.001)){
		  x0 = x1;
		  //Formula x0 - ((x0^3-v)/(3*x0^2)) == 1/3*(2*x0 + a/x0^2) == x1
		  //x1 is closest to cubic root of v
		  x1 = x0 - (x0*x0*x0-v)/(3*x0*x0);
		  //System.out.println(x1);
		}
		System.out.println("cubic root of: " + v + " is close to " + x1);
	}
}
