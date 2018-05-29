import java.util.Arrays;
import java.util.Scanner;

public class PascalschesDreieck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pascal();
	}

	public static void Pascal(){
		
		Scanner scn = new Scanner(System.in);
		int heigth = scn.nextInt();
		int [] currentline;
		int [] nextline = new int[1];
		
		
		for(int i = 0; i<heigth; i++){
			currentline = nextline.clone();
			nextline = new int[i+1];
			if (i == 0){
				nextline[0] = 1;
			}
			else if( i == 1){
				nextline[0] = 1;
				nextline[i] = 1;
			}
			for(int j = 1; j<i; j++){
				nextline[0] = 1;
				nextline[i] = 1;
				nextline[0+j] = currentline[j-1] + currentline[j];
				}
			System.out.println(Arrays.toString(nextline));
		}
		
	}
	
}
//Arrays.toString();