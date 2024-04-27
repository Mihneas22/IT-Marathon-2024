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
            val acceptedDb = fb.collection("optionals").document(document.id).collection("Accepted").get().await().documents
            val requestsDb = fb.collection("optionals").document(document.id).collection("Requests").get().await().documents

            val listAccepted = mutableListOf(Student())
            val listRequested = mutableListOf(Student())

            for (acceptedStud in acceptedDb){
                val student = Student(
                    (acceptedStud.get("user_name") as? String)!!,
                    (acceptedStud.get("user_email") as? String)!!,
                    (acceptedStud.get("user_password") as? String)!!,
                    (acceptedStud.get("user_year") as? String)!!,
                    (acceptedStud.get("user_credits") as? String)!!,
                    (acceptedStud.get("user_lastYearScore") as? String)!!,
                )
                listAccepted.add(student)
            }
            for (requestedStud in requestsDb){
                val student = Student(
                    (requestedStud.get("user_name") as? String)!!,
                    (requestedStud.get("user_email") as? String)!!,
                    (requestedStud.get("user_password") as? String)!!,
                    (requestedStud.get("user_year") as? String)!!,
                    (requestedStud.get("user_credits") as? String)!!,
                    (requestedStud.get("user_lastYearScore") as? String)!!,
                )
                listRequested.add(student)
            }
            val course = Course(
                (document["Name"] as? String)!!,
                (document["Teacher"] as? String)!!,
                listAccepted,
                listRequested
            )
            courses.add(course)
        }
        Log.d("courses",courses.toString())
        return courses
    }

    override suspend fun getCourseByName(name: String): Course {
        val course = Course()
        val db = fb.collection("optionals").document(name).get().await().data
        course.name = (db?.get("Name") as? String)!!
        course.teacher = (db["Teacher"] as? String)!!

        val acceptedDb = fb.collection("optionals").document(name).collection("Accepted").get().await().documents
        val requestsDb = fb.collection("optionals").document(name).collection("Requests").get().await().documents


        val listAccepted = mutableListOf<Student>()
        val listRequested = mutableListOf<Student>()
        for (acceptedStud in acceptedDb){
            val student = Student(
                (acceptedStud?.get("user_name") as? String)!!,
                (acceptedStud["user_email"] as? String)!!,
                (acceptedStud["user_password"] as? String)!!,
                (acceptedStud["user_year"] as? String)!!,
                (acceptedStud["user_credits"] as? String)!!,
                (acceptedStud["user_lastYearScore"] as? String)!!,
            )
            listAccepted.add(student)
        }

        for (requestedStud in requestsDb){
            val student = Student(
                (requestedStud?.get("user_name") as? String)!!,
                (requestedStud["user_email"] as? String)!!,
                (requestedStud["user_password"] as? String)!!,
                (requestedStud["user_year"] as? String)!!,
                (requestedStud["user_credits"] as? String)!!,
                (requestedStud["user_lastYearScore"] as? String)!!,
            )
            listRequested.add(student)
        }
        course.accepted = listAccepted
        course.requests = listRequested
        return course
    }
}