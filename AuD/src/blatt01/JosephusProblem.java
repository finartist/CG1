package blatt01;

import aud.Queue;
/* a) Das Array als Queue speichern und dort durchiterieren und Personen in andere Queue stellen,
 *  bis keine Person mehr in der Arrayqueue ist.
 *
 * Alternative: Durch das Array iterieren, ausgezählte Personen in die Queue stellen und im Array null setzen.
 * Kommt man zu einer Person, die schon gewählt wurde, die nächste Person nehmen.
 */

public class JosephusProblem {

	public static <T> Queue<T> josephus(T[] children, int numbSyl) {
		Queue<T> end = new Queue<T>(children.length);
		int pos = 0;
		
		while(!empty(children)){
			pos = nextPos(children, numbSyl, pos);
			end.enqueue(children[pos]);
			children[pos] = null;
		}
		return end;
	}
	
	public static <T> boolean empty(T[] array){
		for(int i = 0; i < array.length; i++){
			if(array[i] != null){
				System.out.println("empty " + i + " " + array[i]);
				return false;
			}
		}
		return true;
	}
	
	public static <T> int nextPos(T[] array, int step, int pos){
		pos += step-1;
		pos %= array.length-1;
		System.out.println(pos);
		while(array[pos]==null && !empty(array)){
			pos++;
			pos %= array.length;
			System.out.println("hallo " + pos + " " + array[pos]);
			System.out.println(empty(array));
		}
		return pos;
	}


//	public static <T> Queue<T> josephus(T[] children, int numbSyl) {
//		Queue<T> original = toQueue(children);
//		Queue<T> end = new Queue<T>(children.length);
//
//		while (!original.is_empty()) {
//			for (int i = 0; i < numbSyl - 1; i++) {
//				original.enqueue(original.dequeue());
//			}
//			end.enqueue(original.dequeue());
//		}
//		return end;
//	}
//
//	public static <T> Queue<T> toQueue(T[] array) {
//		Queue<T> arrayQueue = new Queue<T>(array.length + 1);
//		for (int i = 0; i < array.length; i++) {
//			// arrayQueue.dequeue();
//			arrayQueue.enqueue(array[i]);
//		}
//		return arrayQueue;
//	}

	public static void main(String[] args) {
		Integer[] josephus = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		System.out.println(josephus(josephus, 7).toString());
	}
}
