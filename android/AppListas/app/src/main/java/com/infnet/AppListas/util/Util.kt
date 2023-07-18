package com.infnet.AppAnimes.util

import com.infnet.AppAnimes.dto.AnimesDTO
import com.infnet.AppAnimes.dto.UserDTO

lateinit var UserDTO: UserDTO

lateinit var AnimesDTO: AnimesDTO

fun setUserDTO(nome:String,id:Int){
    UserDTO = UserDTO(nome,id)
}

fun setAnimesDTO(id:Int){
    AnimesDTO = AnimesDTO(id)
}
