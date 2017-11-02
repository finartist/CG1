package klausurVorbereitung;

public class Hierarchie {
	public static String what(String str){
	if (str.length()==0) return "";
	return what(str.substring(1)) + str.charAt(0);
	}
	
public static void main(String[] args) {
A a1 = new B();
A a2 = new C();
B b1 = new B();
B b2 = new C();
C c1 = new C();
A a3 = c1;
a2 = a1;

System.out.println(what("Hallo"));

System.out.print(a1.msg1());
System.out.print(a1.msg2());
//System.out.print(a1.msg3());
System.out.print(a2.msg1());
System.out.print(a2.msg2());
//System.out.print(a2.msg3());
System.out.print(a3.msg1());
System.out.print(a3.msg2());
//System.out.print(a3.msg3());
System.out.print(b1.msg1());
System.out.print(b1.msg2());
System.out.print(b1.msg3());
System.out.print(b2.msg1());
System.out.print(b2.msg2());
System.out.print(b2.msg3());
System.out.print(c1.msg1());
System.out.print(c1.msg2());
System.out.print(c1.msg3());
}
}
