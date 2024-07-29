package scala.ch.makery.ninjagame.utilities

import scalafx.scene.media.AudioClip

object SoundManager {
  private def getResourcePath(resource: String): String = {
    getClass.getResource(resource).toString
  }

  def playSound(soundFile: String): Unit = {
    try {
      val sound = new AudioClip(getResourcePath(soundFile))
      sound.play()
    } catch {
      case e: Exception => println(s"Failed to play sound $soundFile: ${e.getMessage}")
    }
  }

  def loopSound(soundFile: String): Unit = {
    try {
      val sound = new AudioClip(getResourcePath(soundFile))
      sound.cycleCount = AudioClip.Indefinite
      sound.play()
    } catch {
      case e: Exception => println(s"Failed to loop sound $soundFile: ${e.getMessage}")
    }
  }

  def stopSound(soundFile: String): Unit = {
    try {
      val sound = new AudioClip(getResourcePath(soundFile))
      sound.stop()
    } catch {
      case e: Exception => println(s"Failed to stop sound $soundFile: ${e.getMessage}")
    }
  }
}
