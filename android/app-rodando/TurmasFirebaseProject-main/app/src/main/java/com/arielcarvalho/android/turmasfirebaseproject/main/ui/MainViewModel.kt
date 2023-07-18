package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject
import com.arielcarvalho.android.turmasfirebaseproject.models.Aluno
import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoComId
import com.arielcarvalho.android.turmasfirebaseproject.models.Turma
import com.arielcarvalho.android.turmasfirebaseproject.models.TurmaComId
import com.arielcarvalho.android.turmasfirebaseproject.repository.TurmasRepository

class MainViewModel : ViewModel() {

    val TAG = "ViewModel"
    val repository = TurmasRepository.get()

    fun getCurrentUserEmail(): String {
        return repository.getCurrentUser()?.email ?: "Email não encontrado"
    }

    fun logout() {
        repository.logout()
    }

    fun cadastrarTurma(turma: Turma): Task<DocumentReference> {
        return repository.cadastrarTurma(turma)
    }

    // vários documentos em uma coleção
    // https://firebase.google.com/docs/firestore/query-data/listen?hl=pt&authuser=0#listen_to_multiple_documents_in_a_collection
    fun observeColecaoTurmas() {

        repository.getTurmasColecao()
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }

                val listaInput = mutableListOf<TurmaComId>()

                val listaRemocao = mutableListOf<String>()

                val listaModificacao = mutableListOf<TurmaComId>()

