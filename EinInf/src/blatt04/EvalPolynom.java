package blatt04;

import java.util.Arrays;

public class EvalPolynom {

	public static void main(String[] args) {
		double [] a = {1, 2, 3, 4, 5, 6};
		System.out.println(evalSimple(a, 3));
		System.out.println(evalHorner(a, 3));
		System.out.println(evalHornerRek(a, 3));

	}
	
	public static double evalSimple(double[] a, double x){
		// y ist der Funktionswert
		 double y = 0;
		 int iterations = 0;
		 double xPow = 1;
		 // es werden a.length Schleifendurchläufe benötigt (n Durchläufe
         for(int i = 0; i<a.length; i++){
        	 // xPow ist die Potenz von x, die benötigt wird
        	if(i!=0){
        		xPow *= x;
        	}
        	// y wird der bisher berechnete Funktionswert zugeordnet und dann
        	// ai*x^i addiert
        	y = y + a[i]*xPow;
        	iterations++;
         }
         System.out.println("Iterations normal: " + iterations);
         return y;
    
   }
	
   public static double evalHorner(double[] a, double x){
	   // y wird als Startwert der innerste Teil des Hornerschemas zugeordnet
        double y = a[a.length-1]*x;
        int iterations = 0;
        // for-Schleife beginnt bei dem vorletzten Index, da y bereits den letzten Index
        // als an 
        for(int i = a.length-2; i>= 0; i--){
        	if(i != 0){
        	y = (y + a[i])*x;
        	iterations++;
        	}
        	else{
        		y = y + a[i];
        	}
        }
        // Es werden n-2 Schleifendurchläufe benötigt
        System.out.println("Iterations Horner: " + iterations);
        return y;
    
   }
   
   public static double evalHornerRek(double[] a, double x){
	   // Abbruchbedingung für die Rekursion
	   // a[0] ist am Ende an, also das Äußerste im Horner-Schema
	   if(a.length == 1){
		   return a[0];
	   }
	   else{
		   // Das Array wird immer weiter verkürzt, sodass der erste Index 
		   // bei jedem neuen Aufruf weggekürzt wird und das nächstgrößere a
		   // vorn steht
		   double[] shortenArray = Arrays.copyOfRange(a, 1, a.length);
		   return evalHornerRek(shortenArray, x)*x + a[0];
	   }
   }

}
