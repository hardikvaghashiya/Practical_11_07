package com.practical_11_07.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectFilterModel(
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("isSelected")
    var isSelected: Boolean = false
) : Parcelable