package com.example.pharmacymanagement.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pharmacymanagement.R
import com.example.pharmacymanagement.databinding.FragmentAddMedicineBinding
import com.example.pharmacymanagement.handler.DatabaseHandler

class AddMedicineFragment : Fragment() {

    private lateinit var binding: FragmentAddMedicineBinding
    private lateinit var db: DatabaseHandler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_medicine, container, false)
        db = DatabaseHandler(context)

        binding.btnProductDetailSave.setOnClickListener {
            saveManufacturer()
            saveMrIfAny()
            saveMedicine()
            saveMedicineIngredients()
            saveMedicineStock()

            it.findNavController().navigateUp()
        }

        binding.btnProductDetailCancel.setOnClickListener {
            it.findNavController().navigateUp()
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
        val ingredients = binding.etIngredients.text.toString()
        if (!ingredients.isEmpty()) {
            val lastMedId = db.lastMedId()
            db.addMedicineIngredients(lastMedId, binding.etIngredients.text.toString())
        }
    }

    private fun saveMedicine() {
        var mr_id: Int = 0
        var manufId = db.lastManufacturerId()
        if (!binding.etMrName.text.toString().isEmpty()) {
            mr_id = db.lastMrId()
        }

        db.addMedicine(
            binding.etMedicineName.text.toString(),
            (binding.etPrice.text.toString()).toInt(),
            binding.etManufDate.text.toString(),
            binding.etExpiryDate.text.toString(),
            mr_id,
            manufId
        )
    }

    private fun saveMrIfAny() {
        if (!binding.etMrName.text.toString().isEmpty()) {
            var manufId = db.lastManufacturerId()
            db.addMR(
                binding.etMrName.text.toString(),
                binding.etMrContact.text.toString(),
                manufId
            )
        }
    }

    private fun saveManufacturer() {
        db.addManufacturer(
            binding.etManufacturerName.text.toString(),
            binding.etManufAddresss.text.toString()
        )
    }
}