package model

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalactic.source.Position.apply

class Piece_Test extends FunSpec with Matchers {
  Model.board.reset
  describe("Pieces") {
    describe("Pawn") {
      val d2 = Model.board.getPieceAt("d2").get
      it("moves forward into empty") {
        d2.canMoveTo(Square(3,2)) should equal(true)
      }
      it("moves forward twice on first move into empty") {
        d2.canMoveTo(Square(3,3)) should equal(true)
      }
      it("doesn't move forward twice through a piece") {
        Model.board.addPiece(Piece("Bp", Square(3,2)))
        d2.canMoveTo(Square(3,3)) should equal(false)
      }
      it("doesn't move forward twice after first move") {
        Model.board.addPiece(Piece("Wp", Square(0,2)))
        val a3 = Model.board.getPieceAt("a3").get
        a3.hasMoved = true
        a3.canMoveTo(Square(0,4)) should equal(false)
      }
      it("doesn't attack forward") {
        d2.canMoveTo(Square(3,2)) should equal(false)
      }
      it("doesn't move diagonally into empty") {
        d2.canMoveTo(Square(4,2)) should equal(false)
      }
      it("moves diagonally to attack") {
        Model.board.addPiece(Piece("Bp", Square(4,2)))
        d2.canMoveTo(Square(4,2)) should equal(true)
      }
      it("doesn't attack friendly pieces") {
        Model.board.addPiece(Piece("Wp", Square(2,2)))
        d2.canMoveTo(Square(2,2)) should equal(false)
      }
    }
    describe("Knight") {
      val g1 = Model.board.getPieceAt("g1").get
      it("moves appropriately into empty") {
        g1.canMoveTo(Square(7,2)) should equal(true)
      }
      it("attacks") {
        Model.board.addPiece(Piece("Bp", Square(5,2)))
        g1.canMoveTo(Square(5,2)) should equal(true)
      }
      it("doesn't attack friendly pieces") {
        Model.board.addPiece(Piece("Wp", Square(7,2)))
        g1.canMoveTo(Square(7,2)) should equal(false)
      }
    }
    describe("Bishop") {
      val c1 = Model.board.getPieceAt("c1").get
      it("doesn't move through pieces") {
        c1.canMoveTo(Square(4,2)) should equal(false)
      }
      it("moves appropriately into empty") {
        Model.board.removePiece(Model.board.getPieceAt("d2").get)
        c1.canMoveTo(Square(4,2)) should equal(true)
      }
      it("attacks") {
        Model.board.addPiece(Piece("Bp", Square(4,2)))
        c1.canMoveTo(Square(4,2)) should equal(true)
      }
      it("doesn't attack friendly pieces") {
        c1.canMoveTo(Square(1,1)) should equal(false)
      }
    }
    describe("Rook") {
      
      val a1 = Model.board.getPieceAt("a1").get
      it("doesn't move through pieces") {
        a1.canMoveTo(Square(0,6)) should equal(false)
      }
      it("moves across the board") {
        Model.board.reset
        Model.board.removePiece(Model.board.getPieceAt("a2").get)
        a1.canMoveTo(Square(0,6)) should equal(true)
      }
    }
    describe("King") {
      val e1 = Model.board.getPieceAt("e1").get
      it("doesn't move more than 1 square") {
        Model.board.removePiece(Model.board.getPieceAt("e2").get)
        e1.canMoveTo(Square(4,2)) should equal(false)
      }
      it("doesn't move off the board") {
        e1.canMoveTo(Square(4,-1)) should equal(false)
      }
      it("doesn't castle when blocked") {
        e1.canMoveTo(Square(2,0)) should equal(false)
        e1.canMoveTo(Square(6,0)) should equal(false)
      }
      it("castles when not blocked") {
        Model.board.removePiece(Model.board.getPieceAt("b1").get)
        Model.board.removePiece(Model.board.getPieceAt("c1").get)
        Model.board.removePiece(Model.board.getPieceAt("d1").get)
        e1.canMoveTo(Square(2,0)) should equal(true)
        Model.board.removePiece(Model.board.getPieceAt("f1").get)
        Model.board.removePiece(Model.board.getPieceAt("g1").get)
        e1.canMoveTo(Square(6,0)) should equal(true)
      }
      it("doesn't castle if moved") {
        e1.hasMoved = true
        e1.canMoveTo(Square(2,0)) should equal(false)
        e1.canMoveTo(Square(6,0)) should equal(false)
      }
      it("doesn't castle to rook that has moved") {
        e1.hasMoved = false
        Model.board.getPieceAt("a1").get.hasMoved = true
        e1.canMoveTo(Square(2,0)) should equal(false)
        e1.canMoveTo(Square(6,0)) should equal(true)
        Model.board.getPieceAt("a1").get.hasMoved = false
        Model.board.getPieceAt("h1").get.hasMoved = true
        e1.canMoveTo(Square(2,0)) should equal(true)
        e1.canMoveTo(Square(6,0)) should equal(false)
      }
    }
    describe("General piece behavior") {
      it("moving executes properly") {
        Model.board.reset
        val k = Model.board.getPieceAt("g1").get
        k.canMoveTo(Square(5,2)) should equal(true)
        k.move(Square(5,2))
        Model.board.getPieceAt("g1") should equal(None)
        Model.board.getPieceAt("f3").get should equal(k)
      }
      it("attacking executes properly") {
        val k = Model.board.getPieceAt("f3").get
        Model.board.addPiece(Piece("Bn", Square(4,4)))
        k.canMoveTo(Square(4,4)) should equal(true)
        k.move(Square(4,4))
        Model.board.getPieceAt("f3") should equal(None)
        Model.board.getPieceAt("e5").get should equal(k)
      }
    }
    
  }
}