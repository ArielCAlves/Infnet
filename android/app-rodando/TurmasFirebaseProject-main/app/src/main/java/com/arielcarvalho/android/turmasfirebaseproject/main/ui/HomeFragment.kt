package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arielcarvalho.android.turmasfirebaseproject.R
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentHomeBinding
import com.arielcarvalho.android.turmasfirebaseproject.utils.nav

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()
        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        activity?.setTitle("Home")
    }


    private fun setupClickListeners() {
        binding.apply {
            btnTurmas.setOnClickListener {
                nav(R.id.action_homeFragment_to_turmasFragment)
            }

            btnEstudantes.setOnClickListener {
                nav(R.id.action_homeFragment_to_alunosFragment)
            }

            btnCrypto.setOnClickListener{
                nav(R.id.action_homeFragment_to_crypto)
            }

            btnLocalizar.setOnClickListener {
                nav(R.id.action_homeFragment_to_localizacao)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}