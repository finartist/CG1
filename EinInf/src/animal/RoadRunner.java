package animal;

class RoadRunner extends Bird {
	public void move(String dest){
		System.out.println("run "+dest);
	}	
	public void move(int x){
		System.out.println("run faster to "+x);
	}
	
	public static void main(String[] args) {
	
	    Bird bird = new RoadRunner();
	    Animal animal = new Bird();
	    animal.move("home");
	    animal=bird;
	    //shark.move(0);
	    bird.move(0);
	    animal.move("away");
	    

	    Animal shark = new Fish();
	    //Animal gnu = new Animal();
	    RoadRunner coyote = new RoadRunner();
	    //Fish nemo = coyote;
	    
	    /*
	    a) Ausgabe:
	    5 --> fly home
	    7 --> teleport to 0
	    8 --> run faster to 0
	    9 --> run away

	    b)
	    Überladen: Signatur verändert, aber Fkt. gibt das Gleiche aus (gleich definiert)
	    Überschreiben: Signatur ist gleich, aber Fkt. gibt etwas anderes aus (unterschiedlich definiert)

	    Überschreiben findet im Zusammenhang mit Vererbung statt.
	    z.B überschreibt die Klasse Fish die move() Methode von Animal, sodass wenn .move(String) aufgerufen wird, "swim " + String ausgegeben wird. Das gleiche passiert bei der Klasse RoadRunner, die move() von Bird erbt. Wird ein RoadRunner mit .move(String) aufgerufen, wird "run " + String anstatt "fly " + String ausgegeben.

	    Die move Methode wird allerdings auch überladen, d.h. der Name ist gleich, aber Typ oder Argumentliste (Anzahl, Reihenfolge oder Typen) sind verschieden. In der Klasse Bird hat move zwar immer keine Rückgabe (ist also Typ void), aber die Argumentliste ist verschieden, denn move kann einmal mit einem String aufgerufen werden und einmal mit 2 Integerwerten.

	    c)
	    2 kann nicht übersetzt werden, da Animal eine abstrakte Klasse ist und deshalb keine Instanzen dieser Klasse initialisiert werden können.

	    4 kann nicht übersetzt werden, da Fish und RoadRunner nicht von der gleichen Klasse erben. RoadRunner ist nochmal eine Spezialisierung von Bird, wohingegen Fish direkt von Animal erbt.
	    */


	}
}