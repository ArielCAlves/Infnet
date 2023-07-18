package com.infnet.AppAnimes.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infnet.AppAnimes.R
import com.infnet.AppAnimes.database.AppDatabase
import com.infnet.AppAnimes.databinding.FragmentCadastroUsuarioBinding
import com.infnet.AppAnimes.factory.ViewModelFactory
import com.infnet.AppAnimes.viewModel.CadastroUsuarioViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CadastroUsuarioFragment : Fragment() {

    private var _binding: FragmentCadastroUsuarioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroUsuarioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context:Context = requireContext()
        val dao = AppDatabase.getInstance(context).UserDao()
        val viewModelFactory = ViewModelFactory(dao,null,null,)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CadastroUsuarioViewModel::class.java)

        binding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.cadastroUsuarioToLogin)
        }

        binding.btnCadastrarNovoUser.setOnClickListener {
            val nome = binding.textInputNovoUser.text.toString()
            val senha = binding.textInputNovaSenha.text.toString()
            MainScope().launch(Dispatchers.IO){
                if (viewModel.cadastrar(nome,senha)){
                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            activity,
                            "Cadastrado com sucesso!!",
                            Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.cadastroUsuarioToLogin)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            activity,
                            "Os campos estão errados, tente novamente!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}