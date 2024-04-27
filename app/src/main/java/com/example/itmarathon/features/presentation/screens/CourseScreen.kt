package com.example.itmarathon.features.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.presentation.components.MarathonAppButton
import com.example.itmarathon.features.presentation.viewmodels.CoursesViewModel
import com.example.itmarathon.features.presentation.viewmodels.UserViewModel
import com.example.itmarathon.ui.theme.darkGray
import com.example.itmarathon.ui.theme.lightGray

@Composable
fun CourseScreen(
    student: Student,
    name: String,
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
    coursesViewModel: CoursesViewModel = hiltViewModel()
){
    coursesViewModel.getCourseByName(name)
    val course = coursesViewModel.course

    Card(modifier = Modifier
        .fillMaxSize(),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center) {

            Text(modifier = Modifier.padding(top = 20.dp),
                text = "Name Of Course",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp
            )
            Text(text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = darkGray
            )

            Text(modifier = Modifier.padding(top = 20.dp),
                text = "Teacher",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp
            )
            Text(text = course.teacher,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = darkGray
            )

            Text(modifier = Modifier.padding(top = 20.dp),
                text = "Accepted Students",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp
            )
            Text(text = course.requests.toString(),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = darkGray
            )

            Text(modifier = Modifier.padding(top = 20.dp),
                text = "Requested Students",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp
            )
            Text(text = course.requests.toString(),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = darkGray
            )



            MarathonAppButton(
                modifier = Modifier.padding(top = 50.dp),
                text = "Apply Now",
                onButClick = {
                    userViewModel.ApplyToCourse(name,student)
                    navController.navigate("MenuScreen")
                },
                color = lightGray,
                textColor = Color.White)

            MarathonAppButton(
                modifier = Modifier.padding(top = 20.dp),
                text = "Cancel",
                onButClick = {
                    navController.navigate("MenuScreen")
                },
                color = lightGray,
                textColor = Color.White)
        }
    }
}