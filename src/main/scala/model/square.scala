package model

case class Square(x: Int, y: Int) {
  override def toString: String = {
    var output = ""
    output += ('a' + x).toChar.toString // Columns are a - h
    output += (y + 1).toString // Chess grid goes 1-8
    output
  }
  
  def +(diff: (Int,Int)): Square = {
    Square(this.x + diff._1, this.y + diff._2)
  }
  
  def -(other: Square): (Int,Int) = {
    (this.x - other.x, this.y - other.y) 
  }
  
  def ==(other: Square): Boolean = {
    this.x == other.x && this.y == other.y
  }
  
  def !=(other: Square): Boolean = {
    this.x != other.x || this.y != other.y
  }
}