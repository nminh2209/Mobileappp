package com.example.week7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var lsStudents: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val strList = arrayOf("Nguyen A", "Nguyen B", "Nguyen C", "Nguyen D")
        lsStudents = findViewById(R.id.lsStudents)


        lsStudents.layoutManager = LinearLayoutManager(this)

        val adapter = StudentAdapter(strList)
        lsStudents.adapter = adapter
    }
}

class StudentAdapter(private val items: Array<String>) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
        holder.textView.setOnClickListener {
            Toast.makeText(holder.textView.context, items[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = items.size
}




/**private lateinit var lsStudents: ListView

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val strList = arrayOf("Nguyen A", "Nguyen B", "Nguyen C", "Nguyen D")
    lsStudents = findViewById(R.id.lsStudents)

    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strList)
    lsStudents.adapter = adapter

    lsStudents.setOnItemClickListener { _, _, position, _ ->
        val element = adapter.getItem(position)
        Toast.makeText(this, element, Toast.LENGTH_LONG).show()
    }
}
}
**/