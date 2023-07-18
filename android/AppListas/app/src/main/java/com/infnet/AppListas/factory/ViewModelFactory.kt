package com.infnet.AppAnimes.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infnet.AppAnimes.dao.AnimeDao
import com.infnet.AppAnimes.dao.ListaAnimeDao
import com.infnet.AppAnimes.dao.UserDao
import com.infnet.AppAnimes.viewModel.*

class ViewModelFactory(private val UserDao: UserDao?,private val listasDao: ListaAnimeDao?, private val itensDao: AnimeDao?): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(UserDao) as T
        } else if (modelClass.isAssignableFrom(ListasViewModel::class.java)){
            return ListasViewModel(listasDao) as T
        } else if (modelClass.isAssignableFrom(ItensViewModel::class.java)) {
            return ItensViewModel(itensDao) as T
        }else if (modelClass.isAssignableFrom(CadastroUsuarioViewModel::class.java)) {
            return CadastroUsuarioViewModel(UserDao) as T
        }
        throw IllegalArgumentException("ViewModel ainda não existe.")
    }
}