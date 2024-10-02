package ninjagamePackage.entities

import scala.util.Random
import scalafx.scene.image.Image

//Class representing a Bomb entity which extends GameEntity class
class Bomb(_posX: Double,
           _posY: Double,
           _width: Int,
           _height: Int,
           _velocityX: Double,
           _velocityY: Double,
           _weight: Double,
           _unslicedImage: Image,
           _slicedImage: Image
          ) extends GameEntity(_posX, _posY, _width, _height, _velocityX, _velocityY, _weight, _unslicedImage, _slicedImage){

}

//Companion object for Bomb class, responsible in creating new Bomb instance
object Bomb {

  //Function to create bomb instance with random attributes of weight, initial position and velocity
  def createBomb (): Bomb = {
    //Set a random x position and a fixed y position (right below the boundary) of bomb within the scene
    val initialPosX = Random.nextDouble() * 1024
    val initialPosY = 768

    //Generate random initial velocityX and velocityY of bomb
    val initialVelocityX = Random.nextDouble() * 2 - 1
    val initialVelocityY = -10 - Random.nextDouble() * 2

    //Generate random weight of bomb
    val weight = 1.1  + Random.nextDouble() * 0.2

    //Set width and height of bomb
    val width = 100
    val height = 100

    //Load image of unsliced and sliced bomb
    val unslicedImage = new Image(getClass.getResourceAsStream("/images/bomb.png"))
    val slicedImage = new Image(getClass.getResourceAsStream("/images/bombed.png"))

    //Create and return a new Bomb instance with set attributes
    new Bomb(initialPosX, initialPosY, width, height, initialVelocityX, initialVelocityY, weight, unslicedImage, slicedImage)
  }

}