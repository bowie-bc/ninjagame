package scala.ch.makery.ninjagame.entities

import scala.ch.makery.ninjagame.controllers.{BombController, FruitController}
import scalafx.scene.canvas.GraphicsContext

import scala.ch.makery.ninjagame.utilities.SoundManager
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight}

import scala.util.Random
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


import scala.ch.makery.ninjagame.MainApp


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


  def checkCollisions(): Unit = {
    fruits = fruits.filterNot { fruit =>
      if (fruit.slice(blade.points.map(_._1), blade.points.map(_._2))) {
        score += fruit.points
        SoundManager.playSound("/sounds/slice.mp3")
        println(s"Sliced a fruit worth ${fruit.points} points! Score: $score")
        false // Do not remove the fruit from the list to keep drawing the sliced image
      } else {
        false
      }
    }

    bombs = bombs.filterNot { bomb =>
      if (blade.points.exists { case (x, y) => bomb.isHit(x, y) }) {
        bomb.slice()
        SoundManager.playSound("/sounds/explosion.mp3")
        println("Hit a bomb! Game over.")
        gameOver = true
        false // Do not remove the bomb from the list to keep drawing the bombed image
      } else {
        false
      }
    }
  }

  def updateEntities(): Unit = {
    if (gameOver) return

    moveEntities()

    if (fruits.isEmpty && bombs.isEmpty && !gameOver) {
      println(s"Fruits and bombs are empty. All fruits sliced: ${allFruitsSliced}")
      if (allFruitsSliced) {
        nextLevel()
      } else {
        gameOver = true
        println("Game over. Some fruits were missed.")
      }
    }
  }

  def moveEntities(): Unit = {
    fruits.foreach(_.move())
    fruits = fruits.filterNot { fruit =>
      val offScreen = fruit.y > 600
      if (offScreen && !fruit.isSliced) {
        lives -= 1
        println(s"Missed a fruit! Lives left: $lives")
      }
      offScreen
    }
    bombs.foreach(_.move())
    bombs = bombs.filterNot(_.y > 600)
    if (lives <= 0) {
      gameOver = true
      println("No lives left! Game over.")
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

    fruits.foreach(_.draw(gc))

    bombs.foreach(_.draw(gc))
  }

  def allFruitsSliced: Boolean = {
    fruits.isEmpty
  }

  def nextLevel(): Unit = {
    level += 1
    println(s"Starting level $level")

    // Clear existing fruits and bombs
    fruits = List()
    bombs = List()

    val fruitsToSpawn = Random.nextInt(8) + 3 // Random number of fruits between 3 and 10
    println(s"Spawning $fruitsToSpawn fruits for level $level")
    for (_ <- 1 to fruitsToSpawn) {
      addFruit(FruitController.createFruit())
    }

    // Add a random number of bombs (up to 3) for each level along with fruits
    val bombsToSpawn = Random.nextInt(3) + 1 // Random number of bombs between 1 and 3
    println(s"Spawning $bombsToSpawn bombs for this level")
    BombController.createBombs(bombsToSpawn).foreach(addBomb)
  }

  def startGameLoop(): Unit = {
    println("Starting game loop")
    Future {
      while (!gameOver) {
        Thread.sleep(30)
        updateEntities()
        drawEntities()
      }
      println("Game loop ended. Drawing final state.")
      drawEntities() // Final draw to display game over screen
    }
  }
}
