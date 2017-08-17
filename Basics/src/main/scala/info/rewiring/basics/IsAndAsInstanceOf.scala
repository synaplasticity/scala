package info.rewiring.basics

class IsAndAsInstanceOf {

  def decide(x: Any) = if(x.isInstanceOf[Int]) x.asInstanceOf[Int] + 1 else -1

}

