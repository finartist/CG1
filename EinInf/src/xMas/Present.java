package xMas;

abstract class Present {
	abstract double surface();

	abstract double computeRibbonLength();
	
	abstract String toString2();

	double computePaperSize() { // same for all presents
		return surface() * 1.5; // 50% waste
	}
}