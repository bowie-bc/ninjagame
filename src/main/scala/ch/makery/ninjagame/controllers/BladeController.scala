package scala.ch.makery.ninjagame.controllers

import scala.ch.makery.ninjagame.entities._

class BladeController {
  var blade: Blade = Blade(Nil)

  def addBladePoint(x: Double, y: Double): Unit = {
    blade.addPoint(x, y)
  }
}
