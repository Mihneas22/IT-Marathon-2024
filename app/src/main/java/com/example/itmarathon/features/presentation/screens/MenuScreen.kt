package com.example.itmarathon.features.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itmarathon.R
import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.presentation.components.MarathonAppButton
import com.example.itmarathon.features.presentation.viewmodels.AuthViewModel
import com.example.itmarathon.ui.theme.darkGray
import com.example.itmarathon.ui.theme.lightGray

@Composable
fun MenuScreen(
    user: Student,
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
){
    Column {
        Card(modifier = Modifier
            .height(100.dp)
            .width(100.dp)) {
            Column {
                MarathonAppButton(text = "log out",
                    onButClick = {
                        navController.navigate("SignInScreen")
                        authViewModel.logOut()

                    }, color = darkGray, textColor = Color.White)
            }
        }
        HeaderMainMenu(name = user.name)
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

        Row(modifier = Modifier.fillMaxSize().padding(end = 20.dp),
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
                modifier = Modifier.height(130.dp).width(130.dp))
        }

    }
}

@Composable
fun MainScreen(){

}