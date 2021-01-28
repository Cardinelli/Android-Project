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
import com.example.androidproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {
    private lateinit var favouritesViewModel: AuthViewModel
    private lateinit var emailInput: TextView
    private lateinit var passwordInput: TextView
    private lateinit var registerButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
            ViewModelProvider(this).get(AuthViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_auth_register, container, false)

        registerButton = root.findViewById(R.id.register_btn)
        emailInput = root.findViewById(R.id.username)
        passwordInput = root.findViewById(R.id.password)

        auth = Firebase.auth

        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            register(email, password)

            it.findNavController().navigate(R.id.action_auth_view_to_fragment_home)
        }
        return root
    }

    private fun register(email: String, password: String) {
        activity?.let {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(context, "Hello ${user?.uid}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}