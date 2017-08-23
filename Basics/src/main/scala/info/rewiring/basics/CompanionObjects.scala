package info.rewiring.basics

case class SecretAgents(name: String) {

  def shoot(bulletsShot: Int): Int = {
    import SecretAgents._

    decreaseBullets(bulletsShot) // method from the companion object
  }

}

/**
  * Companion object have the same name as the class
  *
  * This object is of course a singleton and shared by *ALL instances*
  * of created SecretAgent objects
  *
  */
object SecretAgents {
  private var totalBullets: Int = 3000

  private def decreaseBullets(count: Int): Int = {
    if (totalBullets - count == 0) totalBullets = 0
    else totalBullets = totalBullets - count

    totalBullets
  }

  def bullets: Int = totalBullets
}