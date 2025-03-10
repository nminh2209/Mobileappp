package com.example.musi.models

import android.os.Parcel
import android.os.Parcelable

data class RentalItem(
    val name: String,
    val rating: Float,
    val multiChoiceAttribute: String,
    val pricePerMonth: Float,
    val imageResource: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeFloat(rating)
        parcel.writeString(multiChoiceAttribute)
        parcel.writeFloat(pricePerMonth)
        parcel.writeInt(imageResource)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RentalItem> {
        override fun createFromParcel(parcel: Parcel): RentalItem {
            return RentalItem(parcel)
        }

        override fun newArray(size: Int): Array<RentalItem?> {
            return arrayOfNulls(size)
        }
    }
}