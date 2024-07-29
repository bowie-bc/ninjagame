package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities.Bomb
import scalafx.scene.image.Image
import scala.util.Random

object BombController {
  private val unslicedImage = new Image(getClass.getResourceAsStream("/images/bomb.png"))
  private val slicedImage = new Image(getClass.getResourceAsStream("/images/bombed.png"))

  def createBomb(): Bomb = {
    val x = Random.nextDouble() * 800
    val y = 600 // Spawn from below
    val velocityX = Random.nextDouble() * 2 - 1
    val velocityY = -6 - Random.nextDouble() * 2
    val weight = 1.1 + Random.nextDouble() * 0.2
    Bomb(x, y, 150, 150, velocityX, velocityY, weight, 0.1, unslicedImage, slicedImage)
  }

  def createBombs(count: Int): List[Bomb] = {
    List.fill(count)(createBomb())
  }
}
