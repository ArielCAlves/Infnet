package com.infnet.AppAnimes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.infnet.AppAnimes.model.Lista

@Dao
interface ListaAnimeDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg lista: Lista):LongArray

    @Update
    suspend fun update(vararg lista: Lista)

    @Query ("DELETE FROM lista WHERE id = :id")
    suspend fun deleteLista(id:Int)

    @Query ("SELECT * FROM lista where idUsuario = :id")
    fun returnListasUsuario(id: Int): LiveData<List<Lista>>

}