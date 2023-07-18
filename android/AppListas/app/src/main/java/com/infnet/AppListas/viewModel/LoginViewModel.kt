package com.infnet.AppAnimes.viewModel

import androidx.lifecycle.ViewModel
import com.infnet.AppAnimes.dao.UserDao
import com.infnet.AppAnimes.dto.UserDTO
import com.infnet.AppAnimes.util.setUserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async


class LoginViewModel(private val dao: UserDao?): ViewModel() {



    fun verificaCampos(nome: String,senha: String):Boolean{
        return nome == "" || senha == ""
    }

    suspend fun Login(nome:String, senha:String):Boolean{
        return MainScope().async(Dispatchers.IO) {
            val usuario = dao!!.loginUsuario(nome,senha)
            if (usuario != null){
                setUserDTO(usuario.name,usuario.id!!)
                return@async true
            } else {
                return@async false
            }
        }.await()
    }

}