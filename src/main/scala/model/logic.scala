package model

abstract class Logic(val name: Char) {
  def getMoves: List[Move]
}

object WhitePawn extends Logic('p') {
  def getMoves: List[Move] = {
    var moves = List(Move(0,1,-1), Move(-1,1,1), Move(1,1,1))
    moves
  }
}

object BlackPawn extends Logic('d') { // d for upside-down p ;)
  def getMoves: List[Move] = {
    var moves = List(Move(0,-1,-1), Move(-1,-1,1), Move(1,-1,1))
    moves
  }
}

object Knight extends Logic('n') {
  def getMoves: List[Move] = {
    var moves = List(Move(-2,-1), Move(-2,1), Move(-1,-2), Move(-1,2),
        Move(1,-2), Move(1,2), Move(2,-1), Move(2,1))
    moves
  }
}

object Bishop extends Logic('b') {
  def getMoves: List[Move] = {
    var moves = List.empty[Move]
    for (i <- 0 to 7) {
      moves +:= Move(-i,-i)
      moves +:= Move(-i,i)
      moves +:= Move(i,-i)
      moves +:= Move(i,i)
    }
    moves
  }
}

object Rook extends Logic('r') {
  def getMoves: List[Move] = {
    var moves = List.empty[Move]
    for (i <- 0 to 7) {
      moves +:= Move(-i,0)
      moves +:= Move(i,0)
      moves +:= Move(0,-i)
      moves +:= Move(0,i)
    }
    moves
  }
}

object Queen extends Logic('q') {
  def getMoves: List[Move] = {
    var moves = List.empty[Move]
    for (i <- 0 until 7) {
      moves +:= Move(-i,-i)
      moves +:= Move(-i,i)
      moves +:= Move(i,-i)
      moves +:= Move(i,i)
      moves +:= Move(-i,0)
      moves +:= Move(i,0)
      moves +:= Move(0,-i)
      moves +:= Move(0,i)
    }
    moves
  }
}

object King extends Logic('k') {
  def getMoves: List[Move] = {
    var moves = List.empty[Move]
    for (i <- -1 to 1; j <- -1 to 1) {
      if (!(i == 0 && j == 0))
        moves +:= Move(i,j)
    }
    moves
  }
}

object Empty extends Logic('?') {
  def getMoves: List[Move] = List.empty[Move]
}