package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities.Bomb
import scalafx.scene.paint.Color
import scala.util.Random

object BombController {
  def createBomb(): Bomb = {
    val x = Random.nextDouble() * 800
    val y = 600 // Spawn from below
    val velocityX = Random.nextDouble() * 2 - 1
    val velocityY = -8 - Random.nextDouble() * 2
    val weight = 1.1 + Random.nextDouble() * 0.2 // Random weight between 1.1 and 1.3
    Bomb(x, y, 50, 50, Color.Black, velocityX, velocityY, weight)
  }

  def createBombs(numBombs: Int): List[Bomb] = {
    List.fill(numBombs)(createBomb())
  }
}
