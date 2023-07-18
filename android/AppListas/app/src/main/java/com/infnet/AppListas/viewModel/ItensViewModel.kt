package com.infnet.AppAnimes.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infnet.AppAnimes.dao.AnimeDao
import com.infnet.AppAnimes.model.Item
import com.infnet.AppAnimes.util.AnimesDTO
import com.infnet.AppAnimes.util.UserDTO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ItensViewModel(val dao: AnimeDao?): ViewModel() {

    private var _itemLista = mutableListOf<Item>()
    private var itens = dao!!.returnItensLista(AnimesDTO.id)

    fun liveItens():LiveData<List<Item>>{
        return itens
    }

    fun listaItens():List<Item>{
        itens = dao!!.returnItensLista(AnimesDTO.id)
        if (itens.value?.isNotEmpty() == true){
            _itemLista = itens.value as MutableList<Item>
        }
        return _itemLista
    }

    fun deleteItem(id:Int){
        viewModelScope.launch {
            dao!!.deleteItem(id)
        }
        listaItens()
    }

    fun verificarCampo(nome:String,quantidade:String):Boolean{
        return nome == "" || quantidade == ""
    }

    suspend fun cadastrar(nome:String, quantidade: String):Boolean{
        return viewModelScope.async {
            val response = async { dao!!.insert(Item(null, AnimesDTO.id,nome,quantidade.toInt()))}.await()
            return@async response.isNotEmpty()
        }.await()
    }

}