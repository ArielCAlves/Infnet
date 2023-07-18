package com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arielcarvalho.android.turmasfirebaseproject.databinding.AlunoListItemBinding
import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoComId


class AlunoComIdAdapter(val listener: AlunoComIdListener) :
    ListAdapter<
            AlunoComId,
            AlunoComIdAdapter.ViewHolder
            >(AlunoComIdDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    class ViewHolder private constructor(
        val binding: AlunoListItemBinding,
        val listener: AlunoComIdListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlunoComId, position: Int) {
            binding.apply {

                estudanteNome.text = item.nomeEstudante
                nroInscricao.text = item.nro_inscricao
                idade.text = item.idade.toString()

                ivEdit.setOnClickListener {
                    listener.onEditClick(item)
                }
                ivDelete.setOnClickListener {
                    listener.onDeleteClick(item)
                }

            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: AlunoComIdListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlunoListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}


class AlunoComIdDiffCallback : DiffUtil.ItemCallback<AlunoComId>() {

    override fun areItemsTheSame(oldItem: AlunoComId, newItem: AlunoComId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlunoComId, newItem: AlunoComId): Boolean {
        return oldItem == newItem
    }
}


interface AlunoComIdListener {
    fun onEditClick(aluno: AlunoComId)
    fun onDeleteClick(aluno: AlunoComId)
}