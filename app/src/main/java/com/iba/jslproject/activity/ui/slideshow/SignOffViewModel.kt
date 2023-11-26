package com.iba.jslproject.activity.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignOffViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Are you sure?"
    }
    private val _btn_text = MutableLiveData<String>().apply {
        value = "OK"
    }
    val text: LiveData<String> = _text
    val btn_text: LiveData<String> = _btn_text
}