package model

/** A model in a MVC design.
  *  @constructor Create a new model
  */
object Model {
  val white = new Player('W')
  val black = new Player('B')
  
  val board = new Board
  
  var turn = 'W'
}