package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities.GameState
import scalafx.scene.canvas.GraphicsContext
import javafx.scene.input.MouseEvent
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GameController(val gc: GraphicsContext) {
  var gameState = new GameState(gc)

  def handleMouseDrag(e: MouseEvent): Unit = {
    gameState.update(e.getX, e.getY, isMouseDown = true)
  }

  def handleMouseRelease(e: MouseEvent): Unit = {
    gameState.update(e.getX, e.getY, isMouseDown = false)
  }

  def handleMousePress(e: MouseEvent): Unit = {
    gameState.update(e.getX, e.getY, isMouseDown = true)
  }

  def startGameLoop(): Unit = {
    Future {
      while (!gameState.gameOver) {
        Thread.sleep(30)
        gameState.updateEntities()
        gameState.drawEntities()
      }
      gameState.drawEntities() // Final draw to display game over screen
    }
  }

  def startGame(): Unit = {
    gameState.nextLevel()
    startGameLoop()
  }
}

