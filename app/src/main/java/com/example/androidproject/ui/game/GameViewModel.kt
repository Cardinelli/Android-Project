package com.example.androidproject.ui.game

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.ui.data.Game
import com.example.androidproject.ui.data.GameRepository
import com.example.androidproject.ui.home.service.HomeService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameViewModel : ViewModel() {
    private val _gamesLiveData = MutableLiveData<ArrayList<Game>>()
    val gamesLiveData: LiveData<ArrayList<Game>>
        get() = _gamesLiveData

    private val _gameLiveData = MutableLiveData<Game>()
    val gameLiveData: LiveData<Game>
        get() = _gameLiveData


    fun getGames() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Cardinelli/repo/")
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val homeService = retrofit.create(HomeService::class.java)

        homeService.getGames().enqueue(object : Callback<ArrayList<Game>> {
            override fun onResponse(
                call: Call<ArrayList<Game>>,
                response: Response<ArrayList<Game>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _gamesLiveData.postValue(it)
                    }
                } else {
                    Log.d("555555", response.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<Game>>, t: Throwable) {
                Log.d("FAIL", t.toString())
                Log.d("Retrofit Failed", "Error")
            }
        })
    }

    fun postGame(game: Game?) {
        _gameLiveData.postValue(game)
    }

    fun postGames(games: List<Game>?) {
        _gamesLiveData.postValue(games as ArrayList<Game>?)
    }

    fun getGames(context: Context) {
        postGames(GameRepository.getGames(context))
    }
}