package com.example.week6

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class FragmentTop : Fragment {
    constructor()
    lateinit var fname: EditText
    lateinit var lname:EditText
    lateinit var okBtn: Button
    lateinit var mainActivity: MainActivity

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.top_fragment_layout, container, false)
        fname = view.findViewById(R.id.fname)
        lname = view.findViewById(R.id.lname)
        okBtn = view.findViewById(R.id.okBtn)

        okBtn.setOnClickListener(View.OnClickListener {
            val firstname = fname.text.toString()
            val lastname = lname.text.toString()
            mainActivity.showText(firstname, lastname)
        })
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mainActivity = context
        }
    }
}
