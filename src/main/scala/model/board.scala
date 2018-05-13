package model

class Board {
  private var grid: Array[Array[Option[Piece]]] = Array.fill(8,8)(None)
  
  /*------------------ Methods ------------------*/
  
  /* Add a piece to the board
   * 
   */
  def addPiece(piece: Piece) = {
    this.grid(piece.loc.y)(piece.loc.x) = Option(piece)
  }
  
  /* Removes a piece from the board
   * 
   */
  def removePiece(piece: Piece) = {
    this.grid(piece.loc.y)(piece.loc.x) = None
  }
  
  def getPieceAt(sqName: String): Option[Piece] = {
    val x = sqName(0) - 'a'
    val y = sqName(1) - '1'
    this.grid(y)(x)
  }
  
  /* Tells if a square is in the legal boards of the game
   * 
   */
  def inBounds(square: Square): Boolean = {
    square.x >= 0 && square.x < 8 &&
    square.y >= 0 && square.y < 8
  }
  
  /* Checks a square and returns whether the square is empty or 
   * contains a friendly or hostile piece
   * 
   */
  def checkSquare(side: Char, square: Square): Int = {
    val at = this.grid(square.y)(square.x)
    if (at == None) return 0
    else if (at.get.side == side) return 1
    else return -1
  }
  
  /* Checks the spaces in between 2 squares to determine whether
   * a route is blocked (false = blocked)
   * 
   */
  def checkBetween(s: Square, d: Square): Boolean = {
    // 1st check to see if it's a knight move
    val (dX, dY) = d - s
    if ((dX*dX == 1 && dY*dY == 4) || (dX*dX == 4 && dY*dY == 1))
      return true // Knights don't have to worry about in-between
    
    // Figure out what to increment by
    val xUnit = if (dX < 0) -1 else if (dX > 0) 1 else dX
    val yUnit = if (dY < 0) -1 else if (dY > 0) 1 else dY
    
    // Go through each in-between square
    var bw = s + (xUnit, yUnit)
    while (bw != d) {
      if (checkSquare('W', bw) != 0) return false
        // Doesn't matter which side the square is
      bw = bw + (xUnit, yUnit) // Iterate to next between square
    }
    true
  }
  
  /* Set up the board for a new game
   * 
   */
  def reset: Unit = {
    this.grid = Array.fill(8,8)(None)
    Model.white.reset
    Model.black.reset
    val wHome: Array[Option[Piece]] = Array.fill(8)(None)
    val bHome: Array[Option[Piece]] = Array.fill(8)(None)
    val wPawn: Array[Option[Piece]] = Array.fill(8)(None)
    val bPawn: Array[Option[Piece]] = Array.fill(8)(None)
    for (i <- 0 until 8) {
      if (i == 0 || i == 7) {
        wHome(i) = Option(Piece("Wr", Square(i,0)))
        wPawn(i) = Option(Piece("Wp", Square(i,1)))
        bPawn(i) = Option(Piece("Bp", Square(i,6)))
        bHome(i) = Option(Piece("Br", Square(i,7)))
      }
      else if (i == 1 || i == 6) {
        wHome(i) = Option(Piece("Wn", Square(i,0)))
        wPawn(i) = Option(Piece("Wp", Square(i,1)))
        bPawn(i) = Option(Piece("Bp", Square(i,6)))
        bHome(i) = Option(Piece("Bn", Square(i,7)))
      }
      else if (i == 2 || i == 5) {
        wHome(i) = Option(Piece("Wb", Square(i,0)))
        wPawn(i) = Option(Piece("Wp", Square(i,1)))
        bPawn(i) = Option(Piece("Bp", Square(i,6)))
        bHome(i) = Option(Piece("Bb", Square(i,7)))
      }
      else if (i == 3) {
        wHome(i) = Option(Piece("Wq", Square(i,0)))
        wPawn(i) = Option(Piece("Wp", Square(i,1)))
        bPawn(i) = Option(Piece("Bp", Square(i,6)))
        bHome(i) = Option(Piece("Bq", Square(i,7)))
      }
      else { // if (i == 4) {
        wHome(i) = Option(Piece("Wk", Square(i,0)))
        wPawn(i) = Option(Piece("Wp", Square(i,1)))
        bPawn(i) = Option(Piece("Bp", Square(i,6)))
        bHome(i) = Option(Piece("Bk", Square(i,7)))
      }
    }
    // Not currently needed because pieces add themselves to board
    /*for (j <- 0 until 8) {
      board(j) = {
        if (j == 0) bHome
        else if (j == 1) bPawn
        else if (j == 6) wPawn
        else if (j == 7) wHome
        else Array.fill(8)(None)
      }
    }*/
  }
  
  /* Displays the current state of the board via text output
   * 
   */
  def show: String = {
  /*"Br Bn Bb Bq Bk Bb Bn Br\n"+
    "Bp Bp Bp Bp Bp Bp Bp Bp\n"+
    "-- -- -- -- -- -- -- --\n"+
    "-- -- -- -- -- -- -- --\n"+
    "-- -- -- -- -- -- -- --\n"+
    "-- -- -- -- -- -- -- --\n"+
    "Wp Wp Wp Wp Wp Wp Wp Wp\n"+
    "Wr Wn Wb Wq Wk Wb Wn Wr\n"*/
    
    var output = ""
    for ((r,y) <- grid.zipWithIndex.reverse) {
      if (output != "") output += "\n"
      var row = ""
      for ((c,x) <- r.zipWithIndex) {
        if (row != "") row += " "
        row += {
          if (c == None) "--"
          else c.get.name
        }
      }
      output += row
    } // end for
    
    output
  } // End show
  
  /* Find a specific piece and return its location, if still in play
   * 
   */
  def findPiece(piece: Piece): Option[(Int,Int)] = {
    for ((r,y) <- grid.zipWithIndex; (p,x) <- r.zipWithIndex) {
      if (p == piece) return Option(x,y)
    }
    None
  }
  
} // END BOARD