package com.amttech.imageapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImagesResponse(
    @SerializedName("total") var total: Int?,
    @SerializedName("totalHits") var totalHits: Int?,
    @SerializedName("hits") var hits: List<Images>? ) {
}