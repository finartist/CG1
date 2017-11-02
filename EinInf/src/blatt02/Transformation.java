package blatt02;
import java.util.Random;

public class Transformation {

	public static void main(String[] args) {
		Random rng = new Random();
		for(int i = 0; i < 11; i++){
			int randNum = rng.nextInt(10000)+1;
			System.out.println("Die Zahl " + randNum +  " ist in binär " + transformDual(randNum));
		}
	}

	public static String transformDual(int dec) {
		String Dual = "";
		// Abbruchbedingung der Rekursion
		if (dec <= 0) {
			return "";
		} else {
			/* Zahl%2 ergibt die letzte Ziffer im Binärsystem
			 * rekursiv werden die anderen Stellen ermittelt,
			 * indem mit dem Ergebnis der Ganzzahldivision weiter gerechnet wird
			 *
			 * z.B. 2010
			 * 2010%2 => 0                              "11111011010" -> letztendlicher Ausgabewert
			 * 2010/2 => 1005
			 *                                               /\
			 * 1005%2 => 1                               "1111101101"
			 * 1005/2 => 502
			 *                                               /\
			 * 502%2 => 0                                "111110110"
			 * 502/2 => 251
			 *                                               /\
			 * 251%2 => 1                                 "11111011"
			 * 251/2 => 125
			 *                                               /\
			 * 125%2 => 1                                 "1111101"
			 * 125/2 => 62
			 *                                               /\
			 * 62%2 => 0                                  "111110"
			 * 62/2 => 31
			 *                                               /\
			 * 31%2 => 1                                   "11111"
			 * 31/2 => 15
			 *                                               /\
			 * 15%2 => 1                                   "1111"
			 * 15/2 => 7
			 *                                               /\
			 * 7%2 => 1                                     "111"
			 * 7/2 => 3
			 *                                               /\
			 * 3%2 => 1                                     "11"
			 * 3/2 => 1
			 *                                               /\
			 * 1%2 => 1                                      "1"
			 * 1/2 => 0
			 *                                               /\
			 * dec == 0 => leerer String wird zurückgegeben  ""
			 */
			Dual = transformDual(dec / 2) + dec % 2;
			return Dual;
		}
	}

}
