package com.example.di

import com.example.itmarathon.features.data.repository.AuthRepository
import com.example.itmarathon.features.domain.repository.AuthRepositoryIMPL
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryIMPL(
        auth = Firebase.auth,
        fb = FirebaseFirestore.getInstance()
    )
}