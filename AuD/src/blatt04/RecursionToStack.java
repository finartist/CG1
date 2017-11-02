package blatt04;

/* 
Antwort zu Teil a) Berechnet Quersumme
*/
import aud.Stack;

public class RecursionToStack {

	public static int whatRec(int n) {
		if (n < 10)
			return n;
		else
			return whatRec(n / 10) + n % 10;
	}

	public static int whatStack(int n) {
		if (n < 10) return n;
		else{
			Stack<Integer> stack = new Stack<Integer>();
			int number = n;
			int result = 0;
			while(number > 0){
				stack.push(number%10);
				number /= 10;
			}
			while(!stack.is_empty()){
				result += stack.pop();
			}
			return result;
		}
	}

	public static void main(String args[]) {
		System.out.println(whatRec(431332758));
		System.out.println(whatStack(431332758));
	}

}
