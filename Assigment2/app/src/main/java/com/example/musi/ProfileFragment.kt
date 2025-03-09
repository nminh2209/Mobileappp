package com.example.musi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var usernameTextView: TextView
    private lateinit var creditsTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        usernameTextView = view.findViewById(R.id.username)
        creditsTextView = view.findViewById(R.id.credits)

        val username = arguments?.getString("username")
        val credits = arguments?.getInt("credits", 0)

        usernameTextView.text = "Username: $username"
        creditsTextView.text = "Credits: $credits"

        return view
    }
}