package blatt05;

import java.util.Random;

public class Clock implements Comparable<Clock> {
	// ATTRIBUTE
	private int h;

	private int min;

	// GETTER
	public int getMin() {
		return min;
	}

	public int getH() {
		return h;
	}

	// KONSTRUKTOREN
	// default
	public Clock() {
		this.h = 0;
		this.min = 0;
	}

	// Stunden und Minutenangabe
	public Clock(int h, int min) {
		// Clock wird nur erstellt, wenn die Eingabe im richtigen Bereich
		if (h < 24 && h >= 0 && min < 60 && min >= 0) {
			this.h = h;
			this.min = min;
			// sonst geht es in den default-Fall und erstellt eine 0:0-Clock
		} else {
			System.out.println("Your entry is invalid. Clock set to default 0:0");
			this.h = 0;
			this.min = 0;
		}
	}

	// Minutenangabe
	public Clock(int min) {
		if (min < 60 && min >= 0) {
			this.h = 0;
			this.min = min;
		} else {
			System.out.println("Your entry is invalid. Clock set to default 0:0");
			this.h = 0;
			this.min = 0;
		}
	}

	// Stringeingabe hours:min
	public Clock(String time) {
		// der String wird erst gesplittet und in int umgewandelt
		// Rest wie zuvor
		String[] timeArr = time.split(":");
		int hour = Integer.parseInt(timeArr[0]);
		int min = Integer.parseInt(timeArr[1]);
		if (hour < 24 && hour >= 0 && min < 60 && min >= 0) {
			this.h = hour;
			this.min = min;
		} else {
			System.out.println("Your entry is invalid. Clock set to default 0:0");
			this.h = 0;
			this.min = 0;
		}

	}

	// METHODEN
	// Minuten addieren
	public Clock add(int min) {
		// nur positive Werte sind zulässig
		if (min >= 0) {
			// erst wird herausgefunden, wie viele Stunden und Minuten die
			// eingabe ergeben
			int addHours = min / 60;
			int addMin = min % 60;

			Clock addClock = new Clock(this.getH(), this.getMin());

			// die Stunden werden aufaddiert, sodass es bei 23 zum Sprung auf 0
			// kommt
			for (int i = 1; i <= addHours; i++) {
				if (addClock.h < 23 && addClock.h >= 0) {
					addClock.h++;
				} else {
					addClock.h = 0;
				}
			}
			// die Minuten werden wie die Stunden aufaddiert,
			// nur dass hier noch auf den Stundenüberlauf geachtet wird
			for (int i = 1; i <= addMin; i++) {
				if (addClock.min < 59 && addClock.min >= 0) {
					addClock.min++;
				} else {
					addClock.min = 0;
					if (addClock.h < 23 && addClock.h >= 0) {
						addClock.h++;
					} else {
						addClock.h = 0;
					}
				}
			}

			return addClock;
		}
		// für den Fall einer negativen Eingabe, wird die Methode nochmal mit 0
		// ausgeführt
		else {
			System.out.println("Please do only enter positive numbers.");
			return this.add(0);
		}
	}

	public Clock add(Clock c) {
		// Das Clockobjekt wird in Minuten umgerechnet und die add(min) Methode
		// wird aufgerufen
		return this.add(c.h * 60 + c.min);

	}

	// Methode zum erstellen zufälliger Clockobjekte
	public static Clock createRandom() {
		Random rng = new Random();
		int hour = rng.nextInt(24);
		int minutes = rng.nextInt(60);
		return new Clock(hour, minutes);
	}

	public String toString() {
		return (this.h + ":" + this.min);
	}
	
	public int compareTo(Clock c){
		if(c.h == this.h && c.min == this.min){
			return 0;
		}
		else if(c.h>this.h || (c.h==this.h && c.min > this.min)){
			return -1;
		}
		else{
			return 1;
		}
	}

	public static void main(String[] args) {
		Clock myFirstClock = new Clock(22, 23);
		Clock mySecondClock = new Clock(18);
		mySecondClock.h = 9;
		Clock myThirdClock = new Clock("2:7");
		Clock myRandomClock = Clock.createRandom();

		Clock addClocks = myFirstClock.add(mySecondClock);
		Clock addMinutes = myFirstClock.add(97);
		Clock addNegative = myFirstClock.add(-3);

		System.out.println(addClocks.toString());
		System.out.println(addMinutes.toString());
		System.out.println(addNegative);
		System.out.println(myRandomClock.toString());

		for (int x = 0; x < 30; x++) {
			Clock firstRand = Clock.createRandom();
			System.out.println("First Clock: " + firstRand);
			Clock secRand = Clock.createRandom();
			System.out.println("Second Clock: " + secRand);
			System.out.println("");

			System.out.println("Added Clocks: " + firstRand.add(secRand));
			System.out.println("First Clock + 30 minutes: " + firstRand.add(30));
			System.out.println("");

		}

	}
}
