package blatt01;

import aud.Stack;

public class Palindrome {

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
				} 
				else {
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

	/**
	* Removes any blanks, numbers and special characters. 
	* Returns a String with just lower case characters.
	*
	* @param String containing any kind of characters.
	*
	* @return String with only lower case characters.
	*/
	public static String norm(String original) {
		String newstr = original.toLowerCase();
		//[^a-z] ist eine Regex(regular expression) und bedeutet "alle außer klein a bis z"
		// mit replaceAll werden alle Zeichen außer a-z entfernt und durch "" ersetzt
		newstr = newstr.replaceAll("[^a-z]", "");
		return newstr;
	}

	public static void main(String[] args) {
//		System.out.println(norm("ffkzftzzDGSE>NEBD87576djfhjsdr747397_+#"));
		
		System.out.println(isPalindrome("Na, Fakir, Paprika-Fan?"));
		System.out.println(isPalindrome("Marktkram"));
		System.out.println(isPalindrome("Gurken hol ohne Krug!"));
		System.out.println(isPalindrome("O, Streit irritiert so!"));
		System.out.println(isPalindrome("Reliefpfeiler"));
		System.out.println(isPalindrome("Madam"));
		System.out.println(isPalindrome("Hallo"));
		System.out.println(isPalindrome("Esel"));
		System.out.println(isPalindrome("Katze"));

	}

}
