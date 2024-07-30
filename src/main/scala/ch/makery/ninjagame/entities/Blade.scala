package scala.ch.makery.ninjagame.entities

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color
import scalafx.scene.effect.Glow

case class Blade(points: List[(Double, Double)]) {

  def addPoint(x: Double, y: Double): Blade = {
    val newPoints = (x, y) :: points
    val maxLength = 50
    Blade(newPoints.take(maxLength))
  }

  def draw(gc: GraphicsContext): Unit = {
    gc.setStroke(Color.Orange)
    gc.setLineWidth(5)

    val glow = new Glow(100) // Adjust the intensity of the glow here
    gc.setEffect(glow)

    if (points.nonEmpty) {
      gc.beginPath()
      val (x0, y0) = points.head
      gc.moveTo(x0, y0)
      points.tail.foreach { case (x, y) =>
        gc.lineTo(x, y)
      }
      gc.strokePath()
    }

    // Clear the effect after drawing
    gc.setEffect(null)
  }
}