                // Ver alterações entre instantâneos
                // https://firebase.google.com/docs/firestore/query-data/listen?hl=pt&authuser=0#view_changes_between_snapshots
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {

                        // Documento adicionado
                        DocumentChange.Type.ADDED -> {

                            val turma = dc.document.toObject<Turma>()
                            val id = dc.document.id
                            val turmaComId = turmaToTurmaComId(turma, id)

                            Log.i(TAG, "turmaComId: ${turmaComId}")
                            listaInput.add(turmaComId)

                        }

                        // Documento modificado
                        DocumentChange.Type.MODIFIED -> {
                            val turma = dc.document.toObject<Turma>()
                            val id = dc.document.id
                            val turmaComId = turmaToTurmaComId(turma, id)

                            Log.i(TAG, "Modificacao - turmaComId: ${turmaComId}")
                            listaModificacao.add(turmaComId)
                        }

                        // Documento removido
                        DocumentChange.Type.REMOVED -> {
                            val id = dc.document.id
                            Log.i(TAG, "id removido: ${id}")
                            listaRemocao.add(dc.document.id)

                        }
                    }
                }

                addListaToTurmasComId(listaInput)
                removeFromTurmasComId(listaRemocao)
                modifyInTurmasComId(listaModificacao)
            }
    }

    fun modifyItemInListaTurmasComId(itemModificado: TurmaComId) {
        val listaAntiga = turmasComId.value
        val listaNova = mutableListOf<TurmaComId>()

        listaAntiga?.forEach { itemDaLista ->
            if (itemModificado.id == itemDaLista.id) {
                listaNova.add(itemModificado)
            } else {
                listaNova.add(itemDaLista)
            }
        }
        setTurmasComId(listaNova)
    }

    private fun modifyInTurmasComId(listaModificacao: List<TurmaComId>) {
        Log.i(TAG, "listaModificacao: ${listaModificacao}")
        if (listaModificacao.isNotEmpty()) {
            for (itemModificado in listaModificacao) {
                modifyItemInListaTurmasComId(itemModificado)
            }
        }
    }

    private fun removeFromTurmasComId(listaRemocao: List<String>) {

        val listaAntiga = turmasComId.value

        val listaNova = mutableListOf<TurmaComId>()

        Log.i(TAG, "listaRemocao: ${listaRemocao}")

        if (listaRemocao.isNotEmpty()) {
            listaAntiga?.forEach {
                Log.i(TAG, "item da lista Antiga: ${it.id}")
                if (it.id in listaRemocao) {
                    Log.i(TAG, "item ${it.id} está dentro da listaRemocao")

                    //listaNova.add(it)
                } else {
                    Log.i(TAG, "item ${it.id} _NÃO_ está dentro da listaRemocao")

                    listaNova.add(it)
                }
            }
            setTurmasComId(listaNova)
        }


    }

    fun addListaToTurmasComId(listaInput: List<TurmaComId>) {
        val listaAntiga = turmasComId.value

        val listaNova = mutableListOf<TurmaComId>()

        listaAntiga?.forEach {
            listaNova.add(it)
        }

        listaInput.forEach {
            listaNova.add(it)
        }

        setTurmasComId(listaNova)


    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Esses métodos não estão sendo usados porque fazem um novo download //////////////////////////
    // a cada vez que são chamados /////////////////////////////////////////////////////////////////

    fun getTurmas(): List<Turma> {
        val lista = mutableListOf<Turma>()
        repository.getTurmas()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val turma = document.toObject<Turma>()
                    lista.add(turma)
                    Log.i(TAG, "document: ${document}")
                    Log.i(TAG, "turma: ${turma}")
                }
                setTurmas(lista)
            }
            .addOnFailureListener { exception ->

            }
        return lista
    }

    fun addListaToTurmas(listaInput: List<Turma>) {
        val listaAntiga = cursos.value
        val listaNova = mutableListOf<Turma>()

        listaAntiga?.forEach {
            listaNova.add(it)
        }

        listaInput.forEach {
            listaNova.add(it)
        }

        setTurmas(listaNova)

    }

    fun addToTurmas(turma: Turma) {
        val lista = cursos.value
        val listaNova = mutableListOf<Turma>()
        lista?.forEach {
            listaNova.add(it)
        }
        listaNova.add(turma)
        setTurmas(listaNova)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // cursos //////////////////////////////////////////////////////////////////////////////////////
    private val _turmas = MutableLiveData<List<Turma>>()
    val cursos: LiveData<List<Turma>> = _turmas
    fun setTurmas(value: List<Turma>) {
        _turmas.postValue(value)
    }

    private val _turmasComId = MutableLiveData<List<TurmaComId>>()
    val turmasComId: LiveData<List<TurmaComId>> = _turmasComId
    fun setTurmasComId(value: List<TurmaComId>) {
        _turmasComId.postValue(value)
    }

    fun turmaToTurmaComId(turma: Turma, id: String): TurmaComId {
        return TurmaComId(
            nomeInstrutor = turma.nomeInstrutor,
            nomeCurso = turma.nomeCurso,
            hora_da_aula = turma.hora_da_aula,
            id = id
        )
    }

    fun deleteTurma(id: String) {
        repository.deleteTurma(id)
    }

    private val _selectedTurmaComId = MutableLiveData<TurmaComId>()
    val selectedTurmaComId: LiveData<TurmaComId> = _selectedTurmaComId
    fun setSelectedTurmaComId(value: TurmaComId) {
        _selectedTurmaComId.postValue(value)
    }

    fun atualizaTurma(turma: Turma) {
        repository.atualizaTurma(selectedTurmaComId.value?.id, turma)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // estudantes //////////////////////////////////////////////////////////////////////////////////////
    private val _alunosComId = MutableLiveData<List<AlunoComId>>()
    val alunosComId: LiveData<List<AlunoComId>> = _alunosComId
    fun setAlunosComId(value: List<AlunoComId>) {
        Log.i(TAG, "setAlunosComId" )
        Log.i(TAG, "value = ${value}" )
        _alunosComId.postValue(value)
    }

    // Ouvir vários documentos em uma coleção
    // https://firebase.google.com/docs/firestore/query-data/listen?hl=pt&authuser=0#listen_to_multiple_documents_in_a_collection
    fun observeColecaoAlunos() {

        repository.getAlunosColecao()
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w(TAG, "listen:error", e)
                    return@addSnapshotListener
                }

                val listaInput = mutableListOf<AlunoComId>()

                val listaRemocao = mutableListOf<String>()

                val listaModificacao = mutableListOf<AlunoComId>()

                // Ver alterações entre instantâneos
                // https://firebase.google.com/docs/firestore/query-data/listen?hl=pt&authuser=0#view_changes_between_snapshots
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {

                        // Documento adicionado
                        DocumentChange.Type.ADDED -> {

                            val aluno = dc.document.toObject<Aluno>()
                            val id = dc.document.id
                            val alunoComId = alunoToAlunoComId(aluno, id)

                            Log.i(TAG, "alunoComId: ${alunoComId}")
                            listaInput.add(alunoComId)

                        }

                        // Documento modificado
                        DocumentChange.Type.MODIFIED -> {
                            val aluno = dc.document.toObject<Aluno>()
                            val id = dc.document.id
                            val alunoComId = alunoToAlunoComId(aluno, id)

                            Log.i(TAG, "Modificacao - turmaComId: ${alunoComId}")
                            listaModificacao.add(alunoComId)
                        }

                        // Documento removido
                        DocumentChange.Type.REMOVED -> {
                            val id = dc.document.id
//                            Log.i(TAG, "id removido: ${id}")
                            listaRemocao.add(dc.document.id)

                        }
                    }
                }

                addListaToAlunosComId(listaInput)
                removeFromAlunosComId(listaRemocao)
                modifyInAlunosComId(listaModificacao)
            }
    }

    private fun alunoToAlunoComId(aluno: Aluno, id: String): AlunoComId {
        return AlunoComId(
            nomeEstudante = aluno.nomeEstudante,
            nro_inscricao = aluno.nro_inscricao,
            idade = aluno.idade,
            id=id
        )
    }

    fun addListaToAlunosComId(listaInput: List<AlunoComId>) {

        val listaAntiga = alunosComId.value

        val listaNova = mutableListOf<AlunoComId>()

        listaAntiga?.forEach {
            listaNova.add(it)
        }

        listaInput.forEach {
            listaNova.add(it)
        }

        setAlunosComId(listaNova)
    }

    fun modifyItemInListaAlunosComId(itemModificado: AlunoComId) {
        val listaAntiga = alunosComId.value
        val listaNova = mutableListOf<AlunoComId>()

        listaAntiga?.forEach { itemDaLista ->
            if (itemModificado.id == itemDaLista.id) {
                listaNova.add(itemModificado)
            } else {
                listaNova.add(itemDaLista)
            }
        }
        setAlunosComId(listaNova)
    }

    private fun modifyInAlunosComId(listaModificacao: List<AlunoComId>) {
        Log.i(TAG, "listaModificacao: ${listaModificacao}")
        if (listaModificacao.isNotEmpty()) {
            for (itemModificado in listaModificacao) {
                modifyItemInListaAlunosComId(itemModificado)
            }
        }
    }

    private fun removeFromAlunosComId(listaRemocao: List<String>) {

        val listaAntiga = alunosComId.value

        val listaNova = mutableListOf<AlunoComId>()

        Log.i(TAG, "listaRemocao: ${listaRemocao}")

        if (listaRemocao.isNotEmpty()) {
            listaAntiga?.forEach {
                Log.i(TAG, "item da lista Antiga: ${it.id}")
                if (it.id in listaRemocao) {
                    Log.i(TAG, "item ${it.id} está dentro da listaRemocao")

                    //listaNova.add(it)
                } else {
                    Log.i(TAG, "item ${it.id} _NÃO_ está dentro da listaRemocao")
                    listaNova.add(it)
                }
            }
            setAlunosComId(listaNova)
        }


    }

    fun cadastrarAluno(aluno: Aluno): Task<DocumentReference> {
        return repository.cadastrarAluno(aluno)
    }

    private val _selectedAlunoComId = MutableLiveData<AlunoComId>()
    val selectedAlunoComId: LiveData<AlunoComId> = _selectedAlunoComId
    fun setSelectedAlunoComId(value: AlunoComId) {
        _selectedAlunoComId.postValue(value)
    }

    fun atualizaAluno(aluno: Aluno) {
        repository.atualizaAluno(selectedAlunoComId.value?.id, aluno)
    }

    fun deleteAluno(alunoComId: AlunoComId): Task<Void> {
        return repository.deleteAluno(alunoComId.id)
    }


    fun inscreverAlunoNaTurma(alunoComId: AlunoComId){
        repository.inscreverAlunoNaTurma(
            selectedTurmaComId.value?.id!!,
            alunoComId
        )
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    init {
        observeColecaoTurmas()
        observeColecaoAlunos()
    }


}