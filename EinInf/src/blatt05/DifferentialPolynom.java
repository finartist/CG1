package blatt05;

public class DifferentialPolynom {

	public static void main(String[] args) {
		double[] fifthGPoly = {5, 3, 4, 2, 3, 2};
		
		double[] fourthGPoly = diffPoly(fifthGPoly);
		for(double e : fourthGPoly){
			System.out.println(e);
		}

	}

	public static double[] diffPoly(double[] coeffs) {
		
		double[] newCoeffs = new double[coeffs.length-1];
		
		for(int i = 0; i < newCoeffs.length; i++){
			newCoeffs[i] = coeffs[i+1]*(i+1);
		}
		return newCoeffs;

	}

}
