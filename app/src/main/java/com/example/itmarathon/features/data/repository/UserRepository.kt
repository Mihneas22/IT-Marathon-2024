package com.example.itmarathon.features.data.repository

import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.domain.util.Resource

interface UserRepository {
    suspend fun addCreditsAndYear(email: String,credits: String,yearScore: String): Resource<Boolean>
    suspend fun checkIfApplied(): Resource<Boolean>

    suspend fun ApplyToCourse(nameCourse: String,student: Student): Resource<Boolean>
}