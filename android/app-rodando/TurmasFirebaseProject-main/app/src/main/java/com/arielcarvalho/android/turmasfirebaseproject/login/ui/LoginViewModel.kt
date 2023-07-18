package com.arielcarvalho.android.turmasfirebaseproject.login.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.arielcarvalho.android.turmasfirebaseproject.repository.TurmasRepository

class LoginViewModel : ViewModel() {

    val TAG = "ViewModel"
    val repository = TurmasRepository.get()

    // Auth:

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun login(
        email: String,
        password: String
    ): Task<AuthResult> {
        return TurmasRepository.auth.signInWithEmailAndPassword(email, password)
    }

    fun signOn(
        email: String,
        password: String
    ): Task<AuthResult>  {
        return repository.cadastrarUsuarioComSenha(
            email,
            password
        )
    }

}