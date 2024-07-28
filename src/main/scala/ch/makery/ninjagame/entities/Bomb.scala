package scala.ch.makery.ninjagame.entities

import scalafx.scene.image.Image
import scalafx.scene.paint.Color
import scalafx.scene.canvas.GraphicsContext

case class Bomb(var x: Double,
                var y: Double,
                var width: Double,
                var height: Double,
                var velocityX: Double,
                var velocityY: Double,
                var weight: Double,
                var gravity: Double = 0.1,
                var unslicedImage: Image,
                var slicedImage: Image
               ) {
  var isSliced: Boolean = false

  def move(): Unit = {
    x += velocityX
    y += velocityY
    velocityY += gravity
    if (x < 0) x = 0
    if (x > 800 - width) x = 800 - width
    if (y < 0) y = 0
  }

  def draw(gc: GraphicsContext): Unit = {
    val image = if (isSliced) slicedImage else unslicedImage
    gc.drawImage(image, x, y, width, height)
  }

  def slice(): Unit = {
    isSliced = true
  }

  def isHit(mouseX: Double, mouseY: Double): Boolean = {
    x <= mouseX && mouseX <= x + width && y <= mouseY && mouseY <= y + height
  }
}
