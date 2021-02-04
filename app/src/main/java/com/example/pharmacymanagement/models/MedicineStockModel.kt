package com.example.pharmacymanagement.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MedicineStockModel(
    val name: String,
    val quantity: Int,
    val desc: String
) : Parcelable {

    private var _medicine_name: String = name
    val medicine_name: String
        get() = _medicine_name

    private var _stock_quantity: Int = quantity
    val stock_quantity: Int
        get() = _stock_quantity

    private var _description: String = desc
    val description: String
        get() = _description


}