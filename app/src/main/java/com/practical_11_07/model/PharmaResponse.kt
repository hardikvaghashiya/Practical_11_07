package com.practical_11_07.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PharmaResponse(
    @SerializedName("errorCode")
    var errorCode: String? = "",
    @SerializedName("filterData")
    var filterData: ArrayList<FilterData> = ArrayList(),
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("status")
    var status: String? = ""
) : Parcelable

@Parcelize
data class FilterData(
    @SerializedName("Cif")
    var cif: String? = "",
    @SerializedName("companyName")
    var companyName: String? = "",
    @SerializedName("hierarchy")
    var hierarchy: ArrayList<Hierarchy> = ArrayList()
) : Parcelable

@Parcelize
data class Hierarchy(
    @SerializedName("accountNumber")
    var accountNumber: String? = "",
    @SerializedName("brandNameList")
    var brandNameList: ArrayList<BrandName> = ArrayList()
) : Parcelable

@Parcelize
data class BrandName(
    @SerializedName("brandName")
    var brandName: String? = "",
    @SerializedName("locationNameList")
    var locationNameList: ArrayList<LocationName> = ArrayList()
) : Parcelable

@Parcelize
data class LocationName(
    @SerializedName("locationName")
    var locationName: String? = "",
    @SerializedName("merchantNumber")
    var merchantNumber: ArrayList<MerchantNumber> = ArrayList()
) : Parcelable

@Parcelize
data class MerchantNumber(
    @SerializedName("mid")
    var mid: String? = "",
    @SerializedName("outletNumber")
    var outletNumber: ArrayList<String> = ArrayList()
) : Parcelable