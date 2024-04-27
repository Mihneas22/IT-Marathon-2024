package com.example.itmarathon.features.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itmarathon.features.data.repository.UserRepository
import com.example.itmarathon.features.domain.models.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel() {

    fun addCreditsAndScore(email: String,credits: String,lastYearScore: String)
    =viewModelScope.launch {
        repo.addCreditsAndYear(email,credits,lastYearScore)
    }

    fun ApplyToCourse(nameOfCourse: String,student: Student)
    =viewModelScope.launch{
        repo.ApplyToCourse(nameOfCourse,student)
    }
}