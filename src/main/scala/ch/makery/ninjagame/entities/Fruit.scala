package scala.ch.makery.ninjagame.entities

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

case class Fruit(var x: Double, var y: Double, var width: Double, var height: Double, var color: Color, var velocityX: Double, var velocityY: Double, var points: Int, val gravity: Double = 0.1) {
  val shape: Rectangle = new Rectangle {
    width = Fruit.this.width
    height = Fruit.this.height
    fill = Fruit.this.color
    x = Fruit.this.x
    y = Fruit.this.y
  }

  def move(): Unit = {
    x += velocityX
    y += velocityY
    velocityY += gravity
    if (x < 0) x = 0
    if (x > 800 - width) x = 800 - width
    if (y < 0) y = 0
    shape.x = x
    shape.y = y
    println(s"Fruit at ($x, $y) with velocity ($velocityX, $velocityY)")
  }

  def isSliced(sliceX: List[Double], sliceY: List[Double]): Boolean = {
    sliceX.zip(sliceY).exists { case (x, y) => shape.contains(x, y) }
  }
}
