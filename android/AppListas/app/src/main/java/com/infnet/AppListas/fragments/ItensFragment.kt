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
import com.infnet.AppAnimes.adaptor.ListaAnimesAdapter
import com.infnet.AppAnimes.database.AppDatabase
import com.infnet.AppAnimes.databinding.FragmentItensBinding
import com.infnet.AppAnimes.factory.ViewModelFactory
import com.infnet.AppAnimes.model.Item
import com.infnet.AppAnimes.viewModel.ItensViewModel


class ItensFragment : Fragment() {

    private var _binding: FragmentItensBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemAdapter:ListaAnimesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItensBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context = requireContext()
        val meudao = AppDatabase.getInstance(context).AnimeDao()
        val viewModelFactory = ViewModelFactory(null,null,meudao)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ItensViewModel::class.java)
        configureRecyclerView(viewModel)

        binding.btnCadastrarMaisAnimes.setOnClickListener {
            findNavController().navigate(R.id.itensToCadastroItem)
        }

        binding.BtnVoltarDeItens.setOnClickListener {
            findNavController().navigate(R.id.itensVoltarListas)
        }
    }

    private fun configureRecyclerView(viewModel: ItensViewModel) {
        itemAdapter = ListaAnimesAdapter(viewModel.listaItens(),viewModel)
        binding.RecycleViewItens.layoutManager = LinearLayoutManager(activity)
        binding.RecycleViewItens.adapter = itemAdapter
        viewModel.liveItens()?.observe(requireActivity(),itensUpdateObserver)
    }

    private var itensUpdateObserver:Observer<List<Item>> = Observer<List<Item>>(){
        userArrayList -> itemAdapter.updateItensList(userArrayList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}