package controller

import model._
import view._
import scala.swing._

/** A controller for the MVC design
  *
  * @constructor Create a new controller with a view and a model
  * @param view the controller's view component of the MVC design
  * @param model the controller's model component of the MVC design
  */

object Controller { //, model: Model) {
  
  /*------------------------ Methods -----------------------------*/
  
  
  /* Switches whose turn it is
   * 
   */
  def altTurn: Unit = {
    Model.turn = if (Model.turn == 'W') 'B' else 'W'
  }
  
  /* Complete's one player's turn
   * 
   */
  def doTurn: Unit = {
    val (piece, dest) = View.getDecision(Model.turn)
    piece.move(dest)
    this.altTurn
    View.refresh
  }
  
  /* Plays until a game is complete
   * 
   */
  def doGame: Unit = {
    while (true) {
      this.doTurn
    }
  }
  
}