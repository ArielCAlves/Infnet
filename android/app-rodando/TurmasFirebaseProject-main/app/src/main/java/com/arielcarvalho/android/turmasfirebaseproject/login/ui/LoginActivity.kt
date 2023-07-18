package com.arielcarvalho.android.turmasfirebaseproject.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.MainActivity
import com.arielcarvalho.android.turmasfirebaseproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    val viewModel by viewModels<LoginViewModel>()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Usar a vinculação de visualizações em atividades
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#activities
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onStart() {
        super.onStart()
        if(viewModel.isLoggedIn()){
            startMainActivity()
        }
    }



    fun startMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}