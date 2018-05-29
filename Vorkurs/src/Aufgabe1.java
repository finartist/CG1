
public class Aufgabe1 {

	public static void main(String[] args) {
		//1.1
		int a = 3;
		int b = 4;
		int c = a + b;
		int d = a * b;
		System.out.println(c);
		System.out.println(d);
		
		//1.2
		a = c;
		a = b;
		b = c;
		
		//1.3
		int x = 0;
		for(int i = 0; i<=20; i++) {
			if (i % 2 == 0) {
				x += i;
			}
			
		}
		System.out.println(x);
		
		//4.1
		double f = 100;
		double cel = 0;
		cel = 5.0/9.0*(f-32);
		System.out.println(cel);
		
		//4.2
		double r = 3;
		double pi = Math.PI;
		double c_area;
		c_area =  Math.pow(r, 2) * pi;
		System.out.println(c_area);
		
		double c_umfang;
		c_umfang = 2*pi*r;
		System.out.println(c_umfang);
		
	}

}
