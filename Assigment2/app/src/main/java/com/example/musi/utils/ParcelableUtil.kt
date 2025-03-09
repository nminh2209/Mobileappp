package com.example.musi.utils

import android.os.Parcel
import android.os.Parcelable

object ParcelableUtil {
    fun <T : Parcelable?> fromParcel(`in`: Parcel?, creator: Parcelable.Creator<T>): T {
        return creator.createFromParcel(`in`)
    }

    fun <T : Parcelable?> writeToParcel(item: T, out: Parcel) {
        item!!.writeToParcel(out, 0)
    }
}