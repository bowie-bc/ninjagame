package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities.Fruit
import scalafx.scene.paint.Color
import scala.util.Random
import scalafx.scene.image.Image

object FruitController {
  val unslicedImages: Map[String, Image] = Map(
    "apple" -> new Image(getClass.getResourceAsStream("/unsliced1.png")),
    "banana" -> new Image(getClass.getResourceAsStream("/unsliced2.png")),
    "orange" -> new Image(getClass.getResourceAsStream("/unsliced3.png"))
  )

  val slicedImages: Map[String, Image] = Map(
    "apple" -> new Image(getClass.getResourceAsStream("/sliced1.png")),
    "banana" -> new Image(getClass.getResourceAsStream("/sliced2.png")),
    "orange" -> new Image(getClass.getResourceAsStream("/sliced3.png"))
  )

  val fruitWeights: Map[String, Double] = Map(
    "apple" -> 1.15,
    "banana" -> 1.1,
    "orange" -> 1.2
  )

  def createFruit(): Fruit = {
    val fruitType = Random.shuffle(fruitWeights.keys.toList).head
    val x = Random.nextDouble() * 800
    val y = 600 // Spawn from below
    val velocityX = Random.nextDouble() * 2 - 1
    val velocityY = -8 - Random.nextDouble() * 2
    val weight = fruitWeights(fruitType)
    val points = fruitType match {
      case "apple" => 1
      case "banana" => 5
      case "orange" => 10
    }

    val unslicedImage = unslicedImages(fruitType)
    val slicedImage = slicedImages(fruitType)

    Fruit(x, y, 150, 150, velocityX, velocityY, points, weight, 0.1, unslicedImage, slicedImage)
  }
}
