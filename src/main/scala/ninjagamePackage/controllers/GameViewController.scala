package ninjagamePackage.controllers

import ninjagamePackage.MainApp
import ninjagamePackage.util.{GameLogic, SoundManager}
import javafx.animation.PauseTransition
import javafx.util.Duration
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.{Button, Label}
import scalafx.scene.input.MouseEvent
import scalafx.Includes._
import scalafx.application.Platform.runLater
import scalafx.scene.layout.StackPane
import scalafxml.core.macros.sfxml

@sfxml
class GameViewController(private val gameCanvas: Canvas,
                         private val scoreLabel: Label,
                         private val levelLabel: Label,
                         private val livesLabel: Label,
                         private val totalScoreLabel: Label,
                         private val totalLevelLabel: Label,
                         private val musicOnOffButton: Button,
                         private val gameOverOverlay: StackPane,
                         private val popupLabel: Label,
                         private val pauseOverlay: StackPane){

  //Initialize the GraphicsContext from Canvas, the game logic and other attributes
  private val gc = gameCanvas.graphicsContext2D
  private val gameLogic = new GameLogic(gc)
  private var popupShown = false
  private var musicPlaying = true

  //Handle mouse event of click, drag and release on the game canvas
  gameCanvas.onMousePressed = (mouse: MouseEvent) => gameLogic.updateBlade(mouse.x, mouse.y, isMouseDown = true)
  gameCanvas.onMouseDragged = (mouse: MouseEvent) => gameLogic.updateBlade(mouse.x, mouse.y, isMouseDown = true)
  gameCanvas.onMouseReleased = (mouse: MouseEvent) => gameLogic.updateBlade(mouse.x, mouse.y, isMouseDown = false)

  //Initialize and start the game
  startGame()

  //Method to start the game by initializing the startGameLoop() method in GameLogic with attributes such as background music sound and UI update
  private def startGame(): Unit = {
    gameLogic.startGameLoop()
    SoundManager.loopSound("/sounds/gamebgmusic.mp3")
    updateUI()
  }

  //Method to update UI including score, level and lives
  private def updateUI(): Unit = {
    scoreLabel.text = s"Score: ${gameLogic.score}"
    levelLabel.text = s"Level: ${gameLogic.level}"
    livesLabel.text = s"Lives Left: ${gameLogic.lives}"

    //Display pop up message for 5 seconds in regards to current maximum bomb
    if (gameLogic.level == 0 && !popupShown){
      displayPopupMessage(s"Random number of bombs from 0 to ${gameLogic.maxBomb} will be spawned!")

      //Set flag to prevent repeated calling of displayPopupMessage()
      popupShown = true
    }

    //Display pop up message for 5 seconds in regards to maximum bomb limit increases every 5 levels
    if ((gameLogic.level % 5 == 0) && (gameLogic.level != 0) && !popupShown) {
      displayPopupMessage(s"Maximum random number of bomb increased by 1, Maximum bombs: ${gameLogic.maxBomb}")

      //Set flag to prevent repeated calling of displayPopupMessage()
      popupShown = true
    }

    //Display game over overlay and handle sound effects when game is over
    if (gameLogic.gameOver) {
      gameOverOverlay.visible = true
      totalScoreLabel.text = s"Total Score Obtained is ${gameLogic.score}"
      totalLevelLabel.text = s"Total Levels Survived is ${gameLogic.level}"
      SoundManager.stopSound("/sounds/gamebgmusic.mp3")
      SoundManager.playSound("/sounds/gameover.mp3")
    }

    else {
      //Continue updating the UI
      runLater(updateUI())
    }
  }

  //Method to show popup message for 5 seconds
  private def displayPopupMessage(message: String): Unit = {
    popupLabel.text = message
    popupLabel.visible = true
    println("printing popup")

    //Create a PauseTransition that last 5 seconds to set visibility of pop up label off
    val delay = new PauseTransition(Duration.seconds(5))
    delay.setOnFinished {_ =>
      //Set visibility of pop up label to false
      popupLabel.visible = false
      //Reset flag after pop up label is hidden
      popupShown = false
    }
    delay.play()
  }

  //Method to handle turning on and off of music
  def handleMusicOnOff(): Unit = {
    if (musicPlaying) {
      SoundManager.stopSound("/sounds/gamebgmusic.mp3")
      musicOnOffButton.text = "Music On"
      musicPlaying = false
    }

    else {
      SoundManager.loopSound("/sounds/gamebgmusic.mp3")
      musicOnOffButton.text = "Music Off"
      musicPlaying = true
    }
  }

  //Method to handle the restarting of the game
  def handleRestart(): Unit = {
    MainApp.restartGame()
  }

  //Method to handle returning to start view
  def handleReturnToStart(): Unit = {
    MainApp.showStartView()
  }

  //Method to handle quitting of the game
  def handleQuit(): Unit = {
    MainApp.quitGame()
  }

  //Method to handle pausing of the game
  def handlePause(): Unit = {
    gameLogic.gamePaused = true
    pauseOverlay.visible = true
    SoundManager.stopSound("/sounds/gamebgmusic.mp3")
  }

  //Method to handle resume of the game
  def handleResume(): Unit = {
    gameLogic.gamePaused = false
    pauseOverlay.visible = false
    SoundManager.loopSound("/sounds/gamebgmusic.mp3")
    gameLogic.startGameLoop()
  }

}
