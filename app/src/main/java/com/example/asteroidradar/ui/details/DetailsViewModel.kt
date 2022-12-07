package com.example.asteroidradar.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DetailsViewModel:ViewModel() {

    private val _displayExplanationDialog = MutableLiveData<Boolean>()
    val displayExplanationDialog: LiveData<Boolean>
        get() = _displayExplanationDialog

    fun onExplanationButtonClicked(){
        _displayExplanationDialog.value = true
    }

    fun onDisplayExplanationDialogDone(){
        _displayExplanationDialog.value = false
    }


}