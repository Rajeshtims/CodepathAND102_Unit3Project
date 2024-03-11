package com.example.unit3_project

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

class Movie{
    @JvmField
    @SerializedName("original_title")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var desc: String? = null

    @JvmField
    @SerializedName("backdrop_path")
    var imageUrl: String? = null

}


