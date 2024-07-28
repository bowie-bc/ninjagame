package scala.ch.makery.ninjagame.controllers

import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Label
import scalafx.scene.input.MouseEvent
import scalafx.scene.canvas.GraphicsContext
import scalafx.Includes._
import scala.ch.makery.ninjagame.entities.GameState
import scalafxml.core.macros.sfxml

@sfxml
class GameController(private val gameCanvas: Canvas,
                     private val scoreLabel: Label,
                     private val livesLabel: Label,
                     private val levelLabel: Label) {

  private var gc: GraphicsContext = _
  private var gameState: GameState = _


  println("GameController initialized")
  gc = gameCanvas.graphicsContext2D
  gameState = new GameState(gc)

  // Handle mouse events
  gameCanvas.onMouseDragged = (e: MouseEvent) => gameState.update(e.x, e.y, isMouseDown = true)
  gameCanvas.onMouseReleased = (e: MouseEvent) => gameState.update(e.x, e.y, isMouseDown = false)
  gameCanvas.onMousePressed = (e: MouseEvent) => gameState.update(e.x, e.y, isMouseDown = true)

  // Start the game
  startGame()

  private def startGame(): Unit = {
    println("Starting game")
    gameState.startGameLoop()
    updateUI()
  }

  private def updateUI(): Unit = {
    scoreLabel.text = s"Score: ${gameState.score}"
    livesLabel.text = s"Lives: ${gameState.lives}"
    levelLabel.text = s"Level: ${gameState.level}"

    if (!gameState.gameOver) {
      javafx.application.Platform.runLater(() => updateUI())
    }
  }
}
