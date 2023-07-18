package com.infnet.AppAnimes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infnet.AppAnimes.dao.ListaAnimeDao
import com.infnet.AppAnimes.model.Lista
import com.infnet.AppAnimes.util.UserDTO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListasViewModel(val dao: ListaAnimeDao?): ViewModel() {

    private var _listaListas = mutableListOf<Lista>()
    private var listas = dao!!.returnListasUsuario(UserDTO.id)

    fun liveListas(): LiveData<List<Lista>>{
       return listas
    }

    fun listaLista():List<Lista>{
        listas = dao!!.returnListasUsuario(UserDTO.id)
        if (listas.value?.isNotEmpty() == true){
            _listaListas = listas.value as MutableList<Lista>
        }
        return _listaListas
    }


    fun deleteLista(id:Int){
        viewModelScope.launch{
            dao!!.deleteLista(id)
        }
        listaLista()
    }

    // ******************************************* PARTE DO CADASTRO  *******************************************

    fun verificaCampo(nome:String):Boolean{
        return nome == ""
    }

    suspend fun cadastrar(nome: String): Boolean {
        return viewModelScope.async {
            val response = async { dao!!.insert(Lista(null, UserDTO.id,nome)) }.await()
            return@async response.isNotEmpty()
        }.await()
    }
}