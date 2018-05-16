package model

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalactic.source.Position.apply

class Board_Test extends FunSpec with Matchers {
  describe("Board") {
    it("sets correctly"){
      val expected = {
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
      }
      
      Model.board.reset
      Model.board.show should equal(expected)
    }
    it("resets correctly") {
      val expected = {
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
      }
      
      val expected2 = {
        "   #######################\n"+
        "8 #Br Bn Bb Bq Bk Bb Bn Br#\n"+
        "7 #Bp Bp Bp Bp Bp Bp Bp Bp#\n"+
        "6 #-- -- -- -- -- -- -- --#\n"+
        "5 #-- -- -- -- Bq -- -- --#\n"+
        "4 #-- -- -- -- -- -- -- --#\n"+
        "3 #Wp -- -- -- -- -- -- --#\n"+
        "2 #Wp Wp Wp Wp Wp Wp Wp Wp#\n"+
        "1 #Wr Wn Wb Wq Wk Wb Wn Wr#\n"+
        "   #######################\n"+
        "   a  b  c  d  e  f  g  h"
      }
      
      Model.board.addPiece(Piece("Bq", Square(4,4)))
      Model.board.addPiece(Piece("Wp", Square(0,2)))
      Model.board.show should equal(expected2)
      
      Model.board.reset
      Model.board.show should equal(expected)
    }
  }
}