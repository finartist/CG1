
public class Aufgabe_15 {

	public static void main(String[] args) {
		System.out.println("Aufgabe 15");
		
		int maxStarsPerLine = 50;
		int numberOfLines = 101;
		
		
		int starsPerLine =1;
		for(int lineCounter=0; lineCounter<numberOfLines;lineCounter++){
			for(int starcounter=1;starcounter<=starsPerLine; starcounter++){
				System.out.print("*");	
			}
			System.out.println();
			if (starsPerLine == maxStarsPerLine){
				starsPerLine=1;
			}else{
				starsPerLine++;
			}
		}
		
	}
}
