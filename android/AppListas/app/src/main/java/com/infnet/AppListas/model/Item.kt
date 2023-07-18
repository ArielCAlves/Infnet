package com.infnet.AppAnimes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(@PrimaryKey(autoGenerate = true) val id:Int? = null,val idLista:Int, val nomeItem:String, val quantidade:Int)
