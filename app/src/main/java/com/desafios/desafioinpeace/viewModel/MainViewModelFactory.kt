package com.desafios.desafioinpeace.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory :
    ViewModelProvider.Factory {

    //cria a viewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}