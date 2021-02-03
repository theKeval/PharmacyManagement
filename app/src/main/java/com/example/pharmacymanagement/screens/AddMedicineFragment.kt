package com.example.pharmacymanagement.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pharmacymanagement.R
import com.example.pharmacymanagement.databinding.FragmentAddMedicineBinding

class AddMedicineFragment: Fragment() {

    private lateinit var binding: FragmentAddMedicineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medicine, container, false)

        return binding.root
    }
}