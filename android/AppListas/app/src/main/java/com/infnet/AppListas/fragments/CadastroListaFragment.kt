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
import com.infnet.AppAnimes.databinding.FragmentCadastroListaBinding
import com.infnet.AppAnimes.factory.ViewModelFactory
import com.infnet.AppAnimes.viewModel.ListasViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroListaFragment : Fragment() {

    var _binding:FragmentCadastroListaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroListaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context = requireContext()
        val meudao = AppDatabase.getInstance(context).ListaAnimeDao()
        val viewModelFactory = ViewModelFactory(null,meudao,null)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ListasViewModel::class.java)

        binding.btnCadastrarAnimeNova.setOnClickListener {
            val user = binding.textInputAnime.text.toString()
            if (viewModel.verificaCampo(user)) {
                Toast.makeText(
                    activity,
                    "Por favor, preencha o campo.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                MainScope().launch(Dispatchers.IO) {
                    if (viewModel.cadastrar(user)) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                activity,
                                "Cadastrado com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.cadastroListaToListas)
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




        binding.btnVoltarParaListas.setOnClickListener {
            findNavController().navigate(R.id.cadastroListaToListas)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}