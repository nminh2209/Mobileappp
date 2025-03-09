package com.example.musicstudiorental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicstudiorental.models.RentalItem;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView itemName;
    private TextView itemRating;
    private TextView itemPrice;
    private Button borrowButton;
    private RentalItem rentalItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        itemName = findViewById(R.id.item_name);
        itemRating = findViewById(R.id.item_rating);
        itemPrice = findViewById(R.id.item_price);
        borrowButton = findViewById(R.id.borrow_button);

        // Retrieve the RentalItem passed from MainActivity
        rentalItem = getIntent().getParcelableExtra("RENTAL_ITEM");

        if (rentalItem != null) {
            displayItemDetails(rentalItem);
        }

        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the borrow action
                Toast.makeText(ItemDetailActivity.this, "Item borrowed!", Toast.LENGTH_SHORT).show();
                // Optionally, you can finish the activity or navigate back
                finish();
            }
        });
    }

    private void displayItemDetails(RentalItem item) {
        itemName.setText(item.getName());
        itemRating.setText(String.valueOf(item.getRating()));
        itemPrice.setText(String.valueOf(item.getPrice()));
        // Set image resource if applicable
        // ImageView itemImage = findViewById(R.id.item_image);
        // itemImage.setImageResource(item.getImageResource());
    }
}