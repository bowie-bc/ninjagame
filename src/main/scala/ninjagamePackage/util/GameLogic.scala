package ninjagamePackage.util

import ninjagamePackage.entities._
import javafx.animation.AnimationTimer
import scala.util.Random
import scalafx.scene.canvas.GraphicsContext

class GameLogic (val gc: GraphicsContext){
  //Initialize game variables
  private var blade: Blade = Blade(Nil)
  private var fruits: List[Fruit] = List()
  private var bombs: List[Bomb] = List()
  var level: Int = 0
  var maxBomb: Int = 1
  var lives: Int = 3
  var score: Int = 0
  var gameOver: Boolean = false
  var gamePaused: Boolean = false

  //Define AnimationTimer for the game loop
  private val gameLoop = new AnimationTimer() {
    override def handle(now: Long): Unit = {
      if (!gameOver && !gamePaused) {
        moveEntities()
        drawEntities()
        checkLevelCompletion()
      }

      else {
        stop()
      }
    }
  }

  //Method to start game loop
  def startGameLoop(): Unit = {
    gameLoop.start()
  }

  //Method to update blade's trail position based on mouse movement and event
  def updateBlade(mouseX: Double, mouseY: Double, isMouseDown: Boolean): Unit = {
    if (gameOver) return

    if(isMouseDown) {
      blade = blade.addPoint(mouseX, mouseY)
    }

    else {
      //Clear blade's trail points when mouse is not down
      blade = Blade(Nil)
    }

    checkCollisions()
  }

  //Method to check for collisions between blade and game entities
  private def checkCollisions(): Unit = {
    //Check for collisions of blade's trail points with fruits
    fruits = fruits.filterNot{ fruit =>
      if (fruit.slice(blade.bladePoints)){
        score += fruit.points

        //Play slice sound effect
        SoundManager.playSound("/sounds/slice.mp3")

        println(s"Sliced a fruit worth ${fruit.points} points. Score: $score ")

        //Keep the sliced fruit in the game after it is sliced
        false
      }

      else {
        false
      }
    }

    //Check for collisions of blade's trail points with fruits
    bombs = bombs.filterNot { bomb =>
      if (bomb.slice(blade.bladePoints)){
        //Update to sliced bomb image
        bomb.draw(gc)

        //Play explosion sound effect
        SoundManager.playSound("/sounds/explosion.mp3")

        //Update game state to be over
        gameOver = true

        println("Bomb sliced. Game over")

        //Keep the sliced bomb in the game after it is sliced
        false
      }

      else {
        false
      }
    }
  }

  //Method to advance to the next level, spawning new fruits and bombs
  private def nextLevel(): Unit = {
    level += 1

    //Clear existing fruits and bombs in the list respectively
    fruits = List()
    bombs = List()

    //Generate random number of fruits to spawn in range of 1 to 10
    val fruitsToSpawn = Random.nextInt(9) + 1
    println(s"Fruits to spawn for level $level is $fruitsToSpawn")

    //Add the new fruits to the game
    (1 to fruitsToSpawn).foreach(_ =>
      addFruit(Fruit.createFruit()))

    //Increase value of maxBomb every 5 levels
    if ((level % 5) == 0) maxBomb +=1

    //Generate random number of bombs to spawn in range of 0 to maxBomb
    val bombsToSpawn = Random.nextInt(maxBomb + 1)
    println(s"Bombs to spawn for level $level is $bombsToSpawn")

    //Add the new bombs to the game if needed
    if (bombsToSpawn > 0) {
      //Iterate from 1 to value of bombsToSpawn and for each to create a new fruit using FruitController and add to the game
      (1 to bombsToSpawn).foreach(_ =>
        addBomb(Bomb.createBomb()))
    }
  }

  //Helper method to add a new fruit to fruits list to the game
  private def addFruit(fruit: Fruit): Unit = {
    fruits = fruit :: fruits
  }

  //Helper method to add a new bomb to bombs list to the game
  private def addBomb(bomb: Bomb): Unit = {
    bombs = bomb :: bombs
  }

  //Method to draw entities such as blade's trail, fruits and bombs
  private def drawEntities(): Unit = {
    gc.clearRect(0, 0, gc.canvas.getWidth, gc.canvas.getHeight)
    blade.draw(gc)
    fruits.foreach(_.draw(gc))
    bombs.foreach(_.draw(gc))
  }

  //Method to move all entities
  private def moveEntities(): Unit = {
    fruits.foreach(_.move())
    bombs.foreach(_.move())

    //Handle offScreen fruits and deduct lives if fruit is not sliced
    fruits = fruits.filterNot(handleOffScreenFruit)

    //Remove offScreen bombs
    bombs = bombs.filterNot(_.posY > 768)

    //Check if game should be over
    checkGameOver()
  }

  //Helper method to handle offScreen fruits and deduct lives
  private def handleOffScreenFruit(fruit: Fruit): Boolean = {
    val offScreen = fruit.posY > 768
    if (offScreen && !fruit.isSliced) {
      lives -= 1

      //Play sound effect for 1 life lost
      SoundManager.playSound("/sounds/lifelost.mp3")

      println(s"Missed a fruit. Lives left: $lives")
    }
    //Return Boolean of whether fruit is offscreen
    offScreen
  }

  //Helper method to check if game should be over
  private def checkGameOver(): Unit = {
    if (lives == 0) {
      gameOver = true

      println("No lives left. Game over")
    }
  }

  //Helper method to check level completion
  private def checkLevelCompletion(): Unit = {
    if (fruits.isEmpty && bombs.isEmpty && lives != 0) {
      nextLevel()
    }

  }

}