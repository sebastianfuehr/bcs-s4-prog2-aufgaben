package vererbung.animals

class Duck(override val name: String) extends Bird(name) with Flying with Swimming {

  override val flyMessage: String = s"Duck $name has something for you."

  override def fly(): Unit = println(s"$flyMessage")

  override val swimMessage: String = s"Duck $name comes over the water."

  override def swim(): Unit = println(s"$swimMessage")

  /**
    * Gibt "I make nois as a bird while flying while swimming as a duck" auf der Konsole aus.
    * Die Reihenfolge der Ausgabe hängt von der Reihenfolge der Aufrufe der extendeten Klassen
    * ab. D.h., dass erst Animal initialisiert werden muss, damit dann Bird initialisiert werden
    * kann von welchem Duck extendet (extendet ist sozusagen die "wichtigste Vererbung").
    * Dann werden Flying und Swimming initialisiert, in der gleichen Reihenfolge wie sie über
    * das Schlüsselwort with eingebunden werden. Wenn dann alle Mutterklassen und Traits
    * verfügbar sind, wird erst die eigentlich Klasse Duck initalisiert.
    * @return
    */
  override def makeNoise(): String = super.makeNoise() + " as a duck"

}
