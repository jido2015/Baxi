package com.olajide.capricon.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
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

fun <T> Fragment.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}
