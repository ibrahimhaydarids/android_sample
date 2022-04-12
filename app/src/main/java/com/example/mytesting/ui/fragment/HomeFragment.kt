package com.example.mytesting.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytesting.R
import com.example.mytesting.helpers.EventObserver
import com.example.mytesting.ui.viewmodels.HomeViewModel
import com.example.mytesting.utils.toastt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        getGalleryItems()
        subscribeObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.home_fragment, container, false)


    private fun listeners(){
    /*    linearFooter.setOnClickListener {
            findNavController().navigateUp()
            findNavController().navigate(R.id.action_homeFragment_to_showDetailsFragment)
        }*/
    }

    private fun getGalleryItems(){
        homeViewModel.getAllItems(1)
    }

    private fun subscribeObservers(){
        homeViewModel.listItems.observe(viewLifecycleOwner,EventObserver(
            onLoading = {},
            onError = {},
        ){parent ->
            toastt("...."+parent.items!!.size.toString())

        })
    }
}