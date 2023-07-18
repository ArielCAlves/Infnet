package com.arielcarvalho.android.turmasfirebaseproject.utils

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Fragment.nav(id: Int){
    findNavController().navigate(id)
}

fun Fragment.navUp(){
    findNavController().navigateUp()
}

 fun Fragment.getTextInput(editText: EditText): String {
    return editText.text.toString()
}


fun Fragment.getIntInput(editText: EditText): Int {
    return editText.text.toString().toInt()
}

fun Fragment.toast(msg: String){
    Toast.makeText(
        requireContext(),
    msg,
    Toast.LENGTH_SHORT
    ).show()
}

fun View.showSnackbar(
    view:View,
    msg: String,
    lenght: Int,
    actionMessage:CharSequence?,
    action:(View) -> Unit
){
    val snackbar = Snackbar.make(view, msg, lenght)
    if (actionMessage != null){
        snackbar.setAction(actionMessage){
            action(this)
        }.show()
    }else{
        snackbar.show()
    }
}