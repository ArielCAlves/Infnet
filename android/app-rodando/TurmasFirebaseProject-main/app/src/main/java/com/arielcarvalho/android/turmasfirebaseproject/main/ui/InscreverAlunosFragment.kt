package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.arielcarvalho.android.turmasfirebaseproject.R
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentAlunosBinding
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentInscreverAlunosBinding
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.AlunoComIdAdapter
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.AlunoComIdListener
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.InscreverAlunoNaTurmaAdapter
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.InscreverAlunoNaTurmaListener
import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoComId
import com.arielcarvalho.android.turmasfirebaseproject.utils.nav
import com.arielcarvalho.android.turmasfirebaseproject.utils.toast

class InscreverAlunosFragment : Fragment() {

    val TAG = "InscreverAlunosFragment"

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentInscreverAlunosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInscreverAlunosBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    val adapter = InscreverAlunoNaTurmaAdapter(
        object : InscreverAlunoNaTurmaListener {
            override fun onAddClick(aluno: AlunoComId) {
                inscreverAlunoNaTurma(aluno)
            }
        }
    )

    private fun inscreverAlunoNaTurma(aluno: AlunoComId) {
        viewModel.inscreverAlunoNaTurma(aluno)
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
        setupRecyclerView()
        setupObservers()

    }

    private fun setupClickListeners() {

        binding.tilPesquisaEstudante.setEndIconOnClickListener {
            filtrarLista(binding.inputPesquisaEstudante.text.toString())
        }

    }

    private fun filtrarLista(query: String) {
        val listaAntiga = viewModel.alunosComId.value
        val listaNova = mutableListOf<AlunoComId>()

        listaAntiga?.forEach {
            if (it.nomeEstudante.contains(query)){
                listaNova.add(it)
            }
        }

        atualizaRecyclerView(listaNova)

    }

    private fun onSearchClick() {
        toast("Pesquisa pelo aluno: ${binding.inputPesquisaEstudante.text.toString()}")
    }

    private fun setupViews() {
        activity?.setTitle("Increver aluno na turma")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.rvInscreverEstudantesNaTurma.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setupObservers() {
        viewModel.alunosComId.observe(viewLifecycleOwner) {
            atualizaRecyclerView(it)
        }
    }

    fun atualizaRecyclerView(lista: List<AlunoComId>) {
        adapter.submitList(lista)
        binding.rvInscreverEstudantesNaTurma.adapter = adapter
    }




}