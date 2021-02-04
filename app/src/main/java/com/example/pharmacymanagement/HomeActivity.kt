package com.example.pharmacymanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.pharmacymanagement.databinding.ActivityHomeBinding
import com.example.pharmacymanagement.handler.DatabaseHandler

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    // private lateinit var homeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        createTables()
    }

    private fun createTables() {
        var dbHandler: DatabaseHandler = DatabaseHandler(this)
        // dbHandler.addMedicine("third medicine")
        // dbHandler.addMedicineStock("Detol Pharma")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}