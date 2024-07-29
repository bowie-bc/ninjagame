//package scala.ch.makery.ninjagame.utilities
//
//import scalafx.scene.media.AudioClip
//
//object SoundManager {
//  private def getResourcePath(resource: String): String = {
//    getClass.getResource(resource).toString
//  }
//
//  def playSound(soundFile: String): Unit = {
//    try {
//      val sound = new AudioClip(getResourcePath(soundFile))
//      sound.play()
//    } catch {
//      case e: Exception => println(s"Failed to play sound $soundFile: ${e.getMessage}")
//    }
//  }
//
//  def loopSound(soundFile: String): Unit = {
//    try {
//      val sound = new AudioClip(getResourcePath(soundFile))
//      sound.cycleCount = AudioClip.Indefinite
//      sound.play()
//    } catch {
//      case e: Exception => println(s"Failed to loop sound $soundFile: ${e.getMessage}")
//    }
//  }
//
//  def stopSound(soundFile: String): Unit = {
//    try {
//      val sound = new AudioClip(getResourcePath(soundFile))
//      sound.stop()
//    } catch {
//      case e: Exception => println(s"Failed to stop sound $soundFile: ${e.getMessage}")
//    }
//  }
//}
package scala.ch.makery.ninjagame.utilities

import scalafx.scene.media.AudioClip
import scala.collection.mutable

object SoundManager {
  private def getResourcePath(resource: String): String = {
    getClass.getResource(resource).toString
  }

  private val activeSounds = mutable.Map[String, AudioClip]()

  def playSound(soundFile: String): Unit = {
    try {
      val sound = new AudioClip(getResourcePath(soundFile))
      sound.play()
      println(s"Playing sound: $soundFile")
    } catch {
      case e: Exception => println(s"Failed to play sound $soundFile: ${e.getMessage}")
    }
  }

  def loopSound(soundFile: String): Unit = {
    try {
      val sound = activeSounds.getOrElseUpdate(soundFile, new AudioClip(getResourcePath(soundFile)))
      if (!sound.isPlaying) {
        sound.cycleCount = AudioClip.Indefinite
        sound.play()
        println(s"Looping sound: $soundFile")
      } else {
        println(s"Sound already looping: $soundFile")
      }
    } catch {
      case e: Exception => println(s"Failed to loop sound $soundFile: ${e.getMessage}")
    }
  }

  def stopSound(soundFile: String): Unit = {
    try {
      activeSounds.get(soundFile).foreach { sound =>
        sound.stop()
        println(s"Stopped sound: $soundFile")
      }
      activeSounds -= soundFile
    } catch {
      case e: Exception => println(s"Failed to stop sound $soundFile: ${e.getMessage}")
    }
  }
}
