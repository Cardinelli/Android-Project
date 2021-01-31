package com.example.androidproject.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.androidproject.MainActivity
import com.example.androidproject.R
import com.example.androidproject.ui.data.GameRepository
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fragmentView: View
    private lateinit var deactivateButton: Button
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)
        fragmentView = inflater.inflate(R.layout.fragment_settings, container, false)
        auth = Firebase.auth

        deactivateButton = fragmentView.findViewById(R.id.deactivate_btn)
        deactivateButton.setOnClickListener {
            deactivateAccount(auth.currentUser)
        }

        return fragmentView
    }

    private fun deactivateAccount(user: FirebaseUser?) {
        if (user != null) {
            activity?.let {
                user.delete()
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            (activity as MainActivity?)!!.updateUiState(null)
                            GameRepository.deleteGameByUser(it, user.uid)
                            fragmentView.findNavController()
                                .navigate(R.id.action_list_view_auth_view)
                            Toast.makeText(
                                fragmentView.context,
                                "Your user was deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Log.d("55555", task.exception.toString())
                            Toast.makeText(
                                fragmentView.context,
                                "Can not deactivate user",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}