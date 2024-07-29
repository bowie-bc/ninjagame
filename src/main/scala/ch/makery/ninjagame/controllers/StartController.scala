package scala.ch.makery.ninjagame.controllers

import scalafx.scene.control.{Button, TextField}
import scalafxml.core.macros.sfxml
import javafx.event.ActionEvent

import scala.ch.makery.ninjagame.MainApp
import scala.ch.makery.ninjagame.utilities.SoundManager

@sfxml
class StartController(private val startButton: Button,
                      private val leaderboardButton: Button,
                      private val nicknameField: TextField
                     ) {

  SoundManager.loopSound("/sounds/startbackgroundmusic.mp3")

  def handleStart(event: ActionEvent): Unit = {
    MainApp.switchToGameScene()
    SoundManager.stopSound("/sounds/startbackgroundmusic.mp3")
  }

  def handleQuit(): Unit = {
    println("Quit button clicked")
    MainApp.quitGame()
  }




}
