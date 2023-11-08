package com.example.taskpapb_11_retrofit.model

import com.google.gson.annotations.SerializedName

data class LazdayModel(
    @SerializedName("result")
    var result : List<LazdayData>
)

data class LazdayData(
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("image")
    val image : String
)