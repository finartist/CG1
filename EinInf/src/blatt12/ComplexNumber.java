package blatt12;

public class ComplexNumber {

	private class InvalidInputForSquareRoot extends RuntimeException {
		public InvalidInputForSquareRoot() {
			super("The imaginary part needs to be 0");
		}

		public InvalidInputForSquareRoot(String message) {
			super(message);
		}
	}

	private double real;
	private double imag;

	public ComplexNumber(double re, double im) {
		this.real = re;
		this.imag = im;
	}

	public ComplexNumber add(ComplexNumber k) {
		return new ComplexNumber(this.real + k.real, this.imag + k.imag);
	}

	public ComplexNumber mult(ComplexNumber k) {
		return new ComplexNumber((this.real * k.real - this.imag * k.imag), (this.real * k.imag + this.imag * k.real));
	}

	public double absoluteValue() {
		return Math.sqrt(this.real * this.real + this.imag * this.imag);
	}

	public ComplexNumber sqrt() throws InvalidInputForSquareRoot {
		if (this.imag != 0) {
			throw new InvalidInputForSquareRoot();
		} else {
			return new ComplexNumber(Math.sqrt((this.absoluteValue() + this.real) / 2),
					Math.sqrt((this.absoluteValue() - this.real) / 2));
		}
	}

	public String toString() {
		// bitte nicht veraendern (wegen Backend-Kontrolle)
		return real + " + " + imag + "*i";
	}

	public static void main(String[] args) {
		ComplexNumber a = new ComplexNumber(3, 7);
		ComplexNumber b = new ComplexNumber(4, 0);
		ComplexNumber c = new ComplexNumber(0, 13);
		ComplexNumber d = new ComplexNumber(-6, 0);

		System.out.println(a.toString());
		System.out.println(a.add(b).toString());
		System.out.println(b.mult(b).toString());
		System.out.println(b.sqrt().toString());
		System.out.println(c.absoluteValue());
		System.out.println(d.sqrt().toString());
		System.out.println(c.sqrt().toString());

	}

}
