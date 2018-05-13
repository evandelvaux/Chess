package view

import model._
import controller._

import scala.swing._
import BorderPanel.Position._
import java.awt.geom._
import java.awt.Color
import java.awt.Font
import java.awt.Dimension
import javax.swing.border._
import scala.swing.Orientation

/** A view in a MVC design.
  *@constructor Create a new view
  */
class View {
    /** The views controller in the MVC model
  *  
  */
  var controller: Option[Controller] = None
  /** The views model in the MVC model
  *  
  */
  //var model: Option[Model] = None
  
  //******* init ******* 

  /** A model in a MVC designs
  *  
  *@param ctr The view's controller
  *@param mod The view's model
  */ 
  def init(ctr: Controller) {//, mod: Model) {
    controller = Some(ctr)
    //model = Some(mod)
  }
  
}