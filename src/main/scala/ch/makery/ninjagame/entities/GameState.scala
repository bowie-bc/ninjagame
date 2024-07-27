package scala.ch.makery.ninjagame.entities

import scala.ch.makery.ninjagame.controllers.{FruitController, BombController}
import scalafx.scene.canvas.GraphicsContext
import scala.ch.makery.ninjagame.utilities.SoundManager
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight}

class GameState(val gc: GraphicsContext) {
  var fruits: List[Fruit] = List()
  var bombs: List[Bomb] = List()
  var blade: Blade = Blade(Nil)
  var score: Int = 0
  var gameOver: Boolean = false
  var level: Int = 0
  var lives: Int = 3

  def update(mouseX: Double, mouseY: Double, isMouseDown: Boolean): Unit = {
    if (gameOver) return

    if (isMouseDown) {
      blade = blade.addPoint(mouseX, mouseY)
    } else {
      blade = Blade(Nil)
    }

    checkCollisions()
  }

  def updateEntities(): Unit = {
    if (gameOver) return

    moveEntities()

    if (fruits.isEmpty && bombs.isEmpty && !gameOver) {
      nextLevel()
    }
  }

  def moveEntities(): Unit = {
    fruits.foreach(_.move())
    fruits = fruits.filterNot { fruit =>
      if (fruit.y > 600) {
        lives -= 1
        println(s"Missed a fruit! Lives left: $lives")
        false
      } else {
        true
      }
    }
    bombs.foreach(_.move())
    bombs = bombs.filterNot(_.y > 600) // Remove bombs that go off-screen
    if (lives <= 0) {
      gameOver = true
      println("No lives left! Game over.")
    }
  }

  def checkCollisions(): Unit = {
    fruits = fruits.filterNot { fruit =>
      if (fruit.isSliced(blade.points.map(_._1), blade.points.map(_._2))) {
        score += fruit.points
        SoundManager.playSound("/slice.mp3")
        println(s"Sliced a fruit worth ${fruit.points} points! Score: $score")
        true
      } else {
        false
      }
    }

    bombs = bombs.filterNot { bomb =>
      if (blade.points.exists { case (x, y) => bomb.isHit(x, y) }) {
        SoundManager.playSound("/explosion.mp3")
        println("Hit a bomb! Game over.")
        gameOver = true
        true
      } else {
        false
      }
    }
  }

  def addFruit(fruit: Fruit): Unit = {
    println(s"Adding fruit at (${fruit.x}, ${fruit.y})")
    fruits = fruit :: fruits
  }

  def addBomb(bomb: Bomb): Unit = {
    println(s"Adding bomb at (${bomb.x}, ${bomb.y})")
    bombs = bomb :: bombs
  }

  def drawEntities(): Unit = {
    gc.clearRect(0, 0, gc.canvas.getWidth, gc.canvas.getHeight)

    blade.draw(gc)

    fruits.foreach { fruit =>
      gc.fill = fruit.color
      gc.fillRect(fruit.x, fruit.y, fruit.width, fruit.height)
      println(s"Drawing fruit at (${fruit.x}, ${fruit.y}) with color ${fruit.color}")
    }

    bombs.foreach { bomb =>
      gc.fill = bomb.color
      gc.fillRect(bomb.x, bomb.y, bomb.width, bomb.height)
      println(s"Drawing bomb at (${bomb.x}, ${bomb.y}) with color ${bomb.color}")
    }

    drawScore()
    drawLives()

    if (gameOver) {
      drawGameOver()
    }
  }

  def drawScore(): Unit = {
    gc.setFont(Font.font("Arial", FontWeight.Bold, 36))
    gc.setFill(Color.Black)
    gc.fillText(s"Score: $score", 10, 40)
  }

  def drawLives(): Unit = {
    val heartRadius = 15
    for (i <- 0 until lives) {
      gc.setFill(Color.Red)
      gc.fillOval(700 + i * (heartRadius * 2 + 10), 10, heartRadius * 2, heartRadius * 2)
    }
  }

  def drawGameOver(): Unit = {
    gc.setFont(Font.font("Arial", FontWeight.Bold, 72))
    gc.setFill(Color.Red)
    gc.fillText("Game Over", 200, 300)
  }

  def nextLevel(): Unit = {
    level += 1
    println(s"Starting level $level")

    // Clear existing fruits and bombs
    fruits = List()
    bombs = List()

    val fruitsToSpawn = 3 + level // Start with 3 fruits, increase by 1 each level
    println(s"Spawning $fruitsToSpawn fruits for level $level")
    for (_ <- 1 to fruitsToSpawn) {
      addFruit(FruitController.createFruit())
    }
    // Add a bomb only every second level, but not on the first level
    if (level > 1 && level % 2 == 0) {
      println("Spawning a bomb for this level")
      addBomb(BombController.createBomb())
    }
  }
}
