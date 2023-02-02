package com.example.tutorialapi

import com.google.gson.annotations.SerializedName
data class Musica (

    @SerializedName("_id") var _id : String,
    @SerializedName("_rev") var _rev : String,
    @SerializedName("titulo") var titulo : String,
    @SerializedName("artista") var artista : String,
    @SerializedName("album") var album : String,
    @SerializedName("imagem") var imagem : String
)