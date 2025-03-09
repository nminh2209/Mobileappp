package com.example.musi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musi.models.RentalItem

class ItemAdapter(
    private val context: Context,
    private val rentalItems: List<RentalItem>,
    private val listener: OnItemClickListener?
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(rentalItem: RentalItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rentalItem = rentalItems[position]
        holder.bind(rentalItem)
    }

    override fun getItemCount(): Int {
        return rentalItems.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(android.R.id.text1)
        private val itemDetails: TextView = itemView.findViewById(android.R.id.text2)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(rentalItems[adapterPosition])
            }
        }

        fun bind(rentalItem: RentalItem) {
            itemName.text = rentalItem.name
            itemDetails.text = "Rating: ${rentalItem.rating} | Price: ${rentalItem.pricePerMonth} credits"
        }
    }
}