package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.MainApp
import scala.ch.makery.ninjagame.entities.GameState
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.{Button, Label}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.StackPane
import scalafx.Includes._
import scalafxml.core.macros.sfxml

import scala.ch.makery.ninjagame.utilities.SoundManager

@sfxml
class GameController(private val gameCanvas: Canvas,
                     private val scoreLabel: Label,
                     private val livesLabel: Label,
                     private val levelLabel: Label,
                     private val gameOverPane: StackPane) {

  private var gc = gameCanvas.graphicsContext2D
  private var gameState = new GameState(gc)

  // Handle mouse events
  gameCanvas.onMouseDragged = (e: MouseEvent) => gameState.update(e.x, e.y, isMouseDown = true)
  gameCanvas.onMouseReleased = (e: MouseEvent) => gameState.update(e.x, e.y, isMouseDown = false)
  gameCanvas.onMousePressed = (e: MouseEvent) => gameState.update(e.x, e.y, isMouseDown = true)

  // Start the game
  startGame()

  private def startGame(): Unit = {
    println("Starting game")
    println("playing music")
    SoundManager.loopSound("/sounds/backgroundmusic.mp3")
    gameState.startGameLoop()
    updateUI()
  }

  private def updateUI(): Unit = {
    scoreLabel.text = s"Score: ${gameState.score}"
    livesLabel.text = s"Lives: ${gameState.lives}"
    levelLabel.text = s"Level: ${gameState.level}"

    if (!gameState.gameOver) {
      javafx.application.Platform.runLater(() => updateUI())
    } else {
      javafx.application.Platform.runLater(() => updateUI())
      showGameOverOverlay()
      SoundManager.stopSound("/sounds/backgroundmusic.mp3")
    }
  }

  private def showGameOverOverlay(): Unit = {
    gameOverPane.visible = true
  }

  def handleRestart(): Unit = {
    println("Restart button clicked")
    MainApp.restartGame()
  }

  def handleQuit(): Unit = {
    println("Quit button clicked")
    MainApp.quitGame()
  }

  def handleQuitToStart(): Unit = {
    println("Quit to start button clicked")
    MainApp.quitToStart()
  }

  def handleMusicOff(): Unit = {
    println("music off button clicked")
    SoundManager.stopSound("/sounds/backgroundmusic.mp3")
  }

  def handleMusicOn(): Unit = {
    println("music on button clicked")
    SoundManager.playSound("/sounds/backgroundmusic.mp3")
  }
}
