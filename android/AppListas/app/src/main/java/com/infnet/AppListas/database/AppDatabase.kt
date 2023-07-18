package com.infnet.AppAnimes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.infnet.AppAnimes.dao.AnimeDao
import com.infnet.AppAnimes.dao.ListaAnimeDao
import com.infnet.AppAnimes.dao.UserDao
import com.infnet.AppAnimes.model.Item
import com.infnet.AppAnimes.model.Lista
import com.infnet.AppAnimes.model.Usuario

@Database(entities = arrayOf(Usuario::class, Lista::class, Item::class),version=1,exportSchema=false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun UserDao(): UserDao
    abstract fun ListaAnimeDao(): ListaAnimeDao
    abstract fun AnimeDao(): AnimeDao

    companion object {
        private var appDatabase:AppDatabase? = null

        fun getInstance(context:Context): AppDatabase {
            if (appDatabase == null){
                appDatabase = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "AppDatabase").build()
            }
            return appDatabase!!
        }
    }
}