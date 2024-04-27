package com.example.itmarathon.features.data.repository

import com.example.itmarathon.features.domain.util.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun signUpWithEmailAndPassword(email: String,password: String): Resource<Boolean>

    suspend fun signInWithEmailAndPassword(email: String,password: String): Resource<Boolean>

    suspend fun createUserFB(name: String,email: String,password: String,year: String): Resource<Boolean>

    suspend fun observeAuthState(observer: (FirebaseUser?) -> Unit)

    fun getAuthStateLogin(viewModelScope: CoroutineScope): StateFlow<Boolean>

    fun getAuthStateData(viewModelScope: CoroutineScope): StateFlow<FirebaseUser?>

    fun logOut()
}