package scala.ch.makery.ninjagame.entities

import scalafx.scene.canvas.GraphicsContext

abstract class GameEntity(var posX: Double,
                          var posY: Double,
                          var width: Double,
                          var height: Double,
                          var velocityX: Double,
                          var velocityY: Double,
                          var weight: Double,
                          var gravity: Double = 0.1) {

  def move(): Unit = {
    posX += velocityX
    posY += velocityY
    velocityY += gravity * weight
    if (posX < 0) posX = 0
    if (posX > 800 - width) posX = 800 - width
    if (posY < 0) posY = 0
  }

  def draw(gc: GraphicsContext): Unit

  def isHit(px: Double, py: Double): Boolean = {
    px >= posX && px <= posX + width && py >= posY && py <= posY + height
  }
}
