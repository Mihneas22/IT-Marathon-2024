package com.example.itmarathon.features.presentation.screens

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itmarathon.features.presentation.components.MarathonAppButton
import com.example.itmarathon.features.presentation.components.MarathonAppTextField
import com.example.itmarathon.features.presentation.viewmodels.UserViewModel
import com.example.itmarathon.ui.theme.darkGray
import com.example.itmarathon.ui.theme.evenLighterGray
import com.example.itmarathon.ui.theme.lightGray

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    email: String,
    navController: NavController
){
    var credits by remember{
        mutableStateOf("")
    }

    var year by remember {
        mutableStateOf("")
    }
    Card(modifier = Modifier.fillMaxSize(),
        shape = RectangleShape) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Enter your number of credits:",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp)
            MarathonAppTextField(text = credits,
                onTextChange = {
                               if (it.all { char->
                                   char.isDigit()
                                   })credits = it
                }, label = "Credits",
                color = darkGray,
                textColor = evenLighterGray
            )

            Text(modifier = Modifier.padding(top = 30.dp),
                text = "Enter last year score:",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp)
            MarathonAppTextField(text = year,
                onTextChange = {
                    if (it.all { char->
                            char.isDigit()
                        })year = it
                }, label = "Last Year Score",
                color = darkGray,
                textColor = evenLighterGray
            )

            MarathonAppButton(text = "Save",
                onButClick = {
                    if(credits.isNotEmpty() && year.isNotEmpty()) {
                        userViewModel.addCreditsAndScore(email,credits,year)
                        navController.navigate("MenuScreen")
                    }
                },
                color = lightGray,
                textColor = Color.White,
                modifier = Modifier.padding(top = 30.dp)
            )

            Text(text = "Cancel",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.clickable {
                    navController.navigate("MenuScreen")
                }
            )
        }
    }
}