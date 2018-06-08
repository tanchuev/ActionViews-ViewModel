package com.tanchuev.actionviews.viewmodel.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tanchuev.actionviews.viewmodel.utils.ErrorMessage

abstract class ActionsViewModel : ViewModel() {
    val contentVisibility = MutableLiveData<Boolean>() // todo remove contentVisibility if not needed
    val loadingVisibility = MutableLiveData<Boolean>()
    val noInternetVisibility = MutableLiveData<Boolean>()
    val emptyContentVisibility = MutableLiveData<Boolean>()
    val isContentEmpty = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<ErrorMessage>()
}