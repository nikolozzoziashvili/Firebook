package ge.btu.firebook

import android.widget.ImageView

data class Data(
    val bookName: String,
    val category: String,
    val authors: String,
    val description: String,
    val bookImage: ImageView
)