import scala.annotation.tailrec
import scala.io.StdIn

object TerminalApp {

  private var productList:List[Product] = List()

  def run(): Unit = {
    System.out.println("Assignment running\nEnter "
      + Console.BLUE + "product list" + Console.RESET
      + " into the terminal: "
      + Console.BLUE + "[e.g. `d` or `done`]" + Console.RESET)
    commandLoop()

  }

  @tailrec
  private def commandLoop(): Unit =
    ExprParser.parseAsCommand(StdIn.readLine()) match {
      case NewProductCommand(product:Product) =>
        productList = product::productList
        commandLoop()
      case QuitCommand =>
        println("Now scan items one by one - Enter $ to end:")
        //scanLoop()
      case UnkownCommand =>
        println("Invalid Input - please use the correct format: ProductName:SingleCharacter," +
          "UnitPrice:Double,VolumeQty:Int,VolumePrice:Double")
        println("example:A,12.5,3,1.5")
        commandLoop()
    }

  @tailrec
  private def scanLoop(terminal:Terminal): Unit =
    ExprParser.parseAsScan(StdIn.readLine()) match {
      case str if(str == "quit") =>
        return
      case str if(str == "invalid") =>
        println("Invalid product name - please enter correct product")
        scanLoop(terminal)
      case productCode  => println(s"scanned product is ${productCode} - type $$ to exit"); terminal.scan(productCode)
        scanLoop(terminal)
    }


  def main(args: Array[String]): Unit = {
    val terminal = new Terminal
    System.out.println("Assignment running\nEnter "
      + Console.BLUE + "product list" + Console.RESET
      + " into the terminal - "
      + Console.BLUE + "[enter `d` or `done`] to exit" + Console.RESET)
    System.out.println(Console.BLUE +
      "Product List Format: " + Console.RESET + "ProductName:[A-D],UnitPrice:Double,VolumeQty:Int,VolumePrice:Double")
    System.out.println(Console.BLUE + "example:" + Console.RESET + "A,12.5,3,1.5")
    commandLoop()
    terminal.setPricing(productList)
    scanLoop(terminal)  // See the terminal.scan in the ScanLoop

    /*terminal.scan("A")
    terminal.scan("B")
    terminal.scan("C")
    terminal.scan("D")
    terminal.scan("A")
    terminal.scan("B")
    terminal.scan("A")
    terminal.scan("A")
*/

    /*terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")
    terminal.scan("C")*/

    /*terminal.scan("A")
    terminal.scan("B")
    terminal.scan("C")
    terminal.scan("D")*/
    val result = terminal.getTotalPrice
    println(s"Total: ${result}")
  }
}
