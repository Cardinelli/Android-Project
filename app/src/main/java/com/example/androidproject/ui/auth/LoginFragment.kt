package com.example.androidproject.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.androidproject.MainActivity
import com.example.androidproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var favouritesViewModel: AuthViewModel
    private lateinit var fragmentView: View
    private lateinit var emailInput: TextView
    private lateinit var passwordInput: TextView
    private lateinit var loginButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
            ViewModelProvider(this).get(AuthViewModel::class.java)
        fragmentView = inflater.inflate(R.layout.fragment_auth_login, container, false)

        loginButton = fragmentView.findViewById(R.id.login_btn)
        emailInput = fragmentView.findViewById(R.id.username)
        passwordInput = fragmentView.findViewById(R.id.password)

        auth = Firebase.auth

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            signIn(email, password)
        }
        return fragmentView
    }

    private fun signIn(email: String, password: String) {
        activity?.let {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        (activity as MainActivity?)!!.updateUiState(auth.currentUser)
                        fragmentView.findNavController()
                            .navigate(R.id.action_auth_view_to_fragment_home)
                    } else {
                        Toast.makeText(
                            fragmentView.context,
                            "Authentication failed: ${task.exception?.message.toString()}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
        }
    }
}