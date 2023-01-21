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
        "https://images.samsung.com/is/image/samsung/p6pim/de/gu32t5377cuxzg/gallery/de-fhd-t5377-324544-gu32t5377cuxzg-450345077?\$650_519_PNG\$",
        "Smart TV",
        "629,00 €",
        "4.6",
        "86 Reviews"),
    Product(
        "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MQG83ref_VW_34FR+watch-44-alum-silver-nc-se_VW_34FR_WF_CO_GEO_DE?wid=750&hei=712&trim=1%2C0&fmt=p-jpg&qlt=95&.v=1660778418734%2C1661467371152",
        "Smart Watch",
        "109,00 €",
        "4.6",
        "86 Reviews"),

    Product(
        "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/ipad-pro-finish-select-202212-12-9inch-space-gray-wifi_AV1_GEO_EMEA?wid=5120&hei=2880&fmt=p-jpg&qlt=95&.v=1670866301449",
        "iPad Air - Tablet",
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
        "https://cdn.dxomark.com/wp-content/uploads/medias/post-125834/Apple-iPhone-14_FINAL_featured-image-packshot-review.jpg",
        "Smart Phone",
        "109,00 €",
        "4.6",
        "86 Reviews"),

    )