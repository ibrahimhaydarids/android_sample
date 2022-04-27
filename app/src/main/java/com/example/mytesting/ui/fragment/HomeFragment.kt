package com.example.mytesting.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytesting.R
import com.example.mytesting.helpers.EventObserver
import com.example.mytesting.model.Item
import com.example.mytesting.ui.adapters.ItemAdapter
import com.example.mytesting.ui.viewmodels.HomeViewModel
import com.example.mytesting.utils.hide
import com.example.mytesting.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var itemAdapter:ItemAdapter

    val items: ArrayList<Item> by lazy {
        ArrayList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        setupRecyclerViewJob()
        getGalleryItems()
        subscribeObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.home_fragment, container, false)


    private fun listeners(){

        itemAdapter.setOnItemClickListener { item ->
            val action = HomeFragmentDirections.actionHomeFragmentToShowDetailsFragment(item)
            findNavController().navigate(action)
        }


        itemAdapter.setOnFavoriteClickListener{item,pos,image ->
            if(!item.favorite){
                homeViewModel.insertFavoriteItems(item)
                image.setBackgroundResource(R.drawable.ic_star_filled)
            }else{
                homeViewModel.deleteFavorite(item)
                image.setBackgroundResource(R.drawable.ic_star)
            }

        }
    }




    private fun getGalleryItems(){
        homeViewModel.getAllItems(1)
    }

    private fun setupRecyclerViewJob() = rvItems.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = itemAdapter
    }

    private fun subscribeObservers(){
        homeViewModel.listItems.observe(viewLifecycleOwner,EventObserver(
            onLoading = {
               loading.show()
            },
            onError = {
                loading.hide()
            },
        ){parent ->
             loading.hide()
            parent.items?.let { newItems ->

                val oldCount: Int = items.size
                items.addAll(newItems)
                itemAdapter.homeViewModel=homeViewModel
                itemAdapter.items = items
                itemAdapter.notifyItemRangeChanged(oldCount, items.size)

            }
        })
    }



}