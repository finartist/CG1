import java.util.Random;
import java.util.Scanner;

public class AufgabeArrays {

	public static void main(String[] args) {
		// 1. Erzeuge ein zufälliges Array. Gebe das Array selbst und die größte Zahl im Array aus. ZUSATZ: gebe die größte Zahl mit dem zugehörigen Index aus.
		// 2. Erzeuge ein zufälliges Array. Gebe das Array selbst und die Summe aller Zahlen, die durch 3 oder 4 teilbar sind. VARIANTE: gebe die Summe aller durch 3 teilbaren Zahlen minus die Summe aller durch 4 teilbaren Zahlen an.
		// 3.Schreibe ein Programm, welches eine texteingabe entgegennimmt und sie in umgedrehter reihenfolge wieder ausgibt.
		
		//1.
//		Random rng = new Random();
//		int[] randomArray = new int[rng.nextInt(11)+1];
//		
//		for(int i = 0; i < randomArray.length; i++){
//			randomArray[i] = rng.nextInt(101);
//		}
//		int größteZahl = 0;
//		for(int e : randomArray){
//			System.out.println(e + " ");
//			if (größteZahl < e){
//				größteZahl = e;
//		}
//		}
//		System.out.println(größteZahl);
		
		//2.
//		Random rng = new Random();
//		int[] randomArray = new int[rng.nextInt(11)+1];
//		
//		for(int i = 0; i < randomArray.length; i++){
//			randomArray[i] = rng.nextInt(101);
//		}
//		
//		int summe = 0;
//		
//		for(int i=0; i< randomArray.length; i++){
//			System.out.println(randomArray[i]);
//			if (randomArray[i] % 3 == 0 || randomArray[i] %4 == 0){
//				summe += randomArray[i];
//			}
//		}
//		System.out.println(summe);
		
		//2. Variante
//		Random rng = new Random();
//		int[] randomArray = new int[rng.nextInt(11)+1];
//		
//		for(int i = 0; i < randomArray.length; i++){
//			randomArray[i] = rng.nextInt(101);
//		}
//		
//		int diff = 0;
//		int summe3 = 0;
//		int summe4 = 0;
//		
//		for(int i=0; i< randomArray.length; i++){
//			System.out.println(randomArray[i]);
//			if (randomArray[i] % 3 == 0 ^ randomArray[i] % 4 == 0){
//				if (randomArray[i] % 3 == 0) {
//					summe3 += randomArray[i];
//				}
//				else if (randomArray[i] % 4 == 0){
//					summe4 += randomArray[i];
//				}
//			}
//			diff = summe3 - summe4;
//			
//		}
//		System.out.println(diff);
		
		//3.
		Scanner scn = new Scanner(System.in);
		String text = scn.nextLine();
		String reverse = "";
		
		for(int i = text.length()-1; i >=0; i--){
			reverse += text.charAt(i);
		}
		System.out.println(reverse);
		
		
		
		/*
		 * um Arrays zu kopieren sollte man array.clone() verwenden, da bei einem simplen array1 = array2 nur die Referenz auf das selbe Array gespeichert wird
		 * ändert man dann array1 oder 2, ändert sich auch der Wert im anderen Array
		 */
	}

}
