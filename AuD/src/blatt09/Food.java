package blatt09;

public class Food {
    
	// using fancy enums just for fun
	public enum Type{
		DIVERSE,
		FRUIT,
		VEGETABLES,
		MILKPRODUCTS,
		EGGPRODUCTS,
		FLESH
	}
	
	Type type;
	String name;
	int calories;

	// TODO: attributes and constructor
	public Food(Type t, String n, int c){
		type = t;
		name = n;
		calories = c;
	}
	
	//bad hash function
   public int hashCode() {
        return type.ordinal() * name.length();

   }
   public String toString() {
       String s = "Type: " + type.toString() + "\nName: " + name + "\nCalories: " + calories + "\n";
       return s;

   }
}

//d) das auffinden im Kühlschrank sollte allein von der Temperatur abhängen bzw. dem Typen, weil das im "echten Leben" am meisten Sinn ergibt
// das ist allerdings für eine Hashfunktion eher ungeeignet