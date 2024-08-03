package com.example.newsappjetpack.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpack.core.data.remote.NetworkInterface
import com.example.newsappjetpack.home.model.PostModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _newsState = MutableStateFlow<UiState>(UiState.Loading)
    val newsState: StateFlow<UiState> = _newsState

    init {
        getNews()
    }

    private fun getNews() {
       viewModelScope.launch {
           try {
               val response = NetworkInterface.api.GetNews()
               _newsState.value = UiState.Success(response.data.posts)

           }catch (e: Throwable) {
               _newsState.value = UiState.Error(e)
               Log.d("HomeViewModel", "getNews: ", e)
           }
       }
    }
}


sealed class UiState {
    object Loading : UiState()
    data class Success(val news: List<PostModel>) : UiState()
    data class Error(val exception: Throwable) : UiState()
}