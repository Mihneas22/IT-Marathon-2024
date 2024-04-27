package com.example.itmarathon.features.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itmarathon.features.data.repository.CourseRepository
import com.example.itmarathon.features.domain.models.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val repo: CourseRepository
): ViewModel(){
    var courses = mutableListOf<Course>()

    var course = Course()
    fun getCourses()
    =viewModelScope.launch {
        courses = repo.getCourses().toMutableList()
    }

    fun getCourseByName(name: String)
    =viewModelScope.launch {
        course = repo.getCourseByName(name)
    }
}