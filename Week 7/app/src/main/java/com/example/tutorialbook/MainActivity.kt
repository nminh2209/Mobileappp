package com.example.tutorialbook

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

data class Book(
    val title: String,
    val rating: Float,
    val coverResourceId: Int
)

class MainActivity : AppCompatActivity() {
    private lateinit var rvBooks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample book data
        val books = listOf(
            Book("The Great Gatsby", 4.5f, R.drawable.ic_launcher_background),
            Book("To Kill a Mockingbird", 4.8f, R.drawable.ic_launcher_background),
            Book("1984", 4.6f, R.drawable.ic_launcher_background),
            Book("Pride and Prejudice", 4.7f, R.drawable.ic_launcher_background),
            Book("The Catcher in the Rye", 4.2f, R.drawable.ic_launcher_background),
            Book("Lord of the Rings", 4.9f, R.drawable.ic_launcher_background),
            Book("The Hobbit", 4.7f, R.drawable.ic_launcher_background),
            Book("Fahrenheit 451", 4.4f, R.drawable.ic_launcher_background),
            Book("Animal Farm", 4.3f, R.drawable.ic_launcher_background),
            Book("Brave New World", 4.5f, R.drawable.ic_launcher_background)
        )

        rvBooks = findViewById(R.id.rvBooks)
        rvBooks.layoutManager = LinearLayoutManager(this)

        val adapter = BookAdapter(books) { book ->
            Toast.makeText(this, "${book.title} - Rating: ${book.rating}", Toast.LENGTH_SHORT).show()
        }
        rvBooks.adapter = adapter
    }
}

class BookAdapter(
    private val books: List<Book>,
    private val onItemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookCover: ImageView = view.findViewById(R.id.bookCover)
        val bookTitle: TextView = view.findViewById(R.id.bookTitle)
        val bookRating: TextView = view.findViewById(R.id.bookRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bookTitle.text = book.title
        holder.bookRating.text = "Rating: ${book.rating}/5.0"
        holder.bookCover.setImageResource(book.coverResourceId)
        holder.itemView.setOnClickListener { onItemClick(book) }
    }

    override fun getItemCount() = books.size
}
