package model

case class Move(val dX: Int, val dY: Int, attack: Int = 0)
  /* attack = -1 means can't attack this way (pawns)
						= 0 means can attack or not
						= 1 means must be an attack (pawns)*/