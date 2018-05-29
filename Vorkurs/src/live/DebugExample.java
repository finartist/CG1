package live;

public class DebugExample {

	public static void main(String[] args) {
		double[] myArray = new double[10];
		
		for(int i = 0; i <= 10; ++i){
			myArray[i] = Math.random() * 20.0 + 3;
			System.out.println(myArray[i]);
		}
		
		int first = myArray[0];
		double second = myArray[1];
		
		System.out.println(second);
		double sqrtOfSecond = Math.sqrt(second);
		int sqrOfSecond = mySqr(sqrtOfSecond);
		System.out.println(sqrOfSecond);
		
		

	}
	
	public static int mySqr(double a){
		return (int) (a*a);
	}

}
