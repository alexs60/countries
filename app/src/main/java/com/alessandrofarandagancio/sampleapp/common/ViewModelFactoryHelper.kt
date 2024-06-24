package com.alessandrofarandagancio.sampleapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <VM : ViewModel> viewModelFactory(create: () -> VM): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return create() as T
        }
    }
}