package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.arielcarvalho.android.turmasfirebaseproject.databinding.ActivityMainBinding
import com.arielcarvalho.android.turmasfirebaseproject.login.ui.LoginActivity
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {

    val TAG = "ReadWrite"
    var nomeDoArquivo = ""
    var textoDoArquivo = ""

    val viewModel by viewModels<MainViewModel>()


    // Usar a vinculação de visualizações em atividades
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#activities
    private lateinit var binding: ActivityMainBinding
    private lateinit var layout: View

//    val adView = AdView(this)
//    adView.adSize = AdSize.BANNER
//    adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

    private var isCameraPermissionGranted = false
    private var isLocationPermissionGranted = false

    private lateinit var multiplePermissionsLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var singlePermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        MobileAds.initialize(this){}
//        val adRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)

        setup()
    }



    private fun setup() {
        setupViews()
        setupClickListeners()
    }



    private fun setupClickListeners() {
        binding.btnSair.setOnClickListener {
            viewModel.logout()
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun setupViews() {
        setTitle("Principal")
        binding.tvBemVindo.text = "Seja bem vindo ${viewModel.getCurrentUserEmail()}!"
    }


}