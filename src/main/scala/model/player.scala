package model

class Player(val side: Char) { //, var logic: Logic) {
  var pieces = List.empty[Piece]
  
  /*------------------------- Methods ------------------------*/
  
  def reset = pieces = List.empty[Piece]
  
  def piecesInPlay: List[Piece] = {
    this.pieces.filter(_.loc != Square(-1,-1))
  }
  
  def deadPieces: List[Piece] = {
    this.pieces.filter(_.loc == Square(-1,-1))
  }
  
}