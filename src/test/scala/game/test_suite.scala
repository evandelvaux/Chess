package game

import model._
import org.scalatest.Suites

class TestSuites extends Suites(
   new Square_Test,
   new Piece_Test,
   new Board_Test
)
