package scala.ch.makery.ninjagame.entities

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

case class Blade(points: List[(Double, Double)]) {

  def addPoint(x: Double, y: Double): Blade = {
    Blade((x, y) :: points.take(10)) // Keep only the latest 10 points
  }

  def draw(gc: GraphicsContext): Unit = {
    gc.setStroke(Color.Blue)
    gc.setLineWidth(3)
    if (points.nonEmpty) {
      gc.beginPath()
      val (x0, y0) = points.head
      gc.moveTo(x0, y0)
      points.tail.foreach { case (x, y) =>
        gc.lineTo(x, y)
      }
      gc.strokePath()
    }
  }
}
