package model

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalactic.source.Position.apply

class Square_Test extends FunSpec with Matchers {
  describe("Square") {
    it("converts to string correctly"){
      Square(3,1).toString should equal("d2")
    }
  }
}