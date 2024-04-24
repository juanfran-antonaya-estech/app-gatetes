package es.estech.myapplication.data.models.breeds


import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("imperial") var imperial: String = "", // 6 - 12
    @SerializedName("metric") var metric: String = "" // 3 - 7
)