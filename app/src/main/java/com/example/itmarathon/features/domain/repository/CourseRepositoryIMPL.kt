package com.example.itmarathon.features.domain.repository

import android.util.Log
import com.example.itmarathon.features.data.repository.CourseRepository
import com.example.itmarathon.features.domain.models.Course
import com.example.itmarathon.features.domain.models.Student
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class CourseRepositoryIMPL @Inject constructor(
    private val fb: FirebaseFirestore
): CourseRepository {
    override suspend fun getCourses(): List<Course> {
        val courses = mutableListOf<Course>()
        val db = fb.collection("optionals").get().await().documents
        for (document in db){
            val course = Course(
                (document["Name"] as? String)!!,
                (document["Teacher"] as? String)!!,
                (document["accepted"] as? List<Student>)!!,
                (document["requests"] as? List<Student>)!!
            )
            courses.add(course)
        }
        Log.d("courses",courses.toString())
        return courses
    }
}