
trait Product{
  val productCode:String
  val unitPrice:Double
  val volumePrice:Tuple2[Int,Double]
  val qty:Int
}

case class A(productCode:String, unitPrice:Double, volumePrice:Tuple2[Int, Double] = (0,0.0), qty:Int=0) extends Product
case class B(productCode:String, unitPrice:Double, volumePrice:Tuple2[Int, Double] = (0,0.0), qty:Int=0) extends Product
case class C(productCode:String, unitPrice:Double, volumePrice:Tuple2[Int, Double] = (0,0.0), qty:Int=0) extends Product
case class D(productCode:String, unitPrice:Double, volumePrice:Tuple2[Int, Double] = (0,0.0), qty:Int=0) extends Product

