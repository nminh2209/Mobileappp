package com.example.musicstudiorental.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableUtil {

    public static <T extends Parcelable> T fromParcel(Parcel in, Parcelable.Creator<T> creator) {
        return creator.createFromParcel(in);
    }

    public static <T extends Parcelable> void writeToParcel(T item, Parcel out) {
        item.writeToParcel(out, 0);
    }
}