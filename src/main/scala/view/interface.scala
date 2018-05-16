package view

import model._

abstract class Interface(vType: Char) {
  /** Uses the appropriate interface to have the player select a piece
   *  
   */
  def selectPiece(whose: Char): Option[Piece]
  
  /** Uses the appropriate interface to have the player select the destination for a move
   *  
   */
  def selectDest(piece: Piece): Option[Square]
  
  /** Returns what will be displayed for testing purposes
   *  
   */
  def toString: String
  
  /** Displays the interface to the user
   *  
   */
  def show: Unit
}


/** Text-based view, mainly for testing
 *  
 */
class TextBased(val test: Boolean = false) extends Interface('t') {
  def testPieceInput(input: String, whose: Char): Option[Piece] = {
    if (input.length != 2) {
      if (!test) View.error("square name must be 2 chars long")
    }
    else if (input(0) < 'a' || input(0) > 'h') {
      if (!test) View.error("column must be a lowercase letter 'a' through 'j'")
    }
    else if (input(1) < '1' || input(1) > '8') {
      if (!test) View.error("row must be an integer '1' through '8'")
    }
    else {
      val p = Model.board.getPieceAt(input)
      if (p == None) {
        if (!test) View.error("there is no piece to select here")
      }
      else if (p.get.side != whose) {
        if (!test) View.error("cannot move opponent's piece")
      }
      else if (p.get.legalMoves.isEmpty) {
        if (!test) View.error("this piece has no legal moves")
      }
      else return p
    }
    None
  }
  
  def selectPiece(whose: Char): Option[Piece] = {
    while (true) {
      val input = readLine("Select piece to move (format - 'a1'): ")
      val p = testPieceInput(input, whose)
      if (p != None) return p
    }
    None //There shouldn't be a case in which this is reached
  }
  
  def testDestInput(input: String, piece: Piece): Option[Square] = {
    if (input.length != 2) {
      if (!test) View.error("square name must be 2 chars long")
    }
    else if (input(0) < 'a' || input(0) > 'h') {
      if (!test) View.error("column must be a lowercase letter 'a' through 'j'")
    }
    else if (input(1) < '1' || input(1) > '8') {
      if (!test) View.error("row must be an integer '1' through '8'")
    }
    else {
      val square = Model.board.nameToSquare(input)
      if (!piece.canMoveTo(square)) {
        if (!test) View.error("invalid move")
      }
      else return Option(square)
    }
    None
  }
  
  def selectDest(piece: Piece): Option[Square] = {
    while (true) {
      val input = readLine("Select destination (format - 'a1'): ")
      val to = testDestInput(input, piece)
      if (to != None) return to
    }
    None //There shouldn't be a case in which this is reached
  }
  
  override def toString: String = {
    var output = "Board: "
    if (Model.turn == 'W') output += "White" else output += "Black"
    output += "'s turn\n"
    output += Model.board.show
    
    output
  }
  
  def show = println(this.toString)
}


