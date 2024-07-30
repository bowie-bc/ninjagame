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

  def loadView(resourcePath: String, title: String): Unit = {
    val resource: URL = getClass.getResource(resourcePath)
    if (resource == null) {
      println(s"$resourcePath resource not found")
      throw new RuntimeException(s"Cannot load FXML resource: $resourcePath")
    }

    val loader = new FXMLLoader(resource, NoDependencyResolver)
    try {
      loader.load()
    } catch {
      case e: Exception =>
        println(s"Failed to load $resourcePath: ${e.getMessage}")
        throw e
    }

    val root = loader.getRoot[jfxs.layout.AnchorPane]
    val scene = new Scene(root)
    stage.scene = scene
    stage.title = title
    println(s"$title set")
  }

  def switchToStartView(): Unit = {
    println("Switching to start scene")
    loadView("/ch.makery.ninjagame.view/StartView.fxml", "Ninja Game: Start View")
  }

  def switchToGameView(): Unit = {
    println("Switching to game scene")
    loadView("/ch.makery.ninjagame.view/GameView.fxml", "Ninja Game: Game View")
  }

  def switchToHowToPlayView(): Unit = {
    println("Switching to how to play scene")
    loadView("/ch.makery.ninjagame.view/HowToPlayView.fxml", "Ninja Game: How To Play View")
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
