package com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arielcarvalho.android.turmasfirebaseproject.databinding.TurmaListItemBinding
import com.arielcarvalho.android.turmasfirebaseproject.models.TurmaComId


class TurmaComIdAdapter(val listener: TurmaComIdListener) :
    ListAdapter<
            TurmaComId,
            TurmaComIdAdapter.ViewHolder
            >(TurmaComIdDiffCallback()) {

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
        val binding: TurmaListItemBinding,
        val listener: TurmaComIdListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TurmaComId, position: Int) {
            binding.apply {
                turmaNome.text = item.nomeCurso
                turmaProfessor.text = item.nomeInstrutor
                turmaHoraDaAula.text = item.hora_da_aula

                ivEdit.setOnClickListener {
                    listener.onEditClick(item)
                }
                ivDelete.setOnClickListener {
                    listener.onDeleteClick(item)
                }

            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: TurmaComIdListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TurmaListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}


class TurmaComIdDiffCallback : DiffUtil.ItemCallback<TurmaComId>() {

    override fun areItemsTheSame(oldItem: TurmaComId, newItem: TurmaComId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TurmaComId, newItem: TurmaComId): Boolean {
        return oldItem == newItem
    }
}


interface TurmaComIdListener {
    fun onEditClick(turma: TurmaComId)
    fun onDeleteClick(turma: TurmaComId)
}