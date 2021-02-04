package com.example.pharmacymanagement.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pharmacymanagement.R
import com.example.pharmacymanagement.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container, false)

        binding.addMedicine.setOnClickListener {
            it.findNavController().
                    navigate(MainFragmentDirections.actionMainFragmentToAddMedicineFragment())
        }

        binding.addInvoice.setOnClickListener {
            it.findNavController().
                    navigate(MainFragmentDirections.actionMainFragmentToAddInvoiceFragment())
        }

        return binding.root
    }
}