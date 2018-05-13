package model

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalactic.source.Position.apply

class Piece_Test extends FunSpec with Matchers {
  Model.board.reset
  describe("Piece") {
    describe("Pawn") {
      val d2 = Model.board.getPieceAt("d2").get
      it("moves forward into empty") {
        d2.canMoveTo(Square(3,2)) should equal(true)
      }
      it("doesn't attack forward") {
        Model.board.addPiece(Piece("Bp", Square(3,2)))
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
    Model.board.reset
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
    Model.board.reset
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
        Model.board.removePiece(Model.board.getPieceAt("a2").get)
        a1.canMoveTo(Square(0,6)) should equal(true)
      }
    }
    Model.board.reset
    describe("King") {
      val e1 = Model.board.getPieceAt("e1").get
      it("doesn't move more than 1 square") {
        Model.board.removePiece(Model.board.getPieceAt("e2").get)
        e1.canMoveTo(Square(4,2)) should equal(false)
      }
      it("doesn't move off the board") {
        e1.canMoveTo(Square(4,-1)) should equal(false)
      }
    }
    
  }
}