package com.infnet.AppAnimes.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.infnet.AppAnimes.model.Usuario


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg usuario:Usuario):LongArray

    @Update
    suspend fun update(vararg usuario: Usuario)

    @Query("DELETE FROM usuario where id = :id")
    suspend fun deleteUsuario(id:Int)

    @Query("SELECT * FROM usuario WHERE name = :nome AND password = :senha")
    suspend fun loginUsuario(nome:String,senha:String):Usuario
}