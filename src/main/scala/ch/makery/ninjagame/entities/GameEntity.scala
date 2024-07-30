package scala.ch.makery.ninjagame.entities

import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext

abstract class GameEntity(var x: Double,
                          var y: Double,
                          var width: Double,
                          var height: Double,
                          var velocityX: Double,
                          var velocityY: Double,
                          var weight: Double,
                          var gravity: Double,
                          var unslicedImage: Image,
                          var slicedImage: Image
                         ) {

  var isSliced: Boolean = false

  def move(): Unit = {
    x += velocityX
    y += velocityY
    velocityY += gravity * weight
    if (x < 0) x = 0
    if (x > 800 - width) x = 800 - width
    if (y < 0) y = 0
  }

  def draw(gc: GraphicsContext): Unit = {
    val image = if (isSliced) slicedImage else unslicedImage
    gc.drawImage(image, x, y, width, height)
  }

  def slice(sliceX: List[Double], sliceY: List[Double]): Boolean = {
    if (!isSliced && sliceX.zip(sliceY).exists { case (sx, sy) => contains(sx, sy) }) {
      isSliced = true
      println(s"${this.getClass.getSimpleName} at ($x, $y) sliced!")
      true
    } else {
      false
    }
  }

  private def contains(px: Double, py: Double): Boolean = {
    px >= x && px <= x + width && py >= y && py <= y + height
  }
}
