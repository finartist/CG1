package blatt02;

public class LinkedNode<T> {
	T data_ = null;
	LinkedNode<T> next_ = null;

	// empty Node
	public LinkedNode() {
	};
	
	public LinkedNode(T data){
		this.data_ = data;
	}
	
	public LinkedNode(LinkedNode<T> next){
		this.next_ = next;
	}
	
	public LinkedNode(T data, LinkedNode<T> next){
		this.data_ = data;
		this.next_ = next;
	}
	// getter and setter
	public T getData_() {
		return data_;
	}

	public void setData_(T data_) {
		this.data_ = data_;
	}

	public LinkedNode<T> getNext_() {
		return next_;
	}

	public void setNext_(LinkedNode<T> next_) {
		this.next_ = next_;
	}

	String toText() {
		String stringOut  = "";
		if(this.next_ != null){
			// recursive methode to make LinkedNodes a String 
			stringOut += (this.data_ + " > " + this.next_.toText());
		}
		else{
			stringOut += this.data_;
		}
		return stringOut;
	}

	public static void main(String[] args) {
		LinkedNode<String> goToSleep = new LinkedNode<String>("Schlafen gehen");
		LinkedNode<String> game = new LinkedNode<String>("Spieleabend", goToSleep);
		LinkedNode<String> sleepLong = new LinkedNode<String>("Ausschlafen", game);
		
		System.out.println(sleepLong.toText());
		// Ausschlafen > Spieleabend > Schlafen gehen
		
		LinkedNode<String> exercise = new LinkedNode<String>("Übung besuchen", game);
		LinkedNode<String> mensa = new LinkedNode<String>("Mensa", exercise);
		LinkedNode<String> lecture = new LinkedNode<String>("Vorlesung besuchen", mensa);
		LinkedNode<String> snack = new LinkedNode<String>("Mitternachtssnack", goToSleep);
		
		sleepLong.setNext_(lecture);
		game.setNext_(snack);
		
		System.out.println(sleepLong.toText());
		//Ausschlafen > Vorlesung besuchen > Mensa > Übung besuchen > Spieleabend > Mitternachtssnack > Schlafen gehen

		
	}
}