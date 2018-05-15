package model

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalactic.source.Position.apply

class Board_Test extends FunSpec with Matchers {
  describe("Board") {
    it("sets correctly"){
      val expected = {
        "Br Bn Bb Bq Bk Bb Bn Br\n"+
        "Bp Bp Bp Bp Bp Bp Bp Bp\n"+
        "-- -- -- -- -- -- -- --\n"+
        "-- -- -- -- -- -- -- --\n"+
        "-- -- -- -- -- -- -- --\n"+
        "-- -- -- -- -- -- -- --\n"+
        "Wp Wp Wp Wp Wp Wp Wp Wp\n"+
        "Wr Wn Wb Wq Wk Wb Wn Wr"
      }
      
      Model.board.reset
      Model.board.show should equal(expected)
    }
    it("resets correctly") {
      val expected = {
        "Br Bn Bb Bq Bk Bb Bn Br\n"+
        "Bp Bp Bp Bp Bp Bp Bp Bp\n"+
        "-- -- -- -- -- -- -- --\n"+
        "-- -- -- -- -- -- -- --\n"+
        "-- -- -- -- -- -- -- --\n"+
        "-- -- -- -- -- -- -- --\n"+
        "Wp Wp Wp Wp Wp Wp Wp Wp\n"+
        "Wr Wn Wb Wq Wk Wb Wn Wr"
      }
      
      val expected2 = {
        "Br Bn Bb Bq Bk Bb Bn Br\n"+
        "Bp Bp Bp Bp Bp Bp Bp Bp\n"+
        "-- -- -- -- -- -- -- --\n"+
        "-- -- -- -- Bq -- -- --\n"+
        "-- -- -- -- -- -- -- --\n"+
        "Wp -- -- -- -- -- -- --\n"+
        "Wp Wp Wp Wp Wp Wp Wp Wp\n"+
        "Wr Wn Wb Wq Wk Wb Wn Wr"
      }
      
      Model.board.addPiece(Piece("Bq", Square(4,4)))
      Model.board.addPiece(Piece("Wp", Square(0,2)))
      Model.board.show should equal(expected2)
      
      Model.board.reset
      Model.board.show should equal(expected)
    }
  }
}