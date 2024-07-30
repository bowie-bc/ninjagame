package scala.ch.makery.ninjagame

import scalafx.application.{JFXApp, Platform}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import java.net.URL
import javafx.{scene => jfxs}

object MainApp extends JFXApp {

  //Initialize the primary stage
  stage = new PrimaryStage {
    onCloseRequest = handle {
      quitGame()
    }
  }

  def switchToStartView(): Unit = {
    println("Switching to startscene")
    val startResource: URL = getClass.getResource("/ch.makery.ninjagame.view/StartView.fxml")
    if (startResource == null) {
      throw new RuntimeException("Cannot load FXML resource")
    }

    val startLoader = new FXMLLoader(startResource, NoDependencyResolver)
    startLoader.load()
    println("Game FXML loaded successfully")

    val startRoot = startLoader.getRoot[jfxs.layout.AnchorPane]
    val startScene = new Scene(startRoot)
    println("Start scene created")

    stage.scene = startScene
    stage.title = "Ninja Game: Start View"
    println("Start scene set")
  }

  def switchToGameView(): Unit = {
    println("Switching to game scene")
    val gameResource: URL = getClass.getResource("/ch.makery.ninjagame.view/GameView.fxml")
    if (gameResource == null) {
      throw new RuntimeException("Cannot load FXML resource")
    }

    val gameLoader = new FXMLLoader(gameResource, NoDependencyResolver)
    gameLoader.load()
    println("Game View loaded successfully")

    val gameRoot = gameLoader.getRoot[jfxs.layout.AnchorPane]
    println(s"Game Root: $gameRoot") // Debug statement to ensure root is loaded
    val gameScene = new Scene(gameRoot)
    println("Game scene created")

    stage.scene = gameScene
    stage.title = "Ninja Game: Game View"
    println("Game scene set")
  }

  def restartGame(): Unit = {
    switchToGameView()
    println("Game restarted")
  }

  def quitToStart(): Unit = {
    switchToStartView()
    println("Quit to start")
  }

  def quitGame(): Unit = {
    println("Game quit")
    Platform.exit()
    System.exit(0)

  }
  //Initially loaded
  switchToStartView()

}
