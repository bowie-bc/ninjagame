package ninjagamePackage.util

import scala.collection.mutable
import scalafx.scene.media.AudioClip

//Singleton object for managing sound effects
object SoundManager {
  //Method to get resource path of sound file
  private def getResourcePath(resourcePath: String ): String = {
    getClass.getResource(resourcePath).toString
  }

  //Mutable map to keep track of currently active playing/looping sounds
  private val activeSounds = mutable.Map[String, AudioClip]()

  //Method to play the sound once
  def playSound(soundFilePath: String): Unit = {
    try {
      //Create a new AudioClip for the sound file
      val sound = new AudioClip(getResourcePath(soundFilePath))

      //Play AudioClip
      sound.play()
      println(s"Playing sound $soundFilePath")
    }

    catch {
      case e:Exception =>
        println(s"Failed to play the sound $soundFilePath: ${e.getMessage}")
    }
  }

  //Method to loop the sound
  def loopSound(soundFilePath: String): Unit = {
    try {
      //Check if no sound is currently playing
      if (activeSounds.isEmpty) {
        //Create a new AudioClip for the sound file
        val sound = new AudioClip(getResourcePath(soundFilePath))

        //Set the cycle count of AudioClip sound to indefinite
        sound.cycleCount = AudioClip.Indefinite

        //Play AudioClip sound
        sound.play()
        println(s"Looping sound $soundFilePath")

        //Add looping AudioClip sound to activeSounds map
        activeSounds(soundFilePath) = sound
      }

      else {
        println(s"Cannot loop $soundFilePath because the same sound file is already looping")
      }
    }

    catch {
      case e:Exception =>
        println(s"Failed to loop the sound $soundFilePath: ${e.getMessage}")
    }
  }

  //Method to stop the sound
  def stopSound(soundFilePath: String): Unit = {
    try {
      //If soundFile of AudioClip sound is in activeSounds, stop and remove it from the activeSounds map
      activeSounds.get(soundFilePath).foreach { sound =>
        //Stop AudioClip
        sound.stop()
        println(s"Stopping sound $soundFilePath")
        activeSounds -= soundFilePath
      }
    }

    catch {
      case e:Exception =>
        println(s"Failed to stop the sound $soundFilePath: ${e.getMessage}")
    }
  }

}