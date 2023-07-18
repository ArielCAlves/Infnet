package com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arielcarvalho.android.turmasfirebaseproject.databinding.TurmaListItemBinding
import com.arielcarvalho.android.turmasfirebaseproject.models.Turma


class TurmaAdapter(val listener: TurmaListener) :
    ListAdapter<
            Turma,
            TurmaAdapter.ViewHolder
            >(TurmaDiffCallback()) {

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
        val listener: TurmaListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Turma, position: Int) {
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
            fun from(parent: ViewGroup, listener: TurmaListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TurmaListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}


class TurmaDiffCallback : DiffUtil.ItemCallback<Turma>() {

    override fun areItemsTheSame(oldItem: Turma, newItem: Turma): Boolean {
        return oldItem.nomeCurso == newItem.nomeCurso
    }

    override fun areContentsTheSame(oldItem: Turma, newItem: Turma): Boolean {
        return oldItem == newItem
    }
}


interface TurmaListener {
    fun onEditClick(turma: Turma)
    fun onDeleteClick(turma:Turma)
}
