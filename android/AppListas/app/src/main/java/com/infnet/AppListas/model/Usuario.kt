package com.infnet.AppAnimes.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Usuario(@PrimaryKey(autoGenerate = true) val id:Int?=null, val name:String, val password:String)
