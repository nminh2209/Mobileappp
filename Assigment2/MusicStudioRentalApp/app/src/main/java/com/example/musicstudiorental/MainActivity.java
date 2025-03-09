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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView itemName;
    private TextView itemRating;
    private TextView itemPrice;
    private ImageView itemImage;
    private Button nextButton;
    private Button borrowButton;

    private List<RentalItem> rentalItems;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemName = findViewById(R.id.item_name);
        itemRating = findViewById(R.id.item_rating);
        itemPrice = findViewById(R.id.item_price);
        itemImage = findViewById(R.id.item_image);
        nextButton = findViewById(R.id.next_button);
        borrowButton = findViewById(R.id.borrow_button);

        rentalItems = new ArrayList<>();
        rentalItems.add(new RentalItem("Guitar", 4, "Acoustic", 10));
        rentalItems.add(new RentalItem("Drum Set", 5, "Electronic", 15));
        rentalItems.add(new RentalItem("Keyboard", 3, "Synth", 12));
        rentalItems.add(new RentalItem("Microphone", 4, "Dynamic", 8));

        displayCurrentItem();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % rentalItems.size();
                displayCurrentItem();
            }
        });

        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentalItem selectedItem = rentalItems.get(currentIndex);
                Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
                intent.putExtra("selectedItem", selectedItem);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void displayCurrentItem() {
        RentalItem currentItem = rentalItems.get(currentIndex);
        itemName.setText(currentItem.getName());
        itemRating.setText(String.valueOf(currentItem.getRating()));
        itemPrice.setText(String.valueOf(currentItem.getPrice()) + " credits");
        // Assume itemImage is set based on the item name or some resource mapping
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Booking successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Booking cancelled.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}