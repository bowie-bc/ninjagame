package scala.ch.makery.ninjagame

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.scene.layout.AnchorPane
import java.net.URL
import javafx.{scene => jfxs}

object MainApp extends JFXApp {

  val startResource: URL = getClass.getResource("/StartView.fxml")
  if (startResource == null) {
    throw new RuntimeException("Cannot load FXML resource")
  }

  val startLoader = new FXMLLoader(startResource, NoDependencyResolver)
  startLoader.load()
  println("Start FXML loaded successfully")

  val startRoot = startLoader.getRoot[jfxs.layout.AnchorPane]
  val startScene = new Scene(startRoot)

  stage = new PrimaryStage {
    title = "Fruit Ninja"
    scene = startScene
  }

  def switchToGameScene(): Unit = {
    println("Switching to game scene")
    val gameResource: URL = getClass.getResource("/GameView.fxml")
    if (gameResource == null) {
      throw new RuntimeException("Cannot load FXML resource")
    }

    val gameLoader = new FXMLLoader(gameResource, NoDependencyResolver)
    gameLoader.load()
    println("Game FXML loaded successfully")

    val gameRoot = gameLoader.getRoot[jfxs.layout.AnchorPane]
    println(s"Game Root: $gameRoot") // Debug statement to ensure root is loaded
    val gameScene = new Scene(gameRoot)
    println("Game scene created")

    stage.scene = gameScene
    println("Game scene set")
  }
}
