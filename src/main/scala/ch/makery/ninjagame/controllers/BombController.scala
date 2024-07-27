package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities.Bomb
import scalafx.scene.paint.Color
import scala.util.Random

object BombController {
  def createBomb(): Bomb = {
    val x = Random.nextDouble() * 800
    val y = 600 // Spawn from below
    val velocityX = Random.nextDouble() * 2 - 1 // Reduced horizontal velocity
    val velocityY = -10 - Random.nextDouble() * 2 // Reduced initial upward velocity
    println(s"Creating bomb at ($x, $y) with velocity ($velocityX, $velocityY)")
    Bomb(x, y, 50, 50, Color.Black, velocityX, velocityY)
  }
}
