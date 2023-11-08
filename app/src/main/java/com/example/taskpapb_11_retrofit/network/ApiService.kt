package com.example.taskpapb_11_retrofit.network

import com.example.taskpapb_11_retrofit.model.LazdayModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data.php")
    fun getLazday() : Call<LazdayModel>
}