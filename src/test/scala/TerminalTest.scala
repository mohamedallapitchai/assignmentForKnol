import org.scalatest._

class TerminalTest extends FlatSpec with BeforeAndAfter {
  val productList = List(createProduct("A", 2.0, (4, 7.0)),
    createProduct("B", 12.0, (0,0.0)),
    createProduct("C", 1.25, (6, 6.0)),
    createProduct("D", 0.15, (0,0.0)))

  behavior of "scan"
  it should "return the correct price with the following scans-1" in {
    val terminal = new Terminal
    terminal.setPricing(productList)
    terminal.scan("A")
    terminal.scan("B")
    terminal.scan("C")
    terminal.scan("D")
    terminal.scan("A")
    terminal.scan("B")
    terminal.scan("A")
    terminal.scan("A")

    val price:Double = terminal.getTotalPrice
    assert(price == 32.40)
  }

  it should "return the correct value with the following scans-2" in {
    val terminal = new Terminal
    terminal.setPricing(productList)
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")

    val price:Double = terminal.getTotalPrice
    assert(price == 7.25)
  }

  it should "return the correct value with the following scans-3" in {
    val terminal = new Terminal
    terminal.setPricing(productList)
    terminal.scan("A")
    terminal.scan("B")
    terminal.scan("C")
    terminal.scan("D")


    val price:Double = terminal.getTotalPrice
    assert(price == 15.40)
  }

  def createProduct(productCode:String, unitPrice:Double, volumePrice:Tuple2[Int,Double]): Product = {
    productCode match {
      case "A" => A(productCode,unitPrice,volumePrice)
      case "B" => B(productCode,unitPrice,volumePrice)
      case "C" => C(productCode,unitPrice,volumePrice)
      case "D" => D(productCode,unitPrice,volumePrice)
    }
  }
}

