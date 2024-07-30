package scala.ch.makery.ninjagame.controllers

import scalafxml.core.macros.sfxml
import javafx.event.ActionEvent
import scalafx.scene.control.Button

import scala.ch.makery.ninjagame.MainApp
import scala.ch.makery.ninjagame.utilities.SoundManager

@sfxml
class HowToPlayController() {

  def handleBack(event: ActionEvent): Unit = {
    MainApp.switchToStartView()
  }

}
