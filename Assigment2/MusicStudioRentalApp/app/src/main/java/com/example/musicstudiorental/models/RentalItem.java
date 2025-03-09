package com.example.musicstudiorental.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RentalItem implements Parcelable {
    private String name;
    private float rating;
    private String multiChoiceAttribute;
    private int pricePerMonth;

    public RentalItem(String name, float rating, String multiChoiceAttribute, int pricePerMonth) {
        this.name = name;
        this.rating = rating;
        this.multiChoiceAttribute = multiChoiceAttribute;
        this.pricePerMonth = pricePerMonth;
    }

    protected RentalItem(Parcel in) {
        name = in.readString();
        rating = in.readFloat();
        multiChoiceAttribute = in.readString();
        pricePerMonth = in.readInt();
    }

    public static final Creator<RentalItem> CREATOR = new Creator<RentalItem>() {
        @Override
        public RentalItem createFromParcel(Parcel in) {
            return new RentalItem(in);
        }

        @Override
        public RentalItem[] newArray(int size) {
            return new RentalItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getMultiChoiceAttribute() {
        return multiChoiceAttribute;
    }

    public int getPricePerMonth() {
        return pricePerMonth;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(rating);
        dest.writeString(multiChoiceAttribute);
        dest.writeInt(pricePerMonth);
    }
}