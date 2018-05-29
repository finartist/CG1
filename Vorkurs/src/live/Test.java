package live;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
//		// Das ist ein Kommentar
//		int a; 
//		a = 42; 
//		
//		/* Das
//		 * ist
//		 * ein 
//		 * mehrzeiliger
//		 * Kommentar
//		 */
//		int b = 1;
//		
//		double c = Math.pow(a, b);
//		c = 35.3;
//		
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
//		
//		boolean bedingung = true;
//		bedingung = a == b;
//		System.out.println(bedingung);
//		System.out.println(a != c);
//		
		String string = "Hello World!";
//		System.out.println(string);
//		System.out.println("Hello World");
//		
//		Scanner scn = new Scanner ( System .in );
////		String eingabe = scn.nextLine();
////		System.out.println(eingabe);
////		eingabe = scn.next();
////		System.out.println(eingabe);
//		
//		System.out.println(bedingung);
//		//bedingung = true;
//		if(bedingung){
//			int r;
//			r = (int) (a * c);
//			System.out.println(r + " ist a * c");
//		}
//		else{
//			for(int i = 0; i < 5; i= i+1 ){
//				System.out.println("i: " + i);
////				int j = 0;
////				while(j < 2){
////					System.out.println("j: " + j);
////					j = j+1;
////					i = i+1;
////				}
//				
//				for(int j = 0; j < 2; j=j+1){
//					System.out.println("j: " + j);
//				}
//			}
//		}
//		
		String string2 = new String("Hello World!");
		System.out.println(string == string2);
		System.out.println(string.equals(string2));
		System.out.println(string.compareTo(string2) == 0);
//		
//		final float UNVERAENDERBAR = 42;
//		//UNVERAENDERBAR = 0;
//		
//		int t = 8;
//		
//		switch(t){
//		case 1: t = t+1;
//			System.out.println(t);
//			break;
//		case 2: t = t+2;
//			System.out.println(t);
//			break;
//		case 8: t = t+8;
//			System.out.println(t);
//			break;
//		default:
//			System.out.println(t);
//			break;
//		}
//		
//		int i = 2;
//		while(true){
//			System.out.println(i);
//			if(i <= 0) break;
//			i = i*2;
//		}
//		
//		System.out.println(a);
//		System.out.println(a++);
//		System.out.println(++a);
//		
//		a+=1; // == a = a+1;
//		a-=1;
//		a*=2;
//		a/=2;
		
		//Aufgabe 4
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
	    int c = scanner.nextInt();
	    boolean d = scanner.nextBoolean();
	    String e = scanner.nextLine();
		int first;
		int second;
		int last;
		if (a <= b && b <= c){
			first = a;
			second = b;
			last = c;
		}
		else if(a <= c && c <= b){
			first = a;
			second = c;
			last = b;
		}
		else if(b <= a && a <= c){
			first = b;
			second = a;
			last = c;
		}
		else if(b <= c && c <= a){
			first = b;
			second = c;
			last = a;
		}
		else if(c <= a && a <= b){
			first = c;
			second = a;
			last = b;
		}
		else{
			first = c;
			second = b;
			last = a;
		}
		System.out.println(first + ", " + second + ", " + last);
		
		// Array leer initialisieren;
		int[] abc = new int[3];
		abc[0] = a;
		abc[1] = b;
		abc[2] = c;
		
		Arrays.sort(abc);
		System.out.println(Arrays.toString(abc));
		
		//Array mit geschweiften Klammern initialisieren
		int[] def = {5, 2, 1};
		System.out.println(Arrays.toString(def));
		
		//Arrays zuweisen
		def = abc;
		abc[1] = 600;
		System.out.println(Arrays.toString(def));
		
		//foreach
		for(int w : abc){
			System.out.println(w);
			w = 1;
		}
		System.out.println(Arrays.toString(abc));
	}
}
