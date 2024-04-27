package com.example.itmarathon.features.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itmarathon.features.presentation.components.MarathonAppButton
import com.example.itmarathon.features.presentation.viewmodels.AuthViewModel
import com.example.itmarathon.ui.theme.darkGray
import com.example.itmarathon.ui.theme.lightGray

@Composable
fun MenuScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
){
    Card(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Helloooo")
            MarathonAppButton(text = "log out",
                onButClick = {
                    navController.navigate("SignInScreen")
                    authViewModel.logOut()

                }, color = darkGray, textColor = Color.White)
        }
    }
}

@Preview
@Composable
fun HeaderMainMenu(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
        colors = CardDefaults.cardColors(
            lightGray
        ),
        shape = RectangleShape
    ) {

    }
}