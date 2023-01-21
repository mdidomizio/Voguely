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

val mockData = listOf(
    Product(
        "https://resource.logitechg.com/w_692,c_limit,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/audio/g735-wireless-headset/gallery/g735-gallery-1.png?v=1",
        "AirPods Max Headset",
        "629,00 €",
        "4.6",
        "86 Reviews"),


    Product(
        "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MMMQ3?wid=2000&hei=2000&fmt=jpeg&qlt=95&.v=1645138486301",
        "Magic Mouse",
        "109,00 €",
        "4.6",
        "86 Reviews"),
    Product(
        "https://resource.logitechg.com/w_692,c_limit,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/audio/g735-wireless-headset/gallery/g735-gallery-1.png?v=1",
        "TV",
        "629,00 €",
        "4.6",
        "86 Reviews"),
    Product(
        "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MMMQ3?wid=2000&hei=2000&fmt=jpeg&qlt=95&.v=1645138486301",
        "Smartwatch",
        "109,00 €",
        "4.6",
        "86 Reviews"),

    Product(
        "https://resource.logitechg.com/w_692,c_limit,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/audio/g735-wireless-headset/gallery/g735-gallery-1.png?v=1",
        "AirPods Max",
        "629,00 €",
        "4.6",
        "86 Reviews"),

    Product(
        "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MMMQ3?wid=2000&hei=2000&fmt=jpeg&qlt=95&.v=1645138486301",
        "Magic Mouse",
        "109,00 €",
        "4.6",
        "86 Reviews"),

    )