package com.example.pharmacymanagement.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pharmacymanagement.R
import com.example.pharmacymanagement.databinding.FragmentInstructionsBinding

class InstructionsFragment: Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        binding.btnInstructionsNext.setOnClickListener {
            it.findNavController().
                    navigate(InstructionsFragmentDirections.actionInstructionsFragmentToMainFragment())
        }

        return binding.root
    }
}