package com.example.androidproject.ui.favourites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.ui.data.Game
import com.example.androidproject.ui.data.GameRepository

class FavouritesViewModel : ViewModel() {

    private val _gamesLiveData = MutableLiveData<List<Game>>()
    val gamesLiveData: LiveData<List<Game>>
        get() = _gamesLiveData

    private val _gameLiveData = MutableLiveData<Game>()
    val gameLiveData: LiveData<Game>
        get() = _gameLiveData

    fun postGame(game: Game?) {
        _gameLiveData.postValue(game)
    }

    fun postGames(games: List<Game>?) {
        _gamesLiveData.postValue(games)
    }

    fun getGames(context: Context) {
        postGames(GameRepository.getGames(context))
    }
}