import javax.swing.JOptionPane;

/**
 * @author Kai Friedrich
 */

public class Aufgabe_26 {
	public static void main(String[] args) {
		System.out.println("Aufgabe 26");
		System.out.println("L�sung mit InputDialog");
		System.out.println("Eine Alternative w�re der BufferedReader");
		String s = JOptionPane.showInputDialog("Gib einen String ein!");
		System.out.println("Deine Eingabe ist: \"" + s + "\"");
	}
}
