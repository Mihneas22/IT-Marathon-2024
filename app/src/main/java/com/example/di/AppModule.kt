package com.example.di

import com.example.itmarathon.features.data.repository.AuthRepository
import com.example.itmarathon.features.data.repository.CourseRepository
import com.example.itmarathon.features.data.repository.UserRepository
import com.example.itmarathon.features.domain.repository.AuthRepositoryIMPL
import com.example.itmarathon.features.domain.repository.CourseRepositoryIMPL
import com.example.itmarathon.features.domain.repository.UserRepositoryIMPL
import com.google.firebase.Firebase
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

    @Provides
    fun provideCourseRepository(): CourseRepository = CourseRepositoryIMPL(
        fb = FirebaseFirestore.getInstance()
    )

    @Provides
    fun provideUserRepository(): UserRepository = UserRepositoryIMPL(
        fb = FirebaseFirestore.getInstance()
    )
}