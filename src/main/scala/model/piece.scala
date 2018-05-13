package model

case class Piece(var name: String, var loc: Square) {
  // name and pType are vars to allow promotion to change them
  val side: Char = name(0)
  var pType: Char = name(1)
  var logic: Logic = Empty // Initialized empty, determined later
  
  if (side == 'W') {
    Model.white.pieces +:= this
  }
  else if (side == 'B') {
    Model.black.pieces +:= this
  }
  
  Model.board.addPiece(this)
  
  this.getLogic
  
  /*------------------------- Methods ------------------------*/

  /* Gets the logic associated with the piece type
   * 
   */
  def getLogic: Unit = {
    this.logic = {
      if (pType == 'p') {
        if (side == 'W') WhitePawn
        else BlackPawn
      }
      else if (pType == 'n') Knight
      else if (pType == 'b') Bishop
      else if (pType == 'r') Rook
      else if (pType == 'q') Queen
      else King 
    }
  }
  
  /* Allows a piece to be promoted to another type. Only used for pawns
   * 
   */
  def promote(newType: Char): Unit = { // Untested/implemented
    this.pType = newType
    this.name = this.side.toString + newType.toString
  }
  
  /* Uses the piece logic to determine possible moves, then filters legal ones
   * 
   */
  def legalMoves: List[Square] = { // Returns the squares it can go to
    val possible: List[Move] = this.logic.getMoves
    //println("Possible: " + possible)
    var legal = List.empty[Square]
    for (move <- possible) {
      val dest: Square = this.loc + (move.dX, move.dY)
      if (Model.board.inBounds(dest)) { // Check bounds
        val atDest = Model.board.checkSquare(this.side, dest)
        if ((atDest == 0 && move.attack != 1) || // Empty & not attack
            (atDest == -1 && move.attack != -1)) // Enemy & can attack
          if (Model.board.checkBetween(this.loc, dest)) legal +:= dest
              // No pieces blocking
      }
    }
    //println("Legal: " + legal)
    legal
  }
  
  /* Tests if a given space is reachable by this piece
   * 
   */
  def canMoveTo(to: Square): Boolean = {
    for (sq <- this.legalMoves) {
      if (sq == to) return true
    }
    false
  }
}
