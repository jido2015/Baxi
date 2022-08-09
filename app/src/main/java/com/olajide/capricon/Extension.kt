package com.olajide.capricon

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Fragment.launchIo(block: suspend CoroutineScope.() ->Unit){
    lifecycleScope.launch(Dispatchers.IO){
        block.invoke(this)
    }
}fun ViewModel.launchIo(block: suspend CoroutineScope.() ->Unit){
    viewModelScope.launch(Dispatchers.IO){
        block.invoke(this)
    }
}