package com.example.androidproject.ui.home.service

import com.example.androidproject.ui.home.GameInfo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface HomeService {
    @GET("games")
    fun getGames(): Call<ArrayList<GameInfo>>
}