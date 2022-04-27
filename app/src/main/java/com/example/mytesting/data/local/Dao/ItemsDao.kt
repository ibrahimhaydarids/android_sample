package com.example.mytesting.data.local.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytesting.model.Item


@Dao
interface ItemsDao {

    @Query("SELECT * FROM item_table ORDER BY id DESC LIMIT 10")
    fun getAllCashedItems(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item : Item):Long

    @Query("DELETE FROM item_table WHERE id = :id")
    suspend fun delete(id : Int):Int

    @Query("select id from item_table")
    fun getIds():LiveData<List<Int>>

    @Query("SELECT * FROM item_table WHERE name LIKE :keyword OR categoryName LIKE :keyword")
    suspend fun searchItems(keyword :String): List<Item>
}