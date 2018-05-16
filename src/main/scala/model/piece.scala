package model

case class Piece(var name: String, var loc: Square) {
  // name and pType are vars to allow promotion to change them
  val side: Char = name(0)
  var pType: Char = name(1)
  var logic: Logic = Empty // Initialized empty, determined later
  var hasMoved = false
  
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
  
  /* Returns any special moves available only to a particular piece in a particular situation
   * 
   */
  def getSpecialMoves: List[Move] = {
    var moves = List.empty[Move]
    
    //Pawns moving 2 on 1st move
    if (this.name == "Wp" && !this.hasMoved) moves +:= Move(0,2,-1)
    else if (this.name == "Bp" && !this.hasMoved) moves +:= Move(0,-2,-1)
    
    //Castling
    //Note: need to add tests for king being at risk; also don't forget to move the rook as well!
    else if (this.pType == 'k' && !this.hasMoved) {
      val lRook = Model.board.getPieceAt((this.loc + (-4,0)).toString)
      if (lRook != None && lRook.get.pType == 'r' && !lRook.get.hasMoved) {
        // Check for empty spaces between
        if (Model.board.getPieceAt((this.loc + (-3,0)).toString) == None && Model.board.getPieceAt((this.loc + (-2,0)).toString) == None &&
            Model.board.getPieceAt((this.loc + (-1,0)).toString) == None)
          moves +:= Move(-2,0)
      }
      val rRook = Model.board.getPieceAt((this.loc + (3,0)).toString)
      if (rRook != None && rRook.get.pType == 'r' && !rRook.get.hasMoved) {
        // Check for empty spaces between
        if (Model.board.getPieceAt((this.loc + (1,0)).toString) == None && Model.board.getPieceAt((this.loc + (2,0)).toString) == None)
          moves +:= Move(2,0)
      }
    }
    moves
  }
  
  /* Uses the piece logic to determine possible moves, then filters legal ones
   * 
   */
  def legalMoves: List[Square] = { // Returns the squares it can go to
    val possible: List[Move] = this.logic.getMoves ++ this.getSpecialMoves
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
  
  /* Removes piece from the current square and moves to the new one
   * 
   */
  def move(to: Square): Unit = {
    this.hasMoved = true
    
    // Remove from current square
    Model.board.removePiece(this)
    
    // Check if there is currently a piece at the destination, if so, remove it
    val at = Model.board.getPieceAt(to.toString)
    if (at != None) {
      Model.board.removePiece(at.get)
      at.get.loc = Square(-1,-1)
    }
    
    // Move to new square
    this.loc = to
    Model.board.addPiece(this)
  }
}
