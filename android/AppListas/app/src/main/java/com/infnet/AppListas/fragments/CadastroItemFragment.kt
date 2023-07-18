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
import com.infnet.AppAnimes.databinding.FragmentCadastroItemBinding
import com.infnet.AppAnimes.factory.ViewModelFactory
import com.infnet.AppAnimes.viewModel.ItensViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CadastroItemFragment : Fragment() {

    private var _binding: FragmentCadastroItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context = requireContext()
        val meudao = AppDatabase.getInstance(context).AnimeDao()
        val viewModelFactory = ViewModelFactory(null,null,meudao)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ItensViewModel::class.java)

        binding.btnVoltarParaLista.setOnClickListener {
            findNavController().navigate(R.id.cadastroItemToItens)
        }

        binding.btnCadastrarAnimeNovo.setOnClickListener {
            val user = binding.textInputNomeAnime.text.toString()
            val assistido = binding.textInputAssistido.text.toString()
            if (viewModel.verificarCampo(user,assistido)){
                Toast.makeText(
                    activity,
                    "Por favor, preencha os campos!",
                    Toast.LENGTH_LONG
                ).show()
            } else{
                MainScope().launch(Dispatchers.IO){
                    if (viewModel.cadastrar(user,assistido)){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                activity,
                                "Cadastrado com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.cadastroItemToItens)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                activity,
                                "Verifique os campos e tente novamente!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
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