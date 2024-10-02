package ninjagamePackage

import javafx.{scene => jfxs}
import scalafx.application.{JFXApp, Platform}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

object MainApp extends JFXApp {
  //Initialize the Primary Stage for application
  stage = new PrimaryStage {
    //Set stage size (width x height)
    width = 1024
    height = 768

    //Restrict resizing of stage (application)
    resizable = false

    //Handle closing of application when close button (X) is clicked
    onCloseRequest = handle {
      quitGame()
    }
  }

  //Method to load a specific FXML view and set it as current scene
  private def loadView(resourcePath: String, title: String): Unit = {
    val resource = getClass.getResource(resourcePath)
    if (resource == null){
      println(s"$resourcePath resource not found")
      throw new RuntimeException(s"Cannot load FXML resource: $resourcePath")
    }

    //Load the FXML file using FXMLLoader
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()

    //Set the loaded FXML root as the root node of a new scene
    val root = loader.getRoot[jfxs.layout.AnchorPane]
    val scene = new Scene(root)

    //Set the new scene and title to the stage
    stage.scene = scene
    stage.title = title

    println(s"$title set")
  }

  //Method to show the start view of the game
  def showStartView(): Unit = {
    println("Show start view")
    loadView("/ninjagamePackage/view/StartView.fxml", "Ninja Game: Start")
  }

  //Method to show the rules view of the game
  def showRulesView(): Unit = {
    println("Show rules view")
    loadView("/ninjagamePackage/view/RulesView.fxml", "Ninja Game: Rules")
  }

  //Method to show the game view of the game
  def showGameView(): Unit = {
    println("Show game view")
    loadView("/ninjagamePackage/view/GameView.fxml", "Ninja Game: Game")
  }

  //Method to quit the game application
  def quitGame(): Unit = {
    println("Quitting game")
    Platform.exit()
    System.exit(0)
  }

  //Method to restart the game for button in game over overlay
  def restartGame(): Unit = {
    println("Restarting game")
    showGameView()
  }

  //Initially show start view when application starts
  showStartView()

}