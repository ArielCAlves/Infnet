package com.infnet.AppAnimes.viewModel

import androidx.lifecycle.ViewModel
import com.infnet.AppAnimes.dao.UserDao
import com.infnet.AppAnimes.model.Usuario
import kotlinx.coroutines.*

class CadastroUsuarioViewModel(private val dao: UserDao?): ViewModel() {

    fun validaCadastro(nome:String,senha: String):Boolean{
        return nome != "" && senha != ""
    }

    suspend fun cadastrar(nome: String, senha: String): Boolean {
        return MainScope().async(Dispatchers.IO){
            if (validaCadastro(nome,senha)) {
                val response = async { dao!!.insert(Usuario(null, nome, senha)) }.await()
                return@async response.isNotEmpty()
            } else {
                return@async false
            }
        }.await()
    }
}