package blatt06;

public class RatNumber implements Comparable<RatNumber> {
	private int num = 0;
	private int denom = 1;

	public RatNumber() {
	}

	public RatNumber(int n, int d) {
		if(d == 0){
			throw new IllegalArgumentException("You cannot divide by 0.");
		}
		else{
			this.num = n;
			this.denom = d;
		}
		this.normalize();
	}

	public int getNum() {
		return this.num;
	}

	public int getDenom() {
		return this.denom;
	}

	public int compareTo(RatNumber n) {
		int comThis = this.num*n.denom;
		int comN = this.denom*n.num;
		if(comThis < comN){
			return -1;
		}
		else if(comThis > comN) {
			return 1;
		}
		else{
			return 0;
		}
	}

	public RatNumber add(RatNumber r) {
		return new RatNumber(this.num*r.denom + r.num * this.denom, this.denom * r.denom);
	}

	public RatNumber div(RatNumber r) {
		return new RatNumber(this.num*r.denom, r.num*this.denom);
	}

	public String toString() {
		return this.num + "/" + this.denom;
	}
	
	private void normalize(){
		if((this.num < 0 && this.denom < 0) || (this.num > 0 && this.denom <0)){
			this.num = -this.num;
			this.denom = -this.denom;
		}
		int gcd = findGCD(Math.abs(this.num), Math.abs(this.denom));
		this.num /= gcd;
		this.denom /= gcd;
	}
	
	private int findGCD(int number1, int number2) {
        if(number2 == 0){
            return number1;
        }
        return findGCD(number2, number1%number2);
    }

	public static void main(String[] args) {
		RatNumber r1 = new RatNumber(2, 4);
		RatNumber r2 = new RatNumber(3, -5);
		RatNumber r3 = new RatNumber(-8, 0);
		RatNumber r4 = r1.add(r2);
		RatNumber r5 = r1.div(r2);
		
		System.out.println("2/4 = " + r1.toString());
		System.out.println(r2.toString());
		System.out.println(r3.toString());
		System.out.println(r1.toString() + " + " + r2.toString() + " = " + r4.toString());
		System.out.println(r1.toString() + " : " + r2.toString() + " = " + r5.toString());
		System.out.println(r1.compareTo(r2));
		System.out.println(r3.compareTo(r1));
	}

}
