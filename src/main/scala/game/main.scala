package game

import controller._
import model._
import view._

/** The foundation of the game which holds the model, view, and controller
  *
  */
object Main {

/** main initializes the components of the model, view, and controller.
  *
  */

  def main(args: Array[String]): Unit = {
    
    // Change the type of view used (t: text; g: GUI)
    val vType = 't'
    
    Model.board.reset
    View.init(vType)
    Controller.doGame
  }

}
