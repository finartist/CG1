package blatt06;

import java.util.Arrays;

public class Polynomial {
	private int degree; // highest power of x
	private double[] coeffs; // Array with coefficients, all coefficients
								// [0..degree] are necessary

	public Polynomial(double[] c) {
		this.degree = c.length - 1;
		this.coeffs = c;
	}

	public int getDegree() {
		return degree;
	}

	public double[] getAllCoefficients() {
		return this.coeffs.clone();
	}

	public double getCoefficient(int i) {
		return coeffs[i];
	}

	public String toString() {
		String polynom = "";
		for (int i = 0; i < this.degree; i++) {
			polynom = polynom + this.coeffs[i] + "x^" + i + " + ";
		}
		polynom = polynom + coeffs[this.degree] + "x^" + this.degree;
		return polynom;
	}

	public double evaluate(double x) {
		double y = this.coeffs[this.coeffs.length - 1] * x;
		for (int i = this.coeffs.length - 2; i >= 0; i--) {
			if (i != 0) {
				y = (y + this.coeffs[i]) * x;
			} else {
				y = y + this.coeffs[i];
			}
		}
		return y;
	}

	public Polynomial differentiate() {
		double[] newCoeffs = new double[this.coeffs.length - 1];

		for (int i = 0; i < newCoeffs.length; i++) {
			newCoeffs[i] = this.coeffs[i + 1] * (i + 1);
		}
		return new Polynomial(newCoeffs);
	}

	public static void main(String[] args) {
		double[] fifthGPoly = { 5, 3, 4, 2, 3, 2 };
		Polynomial five = new Polynomial(fifthGPoly);
		System.out.println(five.toString());
		Polynomial four = five.differentiate();
		System.out.println(four.toString());

		System.out.println(five.evaluate(3.0));
		System.out.println(five.evaluate(-3.0));
	}
}
