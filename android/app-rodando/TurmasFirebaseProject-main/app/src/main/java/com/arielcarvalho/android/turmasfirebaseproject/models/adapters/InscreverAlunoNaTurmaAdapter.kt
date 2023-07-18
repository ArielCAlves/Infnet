package com.arielcarvalho.android.turmasfirebaseproject.main.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arielcarvalho.android.turmasfirebaseproject.databinding.InscreverAlunoNaTurmaListItemBinding
import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoComId


class InscreverAlunoNaTurmaAdapter(val listener: InscreverAlunoNaTurmaListener) :
    ListAdapter<
            AlunoComId,
            InscreverAlunoNaTurmaAdapter.ViewHolder
            >(InscreverAlunoNaTurmaDiffCallback()) {

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
        val binding: InscreverAlunoNaTurmaListItemBinding,
        val listener: InscreverAlunoNaTurmaListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlunoComId, position: Int) {
            binding.apply {

                estudanteNome.text = item.nomeEstudante
                nroInscricao.text = item.nro_inscricao

                ivAdd.setOnClickListener{
                    listener.onAddClick(item)
                }

            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: InscreverAlunoNaTurmaListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = InscreverAlunoNaTurmaListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}


class InscreverAlunoNaTurmaDiffCallback : DiffUtil.ItemCallback<AlunoComId>() {

    override fun areItemsTheSame(oldItem: AlunoComId, newItem: AlunoComId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlunoComId, newItem: AlunoComId): Boolean {
        return oldItem == newItem
    }
}


interface InscreverAlunoNaTurmaListener {
    fun onAddClick(aluno: AlunoComId)
}