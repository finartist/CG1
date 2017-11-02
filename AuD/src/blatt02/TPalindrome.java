package blatt02;

import aud.Stack;

public class TPalindrome {

	public static boolean isTPalindrome(String text) {

		Stack<Character> all = new Stack<Character>();
		String stringBraces = "";
		String stringReturn = "";

		//looping through the whole string
		for (int i = 0; i < text.length(); i++) {
			// as long as the char is no ')', we put it on the Stack
			if (!(text.charAt(i) == ')')) {
				all.push(text.charAt(i));
			} 
			else {
				//otherwise we put every char into a string until we find the matching '('
				while (all.top() != '(') {
					stringBraces += all.pop();
				}
				// get rid of the '('
				all.pop();
				// if the part in the braces is a Palindrome, we replace it by a *
				if (isPalindrome(stringBraces)) {
					all.push('*');
				} 
				// otherwise we put it back on the Stack
				else {
					for (int j = stringBraces.length() - 1; j >= 0; j--) {
						all.push(stringBraces.charAt(j));
					}
				}
				// cleaning the string in the braces
				stringBraces = "";
			}
		}
		// putting the whole Stack in a String with the braces replaced by * if they are palindrome
		while (!all.is_empty()) {
			stringReturn += all.pop();
		}
		System.out.println(stringReturn);
		// let the function from the last exercise do the rest of our work
		return isPalindrome(stringReturn);
	}

	public static boolean isPalindrome(String text) {

		// newstr is a normalized String
		String newstr = norm(text);

		if (newstr.length() > 0) {
			// a stack of the char-Wrapperclass 'Character'
			Stack<Character> stack = new Stack<Character>();

			// push single chars on stack
			for (int i = 0; i <= newstr.length() - 1; ++i) {
				stack.push(newstr.charAt(i));
			}

			for (int i = 0; i <= newstr.length() - 1; ++i) {
				// compare each character from stack(reverse order)
				// with character from newstr
				if (stack.pop().equals(newstr.charAt(i))) {
					continue;
				} else {
					return false;
				}
			}
			// only if every char is the same, it's a palindrome
			return true;

		}
		// empty string (or only numbers/special chars/... is no palindrome
		else {
			return false;
		}
	}

	public static String norm(String original) {
		String newstr = original.toLowerCase();
		// [^a-z] is a Regex(regular expression) and means "all but
		//  a to z"
		// with replaceAll every character not matching a-z is replaced by ""
		newstr = newstr.replaceAll("[^a-z*]", "");
		return newstr;
	}

	public static void main(String[] args) {
		System.out.println(isTPalindrome("abc(ah(otto)v(atta)ha)cba"));
		System.out.println(isTPalindrome("abc(ah(otto)v(atta)ha)ca"));
		System.out.println(isTPalindrome("abc(ah(otto)h)cba"));
		System.out.println(isTPalindrome("aac((ah)ada(ha))(a)caa"));

	}
}
