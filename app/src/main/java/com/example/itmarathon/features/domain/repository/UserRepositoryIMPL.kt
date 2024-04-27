package com.example.itmarathon.features.domain.repository

import com.example.itmarathon.features.data.repository.UserRepository
import com.example.itmarathon.features.domain.models.Course
import com.example.itmarathon.features.domain.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserRepositoryIMPL @Inject constructor(
    private val fb: FirebaseFirestore
): UserRepository {
    override suspend fun checkIfApplied(): Resource<Boolean>
    =try{
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun getAcceptedCourses(): List<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun getAppliedCourses(): List<Course> {
        TODO("Not yet implemented")
    }
}