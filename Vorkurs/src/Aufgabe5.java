import java.util.Scanner;

public class Aufgabe5 {

	public static void main(String[] args) {
		//5.1
		
		Scanner scn = new Scanner(System.in);
		String op = scn.nextLine();
		int par1 = scn.nextInt();
		int par2 = scn.nextInt();
		
		
		String plus = "+";
		String minus = "-";
		String mal = "*";
		String durch = "/";
		
		if (op.equals(plus)){
			System.out.println(par1 + par2);
		} else if(op.equals(minus)){
			System.out.println(par1 - par2);
		} else if(op.equals(mal)){
			System.out.println(par1 * par2);
		} else if(op.equals(durch)){
			if (par2 == 0){
				System.out.println("Achtung, Division durch 0 ist unzulässig!");
			}
			else {
				System.out.println(par1/par2);
			}
		}

	}

}
