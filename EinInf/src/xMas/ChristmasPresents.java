package xMas;

import java.util.Arrays;
import java.util.Random;

/*
 * Das Papier kostet 10 EUR je Rolle, 
 * wobei 5 m2 auf jeder Rolle sind, Schleifenband kostet 3 EUR je Meter.
 * Berechnen Sie den Bedarf an Einwickelpapier (in Rollen) und 
 * Schleifenband (in m) sowie den dazugehörenden Preis.
 */

class ChristmasPresents {
	
	public static void main(String[] args) {
		
		Present[] presents = randomPresents(5);
		
		System.out.println(Arrays.toString(presents));
		int paperPrice = computePaperPrice(presents);
		int ribbonPrice = computeRibbonPrice(presents);
		int price =  paperPrice + ribbonPrice;
		System.out.println("We need to pay " + paperPrice + "€ for paper and " + ribbonPrice + "€ for ribbon. So we have " + price + " € in total.");
	}
	
	public static int computePaperPrice(Present[] presents){
		double sqrmt = 0;
		for(int i = 0; i < presents.length; i++){
			sqrmt += presents[i].computePaperSize();
		}
		System.out.println((int) sqrmt/5 + 1 + " rolls cost " + ((int) (sqrmt/5 + 1))*10 + "€");
		return ((int) (sqrmt/5 + 1))*10;
	}
	
	public static int computeRibbonPrice(Present[] presents){
		double length = 0;
		for(int i = 0; i < presents.length; i++){
			length += presents[i].computeRibbonLength();
		}
		System.out.println((int) length+1 + " meters cost " + ((int) length+1)*3 + "€");
		return ((int) length+1)*3;
	}
	
	public static Present[] randomPresents(int n){
		Random rnd = new Random();
		Present[] presents = new Present[n];
		for(int i = 0; i < n; i++){
			int randNum = rnd.nextInt(3);
			switch(randNum) {
			case 0: 
				double length = rnd.nextDouble() + 0.1;
				double width = rnd.nextDouble() + 0.1;
				double height = rnd.nextDouble() + 0.1;
				
				presents[i] = new Cuboid(length, width, height);
				System.out.println(presents[i].toString2()+"\n");
				break;
			case 1:
				double heigth = rnd.nextDouble() + 0.2;
				double diameter = rnd.nextDouble()/2 + 0.01;
				
				presents[i] = new Cylinder(heigth, diameter);
				System.out.println(presents[i].toString2()+"\n");
				break;
			case 2:
				double radius = rnd.nextDouble()/2 + 0.05;
				
				presents[i] = new Ball(radius);
				System.out.println(presents[i].toString2()+"\n");
				break;
			default:
				break;
			}
		}
		return presents;
	}
}

