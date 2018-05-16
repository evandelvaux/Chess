package view

import model._
import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalactic.source.Position.apply

class View_Test extends FunSpec with Matchers {
  describe("View") {
    describe("Text-based interface") {
      val interface = new TextBased(test = true) // True disables error messages while testing
      it("doesn't allow bad input") {
        Model.board.reset
        interface.testPieceInput("i3", 'W') should equal(None) // Bad column
        interface.testPieceInput("bad", 'W') should equal(None) // Not a square
        interface.testPieceInput("f9", 'W') should equal(None) // Bad row
        interface.testPieceInput("b3", 'W') should equal(None) // No piece to select
        interface.testPieceInput("b7", 'W') should equal(None) // Opponent's piece
        interface.testPieceInput("a1", 'W') should equal(None) // No legal moves
        
        interface.testDestInput("a5", Model.board.getPieceAt("a2").get) should equal(None)
      }
      it("allows good input and behaves correctly") {
        interface.testPieceInput("b1", 'W').get.name should equal("Wn")
        interface.testDestInput("a4", Model.board.getPieceAt("a2").get).get.toString should equal("a4")
      }
      it("shows output correctly") {
        val expected = "Board: White's turn\n"+
            "   #######################\n"+
            "8 #Br Bn Bb Bq Bk Bb Bn Br#\n"+
            "7 #Bp Bp Bp Bp Bp Bp Bp Bp#\n"+
            "6 #-- -- -- -- -- -- -- --#\n"+
            "5 #-- -- -- -- -- -- -- --#\n"+
            "4 #-- -- -- -- -- -- -- --#\n"+
            "3 #-- -- -- -- -- -- -- --#\n"+
            "2 #Wp Wp Wp Wp Wp Wp Wp Wp#\n"+
            "1 #Wr Wn Wb Wq Wk Wb Wn Wr#\n"+
            "   #######################\n"+
            "   a  b  c  d  e  f  g  h"
        interface.toString should equal(expected)
      }

    }
    
  }
}