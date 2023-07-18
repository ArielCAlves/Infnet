package com.arielcarvalho.android.turmasfirebaseproject.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.MainActivity
import com.arielcarvalho.android.turmasfirebaseproject.R
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentSignInBinding
import com.arielcarvalho.android.turmasfirebaseproject.utils.getTextInput
import com.arielcarvalho.android.turmasfirebaseproject.utils.nav
import com.arielcarvalho.android.turmasfirebaseproject.utils.toast

class SignInFragment : Fragment() {

    val viewModel by activityViewModels<LoginViewModel>()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Usar a vinculação de visualizações em fragmentos
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#fragments

    private var _binding: FragmentSignInBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // setup ///////////////////////////////////////////////////////////////////////////////////////

    private fun setup() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {

            btnSignIn.setOnClickListener {
                onSignInClick()
            }

            btnSignOn.setOnClickListener {
                onSignOnClick()
            }

        }
    }


    private fun onSignOnClick() {
        nav(R.id.action_signInFragment_to_signOnFragment)
    }

    private fun onSignInClick() {
        val email = getTextInput(binding.inputEmail)
        val password = getTextInput(binding.inputPassword)
        signIn(email, password)

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    private fun signIn(email: String, password: String){
        viewModel.login(email, password)
            .addOnSuccessListener {
                toast("Logado com Sucesso")
                startMainActivity()
            }
            .addOnFailureListener {
                toast("Falha ao Logar\n${it.message}")
            }
    }

    fun startMainActivity(){
        startActivity(Intent(requireContext(), MainActivity::class.java))
        activity?.finish()
    }


}