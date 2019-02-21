class Terminal {

  type ProductInfo = Tuple3[String, Double, Tuple2[Int, Double]]

  // We need to store the state because of the constraints imposed on the assignment
  /*************************************************/
  private var priceMap:Map[String,Product] = Map()
  private var totalPrice:Double = 0.0
  /************************************************/


  def setPricing(products : List[Product]):Unit = {
    priceMap = priceMap ++ products.foldLeft(priceMap)(createPriceMap(_, _))
    totalPrice = 0.0
  }

  def getTotalPrice:Double = totalPrice


  def scan(productCode:String) : Unit = {
    val productOpt: Option[Product] = priceMap.get(productCode)
    if (productOpt != None) {
      val product = productOpt.get
      val oldValue = if (product.qty == 0) 0 else calculatePrice(product, false)
      val newValue = calculatePrice(product, true)
      totalPrice = (totalPrice - oldValue) + newValue
      priceMap = priceMap.updated(productCode, update(product))
      //println("total price now is " + totalPrice)
    }
    else {
      println("Invalid product scanned")
    }
  }

  private def calculatePrice(product:Product, newValueFlag:Boolean):Double = {
    val volumeQty = if (product.volumePrice._1 == 0) Integer.MAX_VALUE else product.volumePrice._1
    if (!newValueFlag) {
      ((product.qty / volumeQty) * product.volumePrice._2) +
        (product.qty % (volumeQty) * product.unitPrice)
    }
    else
      {
        val qty = product.qty + 1
        ((qty / volumeQty) * product.volumePrice._2)  +
          (qty % (volumeQty) * product.unitPrice)
      }
  }

  private def update(productInfo:Product):Product = {
    productInfo.productCode match {
      case "A" => A(productInfo.productCode, productInfo.unitPrice, productInfo.volumePrice, productInfo.qty + 1)
      case "B" => B(productInfo.productCode, productInfo.unitPrice, productInfo.volumePrice, productInfo.qty + 1)
      case "C" => C(productInfo.productCode, productInfo.unitPrice, productInfo.volumePrice, productInfo.qty + 1)
      case "D" => D(productInfo.productCode, productInfo.unitPrice, productInfo.volumePrice, productInfo.qty + 1)

    }
  }

  private def createPriceMap(pmap:Map[String, Product], product: Product):Map[String, Product] =
    pmap + (product.productCode -> product)

}
