package com.example.musi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musi.models.RentalItem

class RentalItemAdapter(
    private val context: Context,
    private val rentalItems: List<RentalItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RentalItemAdapter.RentalItemViewHolder>() {

    interface OnItemClickListener {
        fun onBorrowClick(rentalItem: RentalItem)
        fun onCancelClick(rentalItem: RentalItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_item_detail, parent, false)
        return RentalItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RentalItemViewHolder, position: Int) {
        val rentalItem = rentalItems[position]
        holder.bind(rentalItem)
    }

    override fun getItemCount(): Int {
        return rentalItems.size
    }

    inner class RentalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.item_name)
        private val itemRating: TextView = itemView.findViewById(R.id.item_rating)
        private val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        private val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        private val borrowButton: Button = itemView.findViewById(R.id.borrow_button)
        private val cancelButton: Button = itemView.findViewById(R.id.cancel_button)

        fun bind(rentalItem: RentalItem) {
            itemName.text = rentalItem.name
            itemRating.text = rentalItem.rating.toString()
            itemPrice.text = "${rentalItem.pricePerMonth} credits"

            // Assume itemImage is set based on the item name or some resource mapping

            borrowButton.setOnClickListener {
                listener.onBorrowClick(rentalItem)
            }

            cancelButton.setOnClickListener {
                listener.onCancelClick(rentalItem)
            }
        }
    }
}