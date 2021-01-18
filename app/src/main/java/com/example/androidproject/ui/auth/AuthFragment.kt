package com.example.androidproject.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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

        root.findViewById<Button>(R.id.login).setOnClickListener{
            it.findNavController().navigate(R.id.action_auth_view_to_fragment_home)
        }

        return root
    }
}