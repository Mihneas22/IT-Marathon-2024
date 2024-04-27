package com.example.itmarathon.features.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _courses = mutableStateOf(listOf<Course>())
    val courses: State<List<Course>> = _courses

    var course = Course()

    fun getCourses()
    =viewModelScope.launch {
        _courses.value = repo.getCourses()
        Log.d("coursesViewModel",courses.value.toString())
    }

    fun getCourseByName(name: String)
    =viewModelScope.launch {
        course = repo.getCourseByName(name)
    }
}