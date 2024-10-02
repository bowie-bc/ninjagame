package ninjagamePackage.entities

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image

abstract class GameEntity(var posX: Double,
                          var posY: Double,
                          val width: Int,
                          val height: Int,
                          var velocityX: Double,
                          var velocityY: Double,
                          val weight: Double,
                          var unslicedImage: Image,
                          var slicedImage: Image){

  //Initialize attributes such as whether game entity isSliced and fixed attributes such as gravity and motion speed
  var isSliced: Boolean = false
  private val gravity: Double = 0.1
  private val motionSpeed: Double = 0.3

  //Method to move the game entity based on its velocity and gravity
  def move(): Unit = {
    posX += velocityX * motionSpeed
    posY += velocityY * motionSpeed
    velocityY += weight * gravity * motionSpeed

    //Ensure game entity stays within the boundaries of 1024 x 768
    if (posX < 0) posX = 0
    if (posX > (1024 - width)) posX = 1024 - width
    if (posY < 0) posY = 0
  }

  //Method to draw game entity's image (sliced / unsliced) based on the isSliced Boolean on the GraphicsContext
  def draw(gc: GraphicsContext): Unit = {
    val image = if (isSliced) slicedImage else unslicedImage
    gc.drawImage(image, posX, posY, width, height)
  }

  //Method to slice game entity
  def slice(bladePoints: List[(Double,Double)]): Boolean = {
    //Check if blade's points intersects with the entity's boundaries
    val isHit = bladePoints.exists {case (x, y) => contains(x, y)}

    //Check if game entity is not sliced and isHit then set isSliced to true, and return true
    if (!isSliced && isHit) {
      isSliced = true
      true
    }

    else {
      false
    }
  }

  //Method to check if point is within the entity's boundaries
  def contains (x: Double, y: Double): Boolean = {
    (x >= posX && x <= (posX + width)) && (y >= posY && y <= (posY + height))
  }

}