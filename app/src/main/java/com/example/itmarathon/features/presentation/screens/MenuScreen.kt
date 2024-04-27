package com.example.itmarathon.features.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itmarathon.R
import com.example.itmarathon.features.domain.models.Course
import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.presentation.viewmodels.CoursesViewModel
import com.example.itmarathon.ui.theme.lightGray

@Composable
fun MenuScreen(
    user: Student,
    navController: NavController,
    coursesViewModel: CoursesViewModel = hiltViewModel()
){
    coursesViewModel.getCourses()
    Log.d("courses", coursesViewModel.courses.toString())
    Column {
        HeaderMainMenu(name = user.name)
        MainScreen(list = coursesViewModel.courses)
    }
}

@Composable
fun HeaderMainMenu(
    name: String
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        colors = CardDefaults.cardColors(
            lightGray
        ),
        shape = RectangleShape,
        border = BorderStroke(2.dp,Color.Black)
    ) {

        Row(modifier = Modifier
            .fillMaxSize()
            .padding(end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center) {
                Text(text = "Welcome",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 40.sp)

                Text(text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 40.sp)
            }

            Image(painter = painterResource(id = R.drawable.logoac),
                contentDescription = "Icon",
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp))
        }

    }
}
@Composable
fun MainScreen(list: List<Course>){
    Card(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        shape = RectangleShape) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
        ) {
            LazyRow {
                items(list.size){
                    Course(course = list[it])
                }
            }
        }
    }
}
@Composable
fun Course(course: Course){
    Card(modifier = Modifier
        .width(190.dp)
        .height(150.dp)
        .padding(start = 20.dp, end = 20.dp)
        .clickable {
                   
        },
        colors = CardDefaults.cardColors(
            lightGray
        )
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "Name: " + course.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = "Teacher: " + course.teacher,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}