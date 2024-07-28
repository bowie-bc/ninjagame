package scala.ch.makery.ninjagame.controllers

import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml
import javafx.event.ActionEvent
import scala.ch.makery.ninjagame.MainApp



@sfxml
class StartController(private val startButton: Button) {

  def onStartButtonClick(event: ActionEvent): Unit = {
    println("Start button clicked")
    MainApp.switchToGameScene()
  }




}
