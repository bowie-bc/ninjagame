package scala.ch.makery.ninjagame.entities

sealed trait Difficulty {
  def initialLives: Int
}

case object Easy extends Difficulty {
  val initialLives: Int = 5
}

case object Medium extends Difficulty {
  val initialLives: Int = 3
}

case object Hard extends Difficulty {
  val initialLives: Int = 1
}