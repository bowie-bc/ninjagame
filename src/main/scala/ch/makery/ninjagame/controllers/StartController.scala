package scala.ch.makery.ninjagame.controllers

import scalafx.scene.control.{Button, TextField}
import scalafxml.core.macros.sfxml
import javafx.event.ActionEvent

import scala.ch.makery.ninjagame.MainApp

@sfxml
class StartController(private val startButton: Button,
                      private val leaderboardButton: Button,
                      private val nicknameField: TextField
                     ) {

  def onStartButtonClick(event: ActionEvent): Unit = {
    MainApp.switchToGameScene()
//    val nickname = nicknameField.text.value.trim
//    if (nickname.nonEmpty) {
//
//    } else {
//      println("Nickname cannot be empty")
//    }
  }




}
