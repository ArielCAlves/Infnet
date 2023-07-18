package com.arielcarvalho.android.turmasfirebaseproject.repository

import com.arielcarvalho.android.turmasfirebaseproject.models.Aluno
import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoComId
import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoNaTurma
import com.arielcarvalho.android.turmasfirebaseproject.models.Turma
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
//import com.arielcarvalho.android.turmasfirebaseproject.models.Aluno
//import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoComId
//import com.arielcarvalho.android.turmasfirebaseproject.models.AlunoNaTurma
//import com.arielcarvalho.android.turmasfirebaseproject.models.Turma


const val TAG = "TurmasFirebase"

class TurmasRepository private constructor() {


// ...
// Initialize Firebase Auth

    companion object {

        lateinit var auth: FirebaseAuth

        lateinit var db: FirebaseFirestore

        lateinit var colecaoTurmas: CollectionReference

        lateinit var colecaoAlunos: CollectionReference

        private var INSTANCE: TurmasRepository? = null
        fun initialize() {
            if (INSTANCE == null) {
                INSTANCE = TurmasRepository()
            }
            auth = Firebase.auth
            // Banco de dados Firestore
            db = Firebase.firestore

            // Coleção de cursos:
            colecaoTurmas = db.collection("cursos")

            // Coleção de estudantes:
            colecaoAlunos = db.collection("estudantes")


        }

        fun get(): TurmasRepository {
            return INSTANCE
                ?: throw IllegalStateException("TurmasRepository deve ser inicializado.")
        }
    }

    // Auth  ///////////////////////////////////////////////////////////////////////////////////////

    fun getCurrentUser() = auth.currentUser

    fun isLoggedIn(): Boolean {

        if (getCurrentUser() != null) {
            return true
        }
        return false
    }

    // Faça o mesmo que foi feito com o Login
    fun cadastrarUsuarioComSenha(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun login(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun logout() {
        auth.signOut()
    }

    // FireStore ///////////////////////////////////////////////////////////////////////////////////

    // cursos
    fun cadastrarTurma(turma: Turma): Task<DocumentReference> {
        return colecaoTurmas.add(turma)
    }

    fun getTurmas(): Task<QuerySnapshot> {
        return colecaoTurmas.get()
    }

    fun getTurmasColecao(): CollectionReference {
        return colecaoTurmas
    }

    fun deleteTurma(id: String) {
        colecaoTurmas.document(id).delete()
    }

    fun atualizaTurma(id: String?, turma: Turma) {
        if (id != null) {
            colecaoTurmas.document(id).set(turma)
        }
    }

    // estudantes

    fun getAlunosColecao(): CollectionReference {
        return colecaoAlunos
    }

    fun cadastrarAluno(aluno: Aluno): Task<DocumentReference> {
        return colecaoAlunos.add(aluno)
    }

    fun deleteAluno(id: String): Task<Void> {
        return colecaoAlunos.document(id).delete()
    }

   fun atualizaAluno(id: String?, aluno: Aluno) {
        if (id != null) {
            colecaoAlunos.document(id).set(aluno)
        }
    }

    fun inscreverAlunoNaTurma(idTurma: String, alunoComId: AlunoComId){
        val alunoNaTurma = AlunoNaTurma(
            nomeEstudante = alunoComId.nomeEstudante,
            nro_inscricao = alunoComId.nro_inscricao,
        )
        colecaoTurmas
            .document(idTurma)
            .collection("estudantes")
            .document(alunoComId.id)
            .set(alunoNaTurma)
    }


}