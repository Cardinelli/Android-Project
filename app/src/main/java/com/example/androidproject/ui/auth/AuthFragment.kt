package com.example.androidproject.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class AuthFragment : Fragment() {

    private lateinit var favouritesViewModel: AuthViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
                ViewModelProvider(this).get(AuthViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_auth, container, false)

        val authButton: FloatingActionButton = findViewById(R.id.login)
        // TODO NAVIGATE TO HOME PAGE ON SUCCESSFUL LOGIN


        return root
    }
}