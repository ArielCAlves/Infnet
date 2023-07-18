package com.infnet.AppAnimes.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.infnet.AppAnimes.R
import com.infnet.AppAnimes.adaptor.ListaListasAdepter
import com.infnet.AppAnimes.database.AppDatabase
import com.infnet.AppAnimes.databinding.FragmentListasBinding
import com.infnet.AppAnimes.factory.ViewModelFactory
import com.infnet.AppAnimes.model.Lista
import com.infnet.AppAnimes.viewModel.ListasViewModel

class ListasFragment : Fragment() {

    private var _binding: FragmentListasBinding? = null
    private val binding get() = _binding!!
    private lateinit var listaAdapter:ListaListasAdepter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentListasBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context:Context = requireContext()
        val dao = AppDatabase.getInstance(context).ListaAnimeDao()
        val viewModelFactory = ViewModelFactory(null, dao, null)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ListasViewModel::class.java)
        configureRecyclerView(viewModel)

        binding.BtnCadastrarAnime.setOnClickListener {
            findNavController().navigate(R.id.listasToCadastroLista)
        }

        binding.btnVoltarAnimesLogin.setOnClickListener {
            findNavController().navigate(R.id.listasVoltarLogin)
        }
    }

    private fun configureRecyclerView(viewModel: ListasViewModel) {
        listaAdapter = ListaListasAdepter(viewModel.listaLista(),viewModel,binding.root)
        binding.recyclerViewListadeAnimes.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewListadeAnimes.adapter =  listaAdapter
        viewModel.liveListas()?.observe(requireActivity(),listaUpdateObserver)
    }

    private var listaUpdateObserver: Observer<List<Lista>> =  Observer<List<Lista>>() {
            userArrayList ->
        listaAdapter.updateListalist(userArrayList);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}