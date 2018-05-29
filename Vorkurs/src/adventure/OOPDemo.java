package adventure;

public class OOPDemo {
	public static void main(String[] args) {
		Person p1 = new Person();
		System.out.println(p1);
		p1.name = "Lisa"; // Zuweisung von Werten zu Attributen per dot-Notation
		System.out.println(p1.name);
		
		Person p2 = new Person(32, "Ina");
		p2.gruss(p1);
	}
}
