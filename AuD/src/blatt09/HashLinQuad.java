package blatt09;

public class HashLinQuad {
	// simple Hashtable with linear/quadratic probing
	// capacity is big enough
	private int[] table; // // array of buckets or slots
	private int size; // current number of elements
	private int capacity;

	// TODO: Constructor
	public HashLinQuad(int capacity)
	{
		table = new int[capacity];
		this.capacity = capacity;
		this.size = 0;
	}

	public int addLin(int obj) {
		if(size < capacity){
			int index = obj%capacity;
			int collisions = 0;
			while(table[index] != 0 && obj != 0){
				index++;
				index = index%capacity;
				collisions++;
			}
			table[index] = obj;
			size++;
			return collisions;
		}
		else return Integer.MIN_VALUE;
	}

	public int addQuad(int obj) {
		// quadratic probing
		// return number of collisions
		if(size < capacity){
			int index = obj % capacity;
			int collisions = 0;
			int factor = 1;
			while(table[index] != 0 && obj != 0){
				index = (obj + factor*factor)%capacity;
				collisions++;
				factor++;
			}
			table[index] = obj;
			size++;
			return collisions;
		}
		else return Integer.MIN_VALUE;
	}
	public String toString() {
		String string = "";
		for(int i = 0; i < table.length; ++i){
			string += table[i] + " ";
			if(i%10 == 0){
				string += "\n";
			}
		}
		return string;
	}

	public static void main(String[] args) {
		// TODO: test with hashtable-capacity of 1259 and 1000 random values
		int[] randomNumbers = new int[1000];
		HashLinQuad linear = new HashLinQuad(1259);
		HashLinQuad quad = new HashLinQuad(1259);
		int linColl = 0;
		int quadColl = 0;
		for(int i = 0; i < 1000; ++i){
			randomNumbers[i] = (int) (Math.random()*10000);
			linColl += linear.addLin(randomNumbers[i]);
			quadColl += quad.addQuad(randomNumbers[i]);
		}

		System.out.println("Linear Collisions: " + linColl);
		System.out.println("Quadratic Collisions: " + quadColl);
		System.out.println(randomNumbers.toString());
		System.out.println("Linear Hashtable:\n" + linear + "\n");
		System.out.println("Quadratic Hashtable\n" + quad + "\n");
	}
	// Das quadratische Sondieren hat sehr viel weniger Kollisionen
}