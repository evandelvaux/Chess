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
    val view  = new View
    val controller = new Controller(view) //, Model)

    view.init(controller)
    //view.showWindow
  }

}
