package scala.ch.makery.ninjagame

import scala.ch.makery.ninjagame.controllers.GameController
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.paint.Color
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler

object MainApp extends JFXApp {

  stage = new PrimaryStage {
    title = "Fruit Ninja"
    scene = new Scene(800, 600) {
      val canvas = new Canvas(800, 600)
      val gc: GraphicsContext = canvas.graphicsContext2D
      gc.stroke = Color.Blue
      gc.lineWidth = 6

      val gameController = new GameController(gc)

      // Handle mouse events
      canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler[MouseEvent] {
        override def handle(e: MouseEvent): Unit = gameController.handleMouseDrag(e)
      })

      canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler[MouseEvent] {
        override def handle(e: MouseEvent): Unit = gameController.handleMouseRelease(e)
      })

      canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler[MouseEvent] {
        override def handle(e: MouseEvent): Unit = gameController.handleMousePress(e)
      })

      content = canvas

      // Start the game
      gameController.startGame()
    }
  }
}
