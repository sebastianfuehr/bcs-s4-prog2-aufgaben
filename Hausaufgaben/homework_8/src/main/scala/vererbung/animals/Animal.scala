package vererbung.animals

/**
  * Abstrakte Klasse: Abstrakte Felder und Methoden einer
  * abstrakten Klasse sind <b>nicht</b> mit dem Schlüsselwort
  * abstract gekennzeichnet.
  * Abstrakte Felder und Methoden der abstrakten Klasse
  * werden in der erbenden Klasse <b>ohne</b> das
  * Schlüsselwort override initialisiert.
  * Unterschied zu Traits: Abstrakte Klassen haben Konstruktoren!
  */
abstract class Animal {

  def makeNoise(): String = {
    return "I make noise"
  }

}
