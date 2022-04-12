package com.example.mytesting.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.qualifiers.MainThread
import com.example.mytesting.helpers.Event
import com.example.mytesting.helpers.Resource
import com.example.mytesting.model.Item
import com.example.mytesting.model.ResponseGallery
import com.example.mytesting.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val repository: HomeRepository,
    @MainThread
    private val dispatcher: CoroutineDispatcher

): ViewModel() {

    private val _listItems= MutableLiveData<Event<Resource<ResponseGallery>>>()
    val listItems: LiveData<Event<Resource<ResponseGallery>>> =_listItems

    private val _listCashedItems= MutableLiveData<Event<Resource<List<Item>>>>()
    val listCashedItems: LiveData<Event<Resource<List<Item>>>> =_listCashedItems
    val data=repository.getAllCashedItems()

    fun  getAllItems(categoryId: Int) {

        _listItems.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            val result=repository.getListItems(categoryId)
            _listItems.postValue(Event(result))
        }

    }

    //



}