package com.example.androidproject.ui.home.service

import com.example.androidproject.ui.data.Game

import retrofit2.Call
import retrofit2.http.GET


interface HomeService {
    @GET("games")
    fun getGames(): Call<ArrayList<Game>>
}