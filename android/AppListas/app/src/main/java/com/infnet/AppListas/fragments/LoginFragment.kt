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
import com.infnet.AppAnimes.databinding.FragmentLoginBinding
import com.infnet.AppAnimes.factory.ViewModelFactory
import com.infnet.AppAnimes.viewModel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context = requireContext()
        val dao = AppDatabase.getInstance(context).UserDao()
        val viewModelFactory = ViewModelFactory(dao, null, null)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.btnLogar.setOnClickListener {
            val user = binding.textInputUser.text.toString()
            val password = binding.textInputPassword.text.toString()
            if (viewModel.verificaCampos(user,password)){
                Toast.makeText(
                    activity,
                    "Favor preencher os campos!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                MainScope().launch(Dispatchers.IO) {
                    if (viewModel.Login(user, password)) {
                        withContext(Dispatchers.Main) {
                            findNavController().navigate(R.id.loginToListas)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                activity,
                                "Usuário ou senha incorreto",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }

        binding.btnCadastrar.setOnClickListener {
            findNavController().navigate(R.id.loginToCadastroUsuario)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}