package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities.Fruit
import scalafx.scene.paint.Color
import scala.util.Random

object FruitController {
  def createFruit(): Fruit = {
    val x = Random.nextDouble() * 800
    val y = 600 // Spawn from below
    val velocityX = Random.nextDouble() * 2 - 1 // Reduced horizontal velocity
    val velocityY = -6 - Random.nextDouble() * 2 // Reduced initial upward velocity
    val points = Random.nextInt(10) match {
      case n if n < 5 => 1 // Common fruit
      case n if n < 8 => 5 // Uncommon fruit
      case _ => 10 // Rare fruit
    }
    val color = points match {
      case 1 => Color.Red
      case 5 => Color.Yellow
      case 10 => Color.Green
    }
    println(s"Creating fruit at ($x, $y) with velocity ($velocityX, $velocityY) and color $color")
    Fruit(x, y, 50, 50, color, velocityX, velocityY, points)
  }
}
