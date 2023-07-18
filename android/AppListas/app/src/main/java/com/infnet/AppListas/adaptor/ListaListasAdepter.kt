package com.infnet.AppAnimes.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.infnet.AppAnimes.R
import com.infnet.AppAnimes.model.Lista
import com.infnet.AppAnimes.util.setAnimesDTO
import com.infnet.AppAnimes.viewModel.ListasViewModel

class ListaListasAdepter(var listaList:List<Lista>?,val viewModel:ListasViewModel,val view: View): RecyclerView.Adapter<ListaListasAdepter.ListaListasViewHolder>()  {

    inner class ListaListasViewHolder(view:View): RecyclerView.ViewHolder(view){
        val nomeTextView: TextView = itemView.findViewById(R.id.textViewNomeAnimes)
        val btnRemover: Button = itemView.findViewById(R.id.BtnRemoverAnime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaListasViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_card,parent,false)
        return ListaListasViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaListasViewHolder, position: Int) {
        holder.nomeTextView.text = listaList?.get(position)?.nomeLista
        holder.btnRemover.setOnClickListener {
            listaList!!.get(position).id?.let { it1 -> viewModel.deleteLista(it1) }
        }
        holder.nomeTextView.setOnClickListener {
            setAnimesDTO(listaList!!.get(position).id!!)
            view.findNavController().navigate(R.id.listasToItens)
        }
    }

    override fun getItemCount(): Int {
        if (listaList?.isNotEmpty() == true){
            return listaList?.size!!
        }
        return 0
    }

    fun updateListalist(lista:List<Lista>){
        listaList = lista
        notifyDataSetChanged()
    }

}