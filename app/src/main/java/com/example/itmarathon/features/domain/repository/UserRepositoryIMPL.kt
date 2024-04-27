package com.example.itmarathon.features.domain.repository

import com.example.itmarathon.features.data.repository.UserRepository
import com.example.itmarathon.features.domain.models.Course
import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.domain.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryIMPL @Inject constructor(
    private val fb: FirebaseFirestore
): UserRepository {

    override suspend fun addCreditsAndYear(
        email: String,
        credits: String,
        yearScore: String
    ): Resource<Boolean>
    =try{
        val user = mutableMapOf<String,Any>()
        val userOld = fb.collection("users").document(email).get().await().data
        user["user_email"] = email
        user["user_name"] = userOld?.get("user_name") as String
        user["user_password"] = userOld["user_password"] as String
        user["user_year"] = userOld["user_year"] as String
        user["user_credits"] = credits
        user["user_lastYearScore"] = yearScore
        fb.collection("users").document(email).set(user)
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }

    override suspend fun ApplyToCourse(
        nameCourse: String,
        student: Student
    ): Resource<Boolean>
    =try{
        fb.collection("optionals").document(nameCourse).collection("Requests").document(student.email).set(student)
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }
    override suspend fun checkIfApplied(): Resource<Boolean>
    =try{
        Resource.Success(true)
    }catch (ex: Exception){
        Resource.Failure(ex)
    }
}