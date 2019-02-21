import scala.util.parsing.combinator.RegexParsers

trait Command

case class NewProductCommand(product: Product) extends Command

case object UnkownCommand extends Command

case object QuitCommand extends Command


private object ExprParser extends RegexParsers {
  val dblNumber: Parser[Double] =
    """\d*(\.\d+)?""".r ^^ {
      case number => number.toDouble
    }

  val charStr: Parser[String] = """[A-D]""".r
  val charStrScan: Parser[String] = """[A-D$]""".r
  val symbol: Parser[String] = """,""".r
  val symbolOpt: Parser[String] = """,?""".r

  val volumeTuple: Parser[Tuple2[Int, Double]] = opt("""\d+""".r ~ symbol ~ dblNumber) ^^ {
    case Some(x ~ y ~ z) => (x.toInt, z)
    case None => (0, 0.0)
  }

  private def expr: Parser[Command] = (charStr ~ symbol ~ dblNumber ~ symbolOpt ~ volumeTuple) ^^ {
    case (pcode ~ sym ~ unitprice ~ symopt ~ volprice) => NewProductCommand(createProduct(pcode, unitprice, volprice))
  }

  private def createProduct(productCode: String, unitPrice: Double, volumePrice: Tuple2[Int, Double]): Product = {
    println(s"Product ${productCode} captured - Enter next product - Enter d or done to scan items")
    productCode match {
      case "A" => A(productCode, unitPrice, volumePrice)
      case "B" => B(productCode, unitPrice, volumePrice)
      case "C" => C(productCode, unitPrice, volumePrice)
      case "D" => D(productCode, unitPrice, volumePrice)
    }
  }

  private def quit: Parser[QuitCommand.type] =
    "done|d".r ^^ (_ => QuitCommand)

  private val parser = ExprParser.expr | ExprParser.quit

  def parseAsCommand(input: String): Command = {
    parseAll(parser, input) match {
      case Success(command, _) => command
      case Failure(msg, _) => UnkownCommand
      case Error(_, _) => throw new Exception("Unexpected Error")
    }
  }

  def parseAsScan(input: String): String = {
    parseAll(charStrScan, input) match {
      case Success(productCode, _) if (productCode == "$") => "quit"
      case Success(productCode, _) => productCode
      case Failure(msg, _) => "invalid"
    }
  }
}


/*object Test {

  def run(): Unit = {
    System.out.println("Assignment running\nEnter "
      + Console.BLUE + "commands" + Console.RESET
      + " into the terminal: "
      + Console.BLUE + "[e.g. `d` or `done`]" + Console.RESET)
    commandLoop()

  }

  @tailrec
  private def commandLoop(): Unit =
    ExprParser.parseAsCommand(StdIn.readLine()) match {
      case NewProductCommand(product:Product) =>
        println(product)
        commandLoop()
      case QuitCommand =>
        println("Now scan items one by one - Enter $ to end:")
        scanLoop()
      case UnkownCommand =>
        println("Invalid Input - please use the correct format: ProductName:SingleCharacter," +
          "UnitPrice:Double,VolumeQty:Int,VolumePrice:Double")
        println("example:A,12.5,3,1.5")
        commandLoop()
    }

  @tailrec
  private def scanLoop(): Unit =
    ExprParser.parseAsScan(StdIn.readLine()) match {
      case str if(str == "quit") =>
        System.exit(0)
      case str if(str == "invalid") =>
        println("Invalid product name - please enter correct product")
        scanLoop()
      case str  => println(s"entered product is ${str}")
        scanLoop()
    }


  def main(args: Array[String]): Unit = {
    run()
  }
}
*/
