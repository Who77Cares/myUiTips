package com.example.materialviewsdemo.carusel

import com.example.materialviewsdemo.R

data class CaruselItem(
    val imgRes: Int,
    val descriptionRes: Int
)

object CaruselData {

    fun getItems(): List<CaruselItem> = listOf(
        CaruselItem(R.drawable.carusel_1, R.string.img_des_1),
        CaruselItem(R.drawable.carusel_2, R.string.img_des_2),
        CaruselItem(R.drawable.carusel_3, R.string.img_des_3),
        CaruselItem(R.drawable.carusel_4, R.string.img_des_4),
        CaruselItem(R.drawable.carusel_5, R.string.img_des_5),
        CaruselItem(R.drawable.carusel_6, R.string.img_des_6),
        CaruselItem(R.drawable.carusel_7, R.string.img_des_7),
    )

}
