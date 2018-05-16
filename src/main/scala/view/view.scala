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
import scala.io.StdIn

/** A view in a MVC design.
  *@constructor Create a new view
  */
object View {
  
  var interface: Option[Interface] = None

  //******* init ******* 

  /** Initiate the view
   *  
   */
  def init(vType: Char) = {//, mod: Model) {
    if (vType == 't') this.interface = Option(new TextBased)
    this.interface.get.show
    //else Option(new GUI)
  }
  
  /** Refresh the interface for the player
   *  
   */
  def refresh = {
    this.interface.get.show
  }
  
  /** Notifies the player that an error has occurred
   *  
   */
  def error(message: String) = {
    println("##Error: " + message)
  }
  
  /** Use the interface to get player input, then make the appropriate move
   *  
   */
  def getDecision(whose: Char): (Piece,Square) = {
    val piece: Piece = interface.get.selectPiece(whose).get
    val dest: Square = interface.get.selectDest(piece).get
    (piece,dest)
  }
  
  
  //def showGUI
  
}