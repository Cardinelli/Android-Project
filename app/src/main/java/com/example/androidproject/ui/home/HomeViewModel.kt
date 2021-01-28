package com.example.androidproject.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.ui.home.service.HomeService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    private val _gamesLiveData = MutableLiveData<ArrayList<GameInfo>>()
    val gamesLiveData: LiveData<ArrayList<GameInfo>>
        get() = _gamesLiveData

    private val _gameLiveData = MutableLiveData<GameInfo>()
    val gameLiveData: LiveData<GameInfo>
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

        homeService.getGames().enqueue(object : Callback<ArrayList<GameInfo>> {
            override fun onResponse(
                call: Call<ArrayList<GameInfo>>,
                response: Response<ArrayList<GameInfo>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _gamesLiveData.postValue(it)
                    }
                } else {
                    Log.d("555555", response.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<GameInfo>>, t: Throwable) {
                Log.d("FAIL", t.toString())
                Log.d("Retrofit Failed", "Error")
            }
        })
    }

    fun postGame(game: GameInfo?) {
        _gameLiveData.postValue(game)
    }
}