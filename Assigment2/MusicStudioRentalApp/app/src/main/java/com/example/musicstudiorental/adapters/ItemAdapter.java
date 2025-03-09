package com.example.musicstudiorental.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicstudiorental.models.RentalItem;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<RentalItem> rentalItems;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(RentalItem rentalItem);
    }

    public ItemAdapter(Context context, List<RentalItem> rentalItems, OnItemClickListener listener) {
        this.context = context;
        this.rentalItems = rentalItems;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        RentalItem rentalItem = rentalItems.get(position);
        holder.bind(rentalItem);
    }

    @Override
    public int getItemCount() {
        return rentalItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private TextView itemDetails;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(android.R.id.text1);
            itemDetails = itemView.findViewById(android.R.id.text2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(rentalItems.get(getAdapterPosition()));
                    }
                }
            });
        }

        public void bind(RentalItem rentalItem) {
            itemName.setText(rentalItem.getName());
            itemDetails.setText("Rating: " + rentalItem.getRating() + " | Price: " + rentalItem.getPrice() + " credits");
        }
    }
}