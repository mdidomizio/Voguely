package com.wit.voguely.ui.main.home

class Product (var urls: String,
               var itemName: String,
               var price: String,
               var rate: String,
               var review: String)

val mockProduct = Product (
    "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/og-airpods-max-202011?wid=1200&hei=630&fmt=jpeg&qlt=95&.v=1603996970000",
    "AirPods Max",
    "629,00 €",
    "3.456",
    "14.787")