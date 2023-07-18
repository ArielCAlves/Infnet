package com.infnet.AppAnimes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lista(@PrimaryKey(autoGenerate = true) val id:Int? = null,val idUsuario:Int,val nomeLista:String)
