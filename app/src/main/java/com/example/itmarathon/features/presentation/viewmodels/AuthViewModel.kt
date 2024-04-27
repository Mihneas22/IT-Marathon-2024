package com.example.itmarathon.features.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itmarathon.features.data.repository.AuthRepository
import com.example.itmarathon.features.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel(){
    var signUpResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set
    var loginInResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set
    var createUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    fun signUpWithEmailAndPassword(email: String,password: String)
    =viewModelScope.launch {
        signUpResponse = Resource.Loading
        signUpResponse = repo.signUpWithEmailAndPassword(email, password)
        Log.d("auth",signUpResponse.toString())
    }

    fun signInWithEmailAndPassword(email: String,password: String)
    =viewModelScope.launch {
        loginInResponse = Resource.Loading
        loginInResponse = repo.signInWithEmailAndPassword(email, password)
    }

    fun createUser(name: String,email: String,password: String,year: String)
    =viewModelScope.launch {
        createUserResponse = Resource.Loading
        createUserResponse = repo.createUserFB(name, email, password, year)
    }

    fun logOut()
    =viewModelScope.launch{
        repo.logOut()
    }
}