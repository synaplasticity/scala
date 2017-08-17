package info.rewiring.basics

class DataTypes {

  def checkByte(data: Byte) : Unit = {
    val data : Byte = Byte.MaxValue

  }

  def checkFloat(data: Float) : Unit = {
    //val notAFloat: Float = 32.0 // Error, assumes double
    val aFloat: Float = 32.0F
  }

  def checkDataTypesAsObjects(): Unit = {
    val sum: Int = 1 + 2

    val sumObject = 1.+(2) // Everything "maybe an object". Depends on how you use it.

    val absVal = -5.abs // Instead of Math.abs(-5) in Java

    val aVal = -10.0

  }
}