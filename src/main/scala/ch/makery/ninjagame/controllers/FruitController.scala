package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities.Fruit
import scala.util.Random
import scalafx.scene.image.Image

object FruitController {
  case class FruitType(name: String, unslicedImage: Image, slicedImage: Image, weight: Double, points: Int)

  val fruitTypes: List[FruitType] = List(
    FruitType("apple", new Image(getClass.getResourceAsStream("/images/unsliced1.png")), new Image(getClass.getResourceAsStream("/images/sliced1.png")), 1.15, 1),
    FruitType("banana", new Image(getClass.getResourceAsStream("/images/unsliced2.png")), new Image(getClass.getResourceAsStream("/images/sliced2.png")), 1.0, 3),
    FruitType("orange", new Image(getClass.getResourceAsStream("/images/unsliced3.png")), new Image(getClass.getResourceAsStream("/images/sliced3.png")), 1.2, 5),
    FruitType("watermelon", new Image(getClass.getResourceAsStream("/images/unsliced4.png")), new Image(getClass.getResourceAsStream("/images/sliced4.png")), 1.5, 10)
  )

  def createFruit(): Fruit = {
    val fruitType = Random.shuffle(fruitTypes).head
    val x = Random.nextDouble() * 800
    val y = 600 // Spawn from below
    val velocityX = Random.nextDouble() * 2 - 1
    val velocityY = -8 - Random.nextDouble() * 2

    Fruit(x, y, 150, 150, velocityX, velocityY, fruitType.points, fruitType.weight, 0.1, fruitType.unslicedImage, fruitType.slicedImage)
  }
}
