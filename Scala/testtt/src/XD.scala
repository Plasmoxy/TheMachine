


object XD {

  class A {
    def <<(that: A): Unit =  {
      println("XD")
    }
  }

  def main(args: Array[String]): Unit = {
    new A << new A
  }

}