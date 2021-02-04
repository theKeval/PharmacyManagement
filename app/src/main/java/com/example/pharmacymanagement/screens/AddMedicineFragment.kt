package com.example.pharmacymanagement.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pharmacymanagement.R
import com.example.pharmacymanagement.databinding.FragmentAddMedicineBinding
import com.example.pharmacymanagement.handler.DatabaseHandler

class AddMedicineFragment: Fragment() {

    private lateinit var binding: FragmentAddMedicineBinding
    private lateinit var db: DatabaseHandler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medicine, container, false)
        db = DatabaseHandler(context)

        binding.btnProductDetailSave.setOnClickListener {
            saveManufacturer()
            saveMrIfAny()
            saveMedicine()
            saveMedicineIngredients()
            saveMedicineStock()
        }

        return binding.root
    }

    private fun saveMedicineStock() {
        db.addMedicineStock(
            binding.etMedicineName.text.toString(),
            (binding.etQuantity.text.toString()).toInt(),
            binding.etDescription.text.toString()
        )
    }

    private fun saveMedicineIngredients() {
        TODO("Not yet implemented")
    }

    private fun saveMedicine() {
        TODO("Not yet implemented")
    }

    private fun saveMrIfAny() {
        TODO("Not yet implemented")
    }

    private fun saveManufacturer() {
        TODO("Not yet implemented")
    }
}