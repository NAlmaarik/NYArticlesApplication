package com.example.nytarticlesapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytarticlesapplication.data.INYArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NYArticleViewModel @Inject constructor(
    private val repository: INYArticlesRepository
):ViewModel() {

    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus : LiveData<Boolean>
        get() = _loadingStatus

    init {
        _loadingStatus.value = true
        viewModelScope.launch {
            repository.loadArticles()
            _loadingStatus.value = false
        }

    }

    val articles = repository.getArticles()
}