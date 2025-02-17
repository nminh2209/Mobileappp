package com.example.week6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Main_Fragment_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment_layout)
    }

    fun showText(firstName: String, lastName: String) {
        // Create an instance of BotFragment
        val bottomFragment = BotFragment()

        // Call the showText method of the BottomFragment
        bottomFragment.showText(firstName, lastName)

        // Show the BottomSheetDialogFragment using the FragmentManager
        bottomFragment.show(supportFragmentManager, bottomFragment.tag)
    }
}
