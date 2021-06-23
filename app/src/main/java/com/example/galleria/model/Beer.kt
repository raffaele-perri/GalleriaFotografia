package com.example.galleria.model

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("id")val id: Long, @SerializedName("name")val name: String,
    @SerializedName("tagline")val tagLine: String, @SerializedName("description")val description: String,
    @SerializedName("image_url")val imageUrl: String
    )
