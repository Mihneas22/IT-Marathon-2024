package com.example.itmarathon.features.domain.models

data class Course(
    val name: String = "",
    val teacher: String = "",
    val accepted: List<Student> = emptyList(),
    val requests: List<Student> = emptyList()
)