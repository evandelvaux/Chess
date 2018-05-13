package model

class Player(val side: Char) { //, var logic: Logic) {
  var pieces = List.empty[Piece]
  
  /*------------------------- Methods ------------------------*/
  
  def reset = pieces = List.empty[Piece]
  
}