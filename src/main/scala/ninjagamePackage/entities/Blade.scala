package ninjagamePackage.entities

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.effect.Glow
import scalafx.scene.paint.Color

//Case class to represent blade's trail path, storing a list of (x,y) points representing the cursor coordinates
case class Blade(bladePoints: List[(Double,Double)]){

  //Method to add new points to the blade's trail
  def addPoint(x: Double, y: Double): Blade = {

    //Add new points to the beginning of the existing trail points
    val newPoints = (x,y) :: bladePoints

    //Define the maximum length of trail (maximum number of points)
    val maxLength = 50

    //Return a new Blade instance with the updated points to be added. limiting to the maxLength
    Blade(newPoints.take(maxLength))
  }

  //Method to draw the blade's trail points on the GraphicsContext
  def draw(gc: GraphicsContext): Unit = {
    //Set stroke color for the blade's trail
    gc.setStroke(Color.Orange)

    //Set width of blade's trail
    gc.setLineWidth(5)

    //Set glow effect of blade's trail
    gc.setEffect(new Glow(100))

    //Check if the list of trail points contains any points to draw
    if (bladePoints.nonEmpty){
      //To begin a new path for drawing of blade's trail
      gc.beginPath()

      //Get the first point of blade's trail
      val (x0, y0) = bladePoints.head

      //Move to first point
      gc.moveTo(x0, y0)

      //Draw lines to connect each subsequent point in the blade's trail
      bladePoints.tail.foreach { case(x, y) =>
        gc.lineTo(x, y)
      }

      //Stroke the path to make the blade's trail visible
      gc.strokePath()
    }

    //Clear the effect after drawing
    gc.setEffect(null)
  }

}