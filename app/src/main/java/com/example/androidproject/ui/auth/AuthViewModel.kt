package com.example.androidproject.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is auth Fragment"
    }
    val text: LiveData<String> = _text
}