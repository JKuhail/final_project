package com.android.finalproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (
        var name: String,
        var image: Int,
        var period: String,
        var details: String,
        var views: String,
) : Parcelable
