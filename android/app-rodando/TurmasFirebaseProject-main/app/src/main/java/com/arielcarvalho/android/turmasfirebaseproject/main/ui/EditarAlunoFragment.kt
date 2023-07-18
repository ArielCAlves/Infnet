package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arielcarvalho.android.turmasfirebaseproject.R
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentEditarAlunoBinding
import com.arielcarvalho.android.turmasfirebaseproject.models.Aluno
import com.arielcarvalho.android.turmasfirebaseproject.utils.getIntInput
import com.arielcarvalho.android.turmasfirebaseproject.utils.getTextInput
import com.arielcarvalho.android.turmasfirebaseproject.utils.navUp

class EditarAlunoFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentEditarAlunoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarAlunoBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()

        return view
    }

    private fun setup() {
        setupViews()
        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnAtualizar.setOnClickListener {
            onAtualizarClick()
        }
    }

    private fun onAtualizarClick() {
        val aluno = getAlunoFromInputs()
        viewModel.atualizaAluno(aluno)
        navUp()
    }

    private fun getAlunoFromInputs(): Aluno {
        binding.apply {
            return Aluno(
                nomeEstudante = getTextInput(inputNomeEstudante),
                nro_inscricao = getTextInput(inputNroInscricao),
                idade = getIntInput(inputIdade)
            )
        }
    }

    private fun setupObservers() {
        viewModel.selectedAlunoComId.observe(viewLifecycleOwner){
            binding.apply {
                inputNomeEstudante.setText(it.nomeEstudante)
                inputNroInscricao.setText(it.nro_inscricao)
                inputIdade.setText(it.idade.toString())
            }
        }
    }

    private fun setupViews() {
        activity?.setTitle("Editar")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}