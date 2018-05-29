
public class Aufgabe_02 {

	public static void main(String[] args) {
		boolean sonne = false;
		boolean regen = true;
		System.out.println("Aufgabe 2");
		if (sonne) {
			// Sonne scheint
			if (regen) {
				// Sonne scheint und es regnet
				System.out.println("Achtung, das Wetter ist schwül! Guck mal, ein Regenbogen :-)");
			} else {
				// Sonne Scheint, es regnet nicht
				System.out.println("Creme dich mit Sonnencreme ein und bekomm keinen Hitzeschlag");
			}
		} else {
			// Sonne scheint nicht
			if (regen) {
				// Sonne scheint nicht, es regnet
				System.out.println("Nimm den Regenschirm mit");
			} else {
				// Sonne scheint nicht, es regnet nicht
				System.out.println("Angenehmes Wetter heute :-)");
			}
		}

		// Lösung 2:
		if (sonne && regen) {
			System.out.println("Achtung, das Wetter ist schwül! Guck mal, ein Regenbogen :-)");
		}
		if (!sonne && regen) {
			System.out.println("Nimm den Regenschirm mit");
		}
		if (sonne && !regen) {
			System.out.println("Creme dich mit Sonnencreme ein und bekomm keinen Hitzeschlag");
		}
		if (!sonne && !regen) {
			System.out.println("Angenehmes Wetter heute :-)");
		}

	}

}
