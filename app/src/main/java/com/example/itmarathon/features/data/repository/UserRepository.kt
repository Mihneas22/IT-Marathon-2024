package com.example.itmarathon.features.data.repository

import com.example.itmarathon.features.domain.models.Course
import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.domain.util.Resource
import com.google.firebase.firestore.auth.User

interface UserRepository {
    suspend fun addCreditsAndYear(email: String,credits: String,yearScore: String): Resource<Boolean>
    suspend fun checkIfApplied(): Resource<Boolean>

    suspend fun getAcceptedCourses(): List<Course>

    suspend fun getAppliedCourses(): List<Course>
}