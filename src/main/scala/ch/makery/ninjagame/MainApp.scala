package scala.ch.makery.ninjagame

import scalafx.application.{JFXApp, Platform}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.scene.layout.{AnchorPane, StackPane}

import java.net.URL
import javafx.{scene => jfxs}

import scala.ch.makery.ninjagame.utilities.SoundManager


object MainApp extends JFXApp {

  val startResource: URL = getClass.getResource("/ch.makery.ninjagame.view/StartView.fxml")
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



    onCloseRequest = handle {
      Platform.exit()
      System.exit(0)
    }
  }

  def switchToGameScene(): Unit = {
    println("Switching to game scene")
    val gameResource: URL = getClass.getResource("/ch.makery.ninjagame.view/GameView.fxml")
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

  def switchToStartScene(): Unit = {
    println("Switching to startscene")
    val startResource: URL = getClass.getResource("/ch.makery.ninjagame.view/StartView.fxml")
    if (startResource == null) {
      throw new RuntimeException("Cannot load FXML resource")
    }

    val startLoader = new FXMLLoader(startResource, NoDependencyResolver)
    startLoader.load()
    println("Game FXML loaded successfully")

    val startRoot = startLoader.getRoot[jfxs.layout.AnchorPane]
    println(s"Start Root: $startRoot") // Debug statement to ensure root is loaded
    val startScene = new Scene(startRoot)
    println("Start scene created")

    stage.scene = startScene
    println("Start scene set")
  }

  def restartGame(): Unit = {
    switchToGameScene()
    println("Game restarted")
  }

  def quitToStart(): Unit = {
    switchToStartScene()
    println("Quit to start")
  }

  def quitGame(): Unit = {
    Platform.exit()
    System.exit(0)
    println("Game quit")
  }

}
