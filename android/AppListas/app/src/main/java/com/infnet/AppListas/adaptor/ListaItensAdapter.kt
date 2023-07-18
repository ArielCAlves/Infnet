package com.infnet.AppAnimes.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.infnet.AppAnimes.R
import com.infnet.AppAnimes.model.Item
import com.infnet.AppAnimes.viewModel.ItensViewModel


class ListaAnimesAdapter(var itensList:List<Item>, val viewModel: ItensViewModel):RecyclerView.Adapter<ListaAnimesAdapter.ListaItensViewHolder>(){

    inner class ListaItensViewHolder(view:View): RecyclerView.ViewHolder(view){
        val nomeTextView:TextView = itemView.findViewById(R.id.textViewNomedoAnime)
        val quantidadeTextView:TextView = itemView.findViewById(R.id.textViewAssistidoAnime)
        val btnDelete:Button = itemView.findViewById(R.id.BtnDeletaAnime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaItensViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        return ListaItensViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaItensViewHolder, position: Int) {
        holder.nomeTextView.text = itensList?.get(position)?.nomeItem
        holder.quantidadeTextView.text = itensList?.get(position)?.quantidade.toString()
        holder.btnDelete.setOnClickListener {
            itensList!!.get(position).id?.let { it1 -> viewModel.deleteItem(it1) }
        }
    }

    override fun getItemCount(): Int {
        if(itensList?.isNotEmpty() == true){
            return itensList?.size!!
        }
        return 0
    }

    fun updateItensList(lista:List<Item>){
        itensList = lista
        notifyDataSetChanged()
    }

}