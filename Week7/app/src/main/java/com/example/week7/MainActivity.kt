package com.example.week7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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


        val avatars = intArrayOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
        )

        val strList = arrayOf("Nguyen A", "Nguyen B", "Nguyen C", "Nguyen D")
        lsStudents = findViewById(R.id.lsStudents)
        lsStudents.layoutManager = LinearLayoutManager(this)

        val adapter = StudentAdapter(strList, avatars) { name ->
            Toast.makeText(this, name, Toast.LENGTH_LONG).show()
        }
        lsStudents.adapter = adapter
    }
}

class StudentAdapter(
    private val items: Array<String>,
    private val avatars: IntArray,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.studentName)
        val imageView: ImageView = view.findViewById(R.id.studentImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
        holder.imageView.setImageResource(avatars[position])
        holder.itemView.setOnClickListener { onItemClick(items[position]) }
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