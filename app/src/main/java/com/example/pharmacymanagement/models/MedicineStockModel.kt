package com.example.pharmacymanagement.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MedicineStockModel(
    medicine_name: String,
    stock_quantity: Int,
    description: String
) : Parcelable {

    private var _medicine_name: String = medicine_name
    val medicine_name: String
        get() = _medicine_name

    private var _stock_quantity: Int = stock_quantity
    val stock_quantity: Int
        get() = _stock_quantity

    private var _description: String = description
    val description: String
        get() = _description


}