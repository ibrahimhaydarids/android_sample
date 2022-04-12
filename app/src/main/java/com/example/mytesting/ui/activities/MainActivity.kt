package com.example.mytesting.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController

import com.example.mytesting.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listeners()
    }

    private fun listeners(){
        linearFooter.setOnClickListener {
 /*           findNavController().navigateUp()
            findNavController().navigate(R.id.action_homeFragment_to_showDetailsFragment)*/
        }
    }

}