package com.github.overlaytest.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.overlaytest.domain.enitity.Group
import com.github.overlaytest.domain.usecase.GetGroupsUseCase
import timber.log.Timber

class MainViewModel(
    val getGroupUseCase: GetGroupsUseCase = GetGroupsUseCase()
) : ViewModel() {

    private val _groupList: MutableLiveData<List<Group>> = MutableLiveData()

    val groupList: LiveData<List<Group>> = _groupList

    fun refresh() {
        Timber.i("refresh()")
        _groupList.value = getGroupUseCase.execute()
    }

}