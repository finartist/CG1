package live;
public class SysoRaetsel {
	public static void main(String[] args) {
		int zahl1;
		int zahl2;
		zahl1 = 4;
		zahl2 = 7;
		int zahl3 = 10;

		System.out.println("zahl1");
		System.out.println(zahl1);
		System.out.println(zahl2 - zahl1);
		System.out.println(zahl3 / zahl1);
		System.out.println((zahl3 + (zahl2 + zahl1) * -1) * -1);
		System.out.println("Countdown ende");

		zahl1 = 13;
		zahl2 = zahl2 - 4;

		System.out.println(zahl2 + " ist die zweitkleinste Primzahl");
		System.out.println("zahl1 % zahl2 = " + zahl1 % zahl2);
		System.out.println("und zahl1 / zahl2 = " + zahl1 / zahl2);
		System.out.println("zahl3 * 8");
		System.out.println((zahl3 + 8) / 6 % 2);
		System.out.println(zahl1 = zahl2 + 3);
		System.out.println("zahl1 = " + zahl1 + ", zahl2 = " + zahl2 + " zahl3 = " + zahl3);

		System.out.println(zahl1 < zahl3);
		System.out.println(zahl1 == zahl3 - 4);
		System.out.println(zahl1 + zahl2 > zahl3);
		System.out.println("Kartoffel");
		System.out.println(zahl1 + zahl2 + 900 * zahl3 > 9000);
		System.out.println(zahl1 < zahl2 && zahl1 < zahl3);
		System.out.println(zahl1 < zahl2 || zahl1 < zahl3);
		System.out.println(zahl1 = zahl2);
		System.out.println("zahl1 = " + zahl1 + ", zahl2 = " + zahl2 + " zahl3 = " + zahl3);
	}
}
