package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentLocalizacaoBinding
import com.arielcarvalho.android.turmasfirebaseproject.main.ui.MainViewModel
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.AdRequest

class LocalAdMob: Fragment(), LocationListener {
    val viewModel by activityViewModels<MainViewModel>()

    private lateinit var locationManager: LocationManager

//    private val TAG = "localizacao"

    //admob
    lateinit var mAdView : AdView

    private var _binding: FragmentLocalizacaoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLocalizacaoBinding.inflate(inflater, container, false)
        val view = binding.root

        //
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        //

//        setup()
        return view
    }

    private fun getLocation(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(
            ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        5000,
        5f,
        this)
    }

    private fun getSystemService(locationService: String): Any{
        TODO()
    }

    override fun onLocationChanged(location: Location){
        binding.viewLat.text = "%5.f".format(location.latitude)
        binding.viewLong.text = "%5.f".format(location.longitude)

    }

}