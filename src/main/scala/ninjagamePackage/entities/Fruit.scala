package ninjagamePackage.entities

import scala.util.Random
import scalafx.scene.image.Image

//Class representing a Fruit entity which extends GameEntity class
class Fruit(_posX: Double,
            _posY: Double,
            _width: Int,
            _height: Int,
            _velocityX: Double,
            _velocityY: Double,
            _weight: Double,
            _unslicedImage: Image,
            _slicedImage: Image,
            val points: Int
           ) extends GameEntity(_posX, _posY, _width, _height, _velocityX, _velocityY, _weight, _unslicedImage, _slicedImage){

}

//Companion object for fruit class, responsible in creating new Fruit instance
object Fruit {
  //Case class to define attributes of different fruit types
  private case class FruitType(name: String,
                               width: Int,
                               height: Int,
                               weight: Double,
                               unslicedImage: Image,
                               slicedImage: Image,
                               points: Int)

  //List of predefined fruit types with their respective attributes
  private val fruitTypes: List [FruitType] = List(
    FruitType("apple", 110, 110, 1.15, new Image(getClass.getResourceAsStream("/images/unsliced1.png")), new Image(getClass.getResourceAsStream("/images/sliced1.png")), 1),
    FruitType("banana", 100, 100, 1.0, new Image(getClass.getResourceAsStream("/images/unsliced2.png")), new Image(getClass.getResourceAsStream("/images/sliced2.png")), 3),
    FruitType("orange", 115, 115, 1.2, new Image(getClass.getResourceAsStream("/images/unsliced3.png")), new Image(getClass.getResourceAsStream("/images/sliced3.png")), 5),
    FruitType("watermelon", 160, 160, 1.5, new Image(getClass.getResourceAsStream("/images/unsliced4.png")), new Image(getClass.getResourceAsStream("/images/sliced4.png")), 10)
  )

  //Method to create a new Fruit instance
  def createFruit(): Fruit = {
    //Randomly select a fruit type
    val fruitType = Random.shuffle(fruitTypes).head

    //Set a random x position and a fixed y position (right below the boundary) of fruit within the scene
    val initialPosX = Random.nextDouble() * 1024
    val initialPosY = 768

    //Generate random initial velocityX and velocityY of fruit
    val initialVelocityX = Random.nextDouble() * 2 - 1
    val initialVelocityY = -10 - Random.nextDouble() * 2

    //Create and return a new Fruit instance with the randomly selected fruit type with set attributes
    new Fruit(initialPosX, initialPosY, fruitType.width, fruitType.height, initialVelocityX, initialVelocityY, fruitType.weight, fruitType.unslicedImage, fruitType.slicedImage, fruitType.points)
  }

}
