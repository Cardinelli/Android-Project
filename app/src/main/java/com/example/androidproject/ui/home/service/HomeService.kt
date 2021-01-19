package com.example.androidproject.ui.home.service

import com.example.androidproject.ui.home.GameInfo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface HomeService {
    @GET("games")
    @Headers(
        "x-rapidapi-key: 42373e77d9msh1ee35e8ed62a788p177f1cjsn1ff236b0958f",
        "x-rapidapi-host: rawg-video-games-database.p.rapidapi.com"
    )
    fun getGames(): Call<ArrayList<GameInfo>>
}