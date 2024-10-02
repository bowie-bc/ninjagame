package ninjagamePackage.controllers

import ninjagamePackage.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class RulesViewController {

  //Method to handle returning back to start view
  def handleBack(): Unit = {
    MainApp.showStartView()
  }

}