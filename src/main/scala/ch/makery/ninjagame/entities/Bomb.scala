package scala.ch.makery.ninjagame.entities

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

case class Bomb(var x: Double, var y: Double, var width: Double, var height: Double, var color: Color, var velocityX: Double, var velocityY: Double, val gravity: Double = 0.1) {
  val shape: Rectangle = new Rectangle {
    width = Bomb.this.width
    height = Bomb.this.height
    fill = Bomb.this.color
    x = Bomb.this.x
    y = Bomb.this.y
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
    println(s"Bomb at ($x, $y) with velocity ($velocityX, $velocityY)")
  }

  def isHit(mouseX: Double, mouseY: Double): Boolean = {
    shape.contains(mouseX, mouseY)
  }
}
