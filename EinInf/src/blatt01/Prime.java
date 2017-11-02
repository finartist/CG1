package blatt01;

public class Prime {
    public static void main(String[] args) {
    	System.out.println(nextPrime(45));
    	System.out.println(isPrime(468));
        System.out.println(isPrime(1));
        System.out.println(isPrime(13));
        System.out.println(isPrime(2));
        System.out.println(isPrime(169));

        System.out.println(nextPrime(78));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(2));

    }
    
    public static int nextPrime(int n){
        int i = n;
        if(n == 2 || n == 1){
           return n;
        }
        else if(n % 2 == 0){
           i++;
           while(!isPrime(i)){
               i+=2;
           }
           return i;
        }
        else{
           while(!isPrime(i)){
               i+=2;
           }
           return i;
        }
    }
    
    private static boolean isPrime(long n){
		if(n == 1){
			return false;
		}
		else if(n == 2){
			return true;
		}
		else if(n % 2 == 0){
			return false;
		}
		else if(n == 3){
			return true;
		}
		else{
			// die Schleife muss nur bis ca. zur WUrzel von n gehen, 
			// da größere Zahlen als die Wurzel niemals Teiler sein können, 
			// ohne einen kleineren Teiler, der schon erfasst worden wäre
			for(long i = 3; i*i <= n; i += 2){
				if(n % i == 0){
					return false;
				}
			}
			return true;
		}
	}
    
}
