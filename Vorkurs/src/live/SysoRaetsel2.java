package live;
public class SysoRaetsel2 {

	public static void main(String[] args) {

		int a, b;
		a = 0;
		b = 4;
		String endlich = "Juhu, ";
		System.out.println(endlich + "wieder ein Syso-Test!");
		while (a <= b) {
			System.out.println(a);
			a = a + 1;
		}
		System.out.println("jetzt hat a den Wert: " + a);
		while (a * 8 != 56) {
			a = (a + 4) % 10;
			if (a == 3) {
				System.out.println("Sonnenschein");
			} else {
				System.out.println("Es regnet");
			}
		}
		System.out.println(fkt1(a, b));
		System.out.println(fkt1(a));
		System.out.println("der Wert von a: " + a);
		a = a + fkt2(b);
		System.out.println("leet-speak? " + a);
		for (int i = 0; i < b; i++) {
			if (isEven(i)) {
				System.out.println("ENTE");
			} else {
				System.out.println("-Ente-");
			}
		}		System.out.println("Gans!");
		System.out.println(fkt1(fkt2(b), fkt2(fkt1(a, a))));
		while (b > 0) {
			int instabileZahl = 5;
			System.out.println(b + instabileZahl);
			b = b - 1;
		}
		//System.out.println(instabileZahl);
	}

	static int fkt1(int x, int y) {
		int z = x + y;
		return z;
	}

	static String fkt1(int x) {
		String erg = "";
		while (x > 0) {
			erg = erg + "a";
			x = x - 1;
		}
		return erg;
	}

	static int fkt2(int x) {
		if (isEven(x)) {
			return 1330;
		} else {
			return 0;
		}
	}

	static boolean isEven(int a) {
		if (a % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
}
