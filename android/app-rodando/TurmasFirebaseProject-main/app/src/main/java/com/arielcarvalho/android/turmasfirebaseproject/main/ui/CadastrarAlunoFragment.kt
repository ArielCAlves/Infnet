package com.arielcarvalho.android.Alunosfirebaseproject.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentCadastrarAlunoBinding
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.MainViewModel
import com.arielcarvalho.android.turmasfirebaseproject.models.Aluno
import com.arielcarvalho.android.turmasfirebaseproject.utils.getIntInput
import com.arielcarvalho.android.turmasfirebaseproject.utils.getTextInput
import com.arielcarvalho.android.turmasfirebaseproject.utils.navUp
import com.arielcarvalho.android.turmasfirebaseproject.utils.toast

class CadastrarAlunoFragment : Fragment() {


    val viewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentCadastrarAlunoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastrarAlunoBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnCadastrar.setOnClickListener {
            onCadastrarClick()
        }
    }

    private fun onCadastrarClick() {
        val aluno = getAlunoFromInputs()

        viewModel.cadastrarAluno(aluno)
            .addOnSuccessListener { documentReference ->
                toast("Cadastrado com sucesso com id: ${documentReference.id}")
                navUp()
            }
            .addOnFailureListener { e ->
                toast("Falha ao cadastrar")
            }
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

    private fun setupViews() {
        activity?.setTitle("Cadastrar Aluno")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}