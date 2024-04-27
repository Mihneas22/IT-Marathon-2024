package com.example.itmarathon.features.data.repository

import com.example.itmarathon.features.domain.models.Course
import com.example.itmarathon.features.domain.util.Resource

interface UserRepository {
    suspend fun checkIfApplied(): Resource<Boolean>

    suspend fun getAcceptedCourses(): List<Course>

    suspend fun getAppliedCourses(): List<Course>
}