package com.example.itmarathon.features.domain.repository

import com.example.itmarathon.features.data.repository.AuthRepository
import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.domain.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryIMPL @Inject constructor(
    private val auth: FirebaseAuth,
    private val fb: FirebaseFirestore
): AuthRepository {
    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Boolean>
    =try{
        auth.createUserWithEmailAndPassword(email,password).await()
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Boolean>
    =try{
        auth.signInWithEmailAndPassword(email,password).await()
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun createUserFB(
        name: String,
        email: String,
        password: String,
        year: String
    ): Resource<Boolean>
    =try{
        val user = mutableMapOf<String,Any>()
        user["user_name"] = name
        user["user_email"] = email
        user["user_password"] = password
        user["user_year"] = year
        user["user_credits"] = "0"
        user["user_lastYearScore"] = "0"
        fb.collection("users").document(email).set(user)
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun getUserFB(email: String): Student {
        val db = fb.collection("users").document(email).get().await().data
        return Student(
            (db?.get("user_name") as? String)!!,
            (db["user_email"] as? String)!!,
            (db["user_password"] as? String)!!,
            (db["user_year"] as? String)!!,
            (db["user_credits"] as? String)!!,
            (db["user_lastYearScore"] as? String)!!,
        )
    }

    override suspend fun observeAuthState(observer: (FirebaseUser?) -> Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            observer(firebaseAuth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
    }

    override fun getAuthStateData(viewModelScope: CoroutineScope): StateFlow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),auth.currentUser)

    override fun getAuthStateLogin(viewModelScope: CoroutineScope): StateFlow<Boolean> =callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),auth.currentUser == null)

    override fun logOut() {
        auth.signOut()
    }
}