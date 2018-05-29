import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_25 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 25");

		Date datum = new Date();
		System.out.println(datum);

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println("Das aktuelle Datum ist: " + sdf.format(datum));

		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		System.out.println("Die aktuelle Uhrzeit: " + sdf2.format(datum));

		SimpleDateFormat sdf3 = new SimpleDateFormat("E");
		System.out.println("Heute ist: " + sdf3.format(datum));
	}
}
