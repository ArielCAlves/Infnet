package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arielcarvalho.android.turmasfirebaseproject.R
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentTurmasBinding
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.TurmaAdapter
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.TurmaComIdAdapter
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.TurmaComIdListener
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters.TurmaListener
import com.arielcarvalho.android.turmasfirebaseproject.models.Turma
import com.arielcarvalho.android.turmasfirebaseproject.models.TurmaComId
import com.arielcarvalho.android.turmasfirebaseproject.utils.nav
import com.arielcarvalho.android.turmasfirebaseproject.utils.toast

class TurmasFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentTurmasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTurmasBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {

        setupViews()

        setupClickListeners()

        setupRecyclerView()

        setupObservers()

        setupClickVoltar()

    }

    private fun setupClickListeners() {
        binding.btnCadastrar.setOnClickListener {
            nav(R.id.action_turmasFragment_to_cadastrarTurmaFragment)
        }
    }

    private fun setupClickVoltar(){
        binding.btnVoltarDeTurmas.setOnClickListener {
            nav(R.id.homeFragment)
        }
    }

    private fun setupViews() {
        activity?.setTitle("cursos")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    // Existe maneira melhor!!!
    //    fun getTurmas() {
    //        viewModel.getTurmas()
    //    }


    val adapter = TurmaComIdAdapter(
        object : TurmaComIdListener {
            override fun onEditClick(turma: TurmaComId) {
                viewModel.setSelectedTurmaComId(turma)
                nav(R.id.action_turmasFragment_to_editarTurmaFragment)
            }

            override fun onDeleteClick(turma: TurmaComId) {
                viewModel.deleteTurma(turma.id)
            }
        }
    )

    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.rvTurmas.adapter = adapter
        binding.rvTurmas.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setupObservers() {
        viewModel.turmasComId.observe(viewLifecycleOwner) {
            atualizaRecyclerView(it)
        }
    }

    fun atualizaRecyclerView(lista: List<TurmaComId>) {
        adapter.submitList(lista)
        binding.rvTurmas.adapter = adapter
    }


}