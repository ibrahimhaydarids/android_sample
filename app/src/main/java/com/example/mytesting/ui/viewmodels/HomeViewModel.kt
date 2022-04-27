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

    private val _insertFavoriteItem= MutableLiveData<Event<Resource<Long>>>()
    val insertFavoriteItem: LiveData<Event<Resource<Long>>> =_insertFavoriteItem

    private val _deleteFavorite= MutableLiveData<Event<Resource<Int>>>()
    val deleteFavorite: LiveData<Event<Resource<Int>>> =_deleteFavorite

    val data=repository.getAllCashedItems()
    val dataIds=repository.getIdsList()


    fun  getAllItems(categoryId: Int) {

        _listItems.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            val result=repository.getListItems(categoryId)
            _listItems.postValue(Event(result))
        }

    }
    

    fun insertFavoriteItems(item: Item) {

        _insertFavoriteItem.postValue(Event(Resource.Loading()))
        viewModelScope.launch (dispatcher){
            val result=repository.insertFavoriteItem(item)
            _insertFavoriteItem.postValue(Event(result))

        }
   }


    fun  deleteFavorite(item: Item) {

        _deleteFavorite.postValue(Event(Resource.Loading()))

        viewModelScope.launch(dispatcher) {
            val result=repository.deleteFavorite(item)
            _deleteFavorite.postValue(Event(result))
        }

    }

    override fun onCleared() {
        super.onCleared()
    }

}