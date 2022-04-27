package com.example.mytesting.repositories

import androidx.lifecycle.LiveData

import com.example.myapplication.qualifiers.IOThread
import com.example.mytesting.data.local.Dao.ItemsDao
import com.example.mytesting.data.network.ApiService

import com.example.mytesting.helpers.Resource
import com.example.mytesting.model.Item
import com.example.mytesting.model.ResponseGallery
import com.example.mytesting.utils.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService,
    private val itemsDao: ItemsDao,
    @IOThread
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getListItems(categoryId: Int):Resource<ResponseGallery> = withContext(dispatcher){
        safeCall {
            val result=apiService.getGallery(categoryId)
            Resource.Success(result)
        }
    }


  suspend  fun insertFavoriteItem(item: Item): Resource<Long> = withContext(dispatcher){
      safeCall {
        val result=itemsDao.insertItem(item)
          Resource.Success(result)
      }
  }
    suspend  fun deleteFavorite(item: Item): Resource<Int> = withContext(dispatcher){
      safeCall {
        val result=itemsDao.delete(item.id)
          Resource.Success(result)
      }
  }

     fun   getAllCashedItems(): LiveData<List<Item>> = itemsDao.getAllCashedItems()

    fun   getIdsList(): LiveData<List<Int>> = itemsDao.getIds()

}