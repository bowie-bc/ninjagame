package scala.ch.makery.ninjagame.controllers

import scalafxml.core.macros.sfxml
import javafx.event.ActionEvent
import scalafx.scene.control.Button

import scala.ch.makery.ninjagame.MainApp
import scala.ch.makery.ninjagame.utilities.SoundManager

@sfxml
class StartController(private val onMusicButton: Button,
                      private val offMusicButton: Button) {

  SoundManager.loopSound("/sounds/startbackgroundmusic.mp3")

  def handleStart(event: ActionEvent): Unit = {
    MainApp.switchToGameView()
    SoundManager.stopSound("/sounds/startbackgroundmusic.mp3")
  }

  def handleHowToPlay(event: ActionEvent): Unit = {
    MainApp.switchToHowToPlayView()
  }

  def handleQuit(): Unit = {
    println("Quit button clicked")
    MainApp.quitGame()
  }

  def handleMusicOff(): Unit = {
    println("music off button clicked")
    SoundManager.stopSound("/sounds/startbackgroundmusic.mp3")
    onMusicButton.visible = true
    offMusicButton.visible = false
  }

  def handleMusicOn(): Unit = {
    println("music on button clicked")
    SoundManager.loopSound("/sounds/startbackgroundmusic.mp3")
    onMusicButton.visible = false
    offMusicButton.visible = true
  }
}
