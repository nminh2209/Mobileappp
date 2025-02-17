package com.example.week6

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BotFragment : BottomSheetDialogFragment() {

    lateinit var fullname: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.bot_fragment_layout, container, false)
        fullname = view.findViewById(R.id.fullname)
        return view
    }

    fun showText(firstname: String, lastname: String) {
        fullname.text = "$firstname $lastname"
    }
}
