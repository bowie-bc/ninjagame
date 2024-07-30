//package scala.ch.makery.ninjagame.controllers
//
//import scalafx.scene.control.{Button, TextField}
//import scalafxml.core.macros.sfxml
//import javafx.event.ActionEvent
//
//import scala.ch.makery.ninjagame.MainApp
//import scala.ch.makery.ninjagame.utilities.SoundManager
//
//@sfxml
//class DifficultyController() {
//
//
//  def handleStart(event: ActionEvent): Unit = {
//
//  }
//
//  def handleEasy(): Unit = {
//    println("Selected Easy difficulty")
//    SoundManager.stopSound("/sounds/startbackgroundmusic.mp3")
//    MainApp.switchToGameView("Easy")
//  }
//
//  def handleMedium(): Unit = {
//    println("Selected Medium difficulty")
//    MainApp.selectedDifficulty = Difficulty.Medium
//    SoundManager.stopSound("/sounds/startbackgroundmusic.mp3")
//    MainApp.switchToGameView()
//  }
//
//  def handleHard(): Unit = {
//    println("Selected Hard difficulty")
//    MainApp.selectedDifficulty = Difficulty.Hard
//    SoundManager.stopSound("/sounds/startbackgroundmusic.mp3")
//    MainApp.switchToGameView()
//  }
//
//
//
//
//
//
//}
