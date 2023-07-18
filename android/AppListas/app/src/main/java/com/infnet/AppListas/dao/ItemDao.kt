package com.infnet.AppAnimes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.infnet.AppAnimes.model.Item


@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg item: Item):LongArray

    @Update
    suspend fun update(vararg item: Item)

    @Query("DELETE FROM item WHERE id = :id")
    suspend fun deleteItem(id:Int)

    @Query("Select * FROM item WHERE idLista = :id")
    fun returnItensLista(id:Int):LiveData<List<Item>>

}