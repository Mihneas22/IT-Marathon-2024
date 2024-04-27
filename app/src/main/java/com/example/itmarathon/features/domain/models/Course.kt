package com.example.itmarathon.features.domain.models

data class Course(
    var name: String = "",
    var teacher: String = "",
    var accepted: List<Student> = emptyList(),
    var requests: List<Student> = emptyList()
)