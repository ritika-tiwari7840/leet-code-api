// SharedViewModel.kt
package com.ritika.taskapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    fun setUsername(value: String) {
        _username.value = value
    }
}
