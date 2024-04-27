package com.example.itmarathon.features.data.repository

import com.example.itmarathon.features.domain.models.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun getCourseByName(name: String): Course

}