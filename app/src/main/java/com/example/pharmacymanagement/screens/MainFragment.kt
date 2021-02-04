package com.example.pharmacymanagement.screens

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pharmacymanagement.R
import com.example.pharmacymanagement.databinding.FragmentMainBinding
import com.example.pharmacymanagement.handler.DatabaseHandler
import com.example.pharmacymanagement.models.MedicineStockModel

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container, false)

        var db: DatabaseHandler = DatabaseHandler(context)
        var data = db.stock

        updateVisibilityUI(data.isEmpty())
        if(!data.isEmpty()) {
            for (medicine in db.stock) {
                addMedicineUI(medicine)
            }
        }


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

    private fun addMedicineUI(medicine: MedicineStockModel) {

        val linearLayout =
            LinearLayout(ContextThemeWrapper(context, R.style.linearLayout_products_style))
        linearLayout.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(16)

        linearLayout.setOnClickListener {
            it.findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToEditStockFragment(medicine)
            )
        }

        val name_textView = TextView(context)
        name_textView.text = "Medicine Name: ${medicine.medicine_name}"
        TextViewCompat.setTextAppearance(name_textView, R.style.label_style_productListing)

        val quantity_textView = TextView(context)
        quantity_textView.text = "Stock Quantity: ${medicine.stock_quantity}"
        TextViewCompat.setTextAppearance(quantity_textView, R.style.label_style_productListing)

        val desctiption_textView = TextView(context)
        desctiption_textView.text = "Description: ${medicine.description}"
        TextViewCompat.setTextAppearance(desctiption_textView, R.style.label_style_productListing)

        linearLayout.addView(name_textView)
        linearLayout.addView(quantity_textView)
        linearLayout.addView(desctiption_textView)

        binding.itemsLayout.addView(linearLayout, layoutParams)
    }

    private fun updateVisibilityUI(noProducts: Boolean) {
        if (noProducts) {
            binding.labelNoItem.visibility = View.VISIBLE
            binding.itemsScrollview.visibility = View.GONE
        } else {
            binding.labelNoItem.visibility = View.GONE
            binding.itemsScrollview.visibility = View.VISIBLE
        }
    }

}