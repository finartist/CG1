Beispiele zu Sortierverfahren
===

Die abstrakte Klasse `Sort` definiert sowohl eine Schnittstelle für
eine Sortierfunktion als auch Methoden zum Test und zur Visualisierung
und auch eine `main()` Methode.

Die Klassen `SelectionSort`, `InsertionSort`, `BubbleSort`,
`Quicksort` und `Mergesort` implementieren die entsprechenden
Algorithmen.

Hier findet ihr den Code von den Vorlesungsfolien. Die einzelnen
Sortieralgorithmen könnt ihr einfach ausprobieren, indem ihr

```bash
java -ea Sort n NAME
```

startet. Dabei ist `n` die Anzahl Einträge und `NAME` der Name des
Sortieralgorithmus z.B. "SelectionSort". Die Option "-ea" steht für
"enable assertions" und aktiviert die Überpfrüng von
(Zwischen-)Ergebnissen mittels [assert()].


[assert()]: http://docs.oracle.com/javase/6/docs/technotes/guides/language/assert.html
