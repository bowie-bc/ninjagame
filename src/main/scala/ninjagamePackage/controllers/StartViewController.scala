package ninjagamePackage.controllers

import ninjagamePackage.MainApp
import ninjagamePackage.util.SoundManager
import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml

@sfxml
class StartViewController(private val musicOnOffButton: Button){

  //Initialize musicPlaying Boolean to on and off music
  private var musicPlaying = true

  //Initialize background music
  SoundManager.loopSound("/sounds/startbgmusic.mp3")

  //Method to handle turning on and off of music
  def handleMusicOnOff(): Unit = {
    if (musicPlaying) {
      SoundManager.stopSound("/sounds/startbgmusic.mp3")
      musicOnOffButton.text = "Music On"
      musicPlaying = false
    }

    else {
      SoundManager.loopSound("/sounds/startbgmusic.mp3")
      musicOnOffButton.text = "Music Off"
      musicPlaying = true
    }
  }

  //Method to handle starting of game to show game view
  def handleStart(): Unit = {
    SoundManager.stopSound("/sounds/startbgmusic.mp3")
    MainApp.showGameView()
  }

  //Method to handle showing of the rules by showing rules view
  def handleRules(): Unit = {
   MainApp.showRulesView()
  }

  //Method to handle quitting of the game
  def handleQuit(): Unit = {
    MainApp.quitGame()
  }

}
