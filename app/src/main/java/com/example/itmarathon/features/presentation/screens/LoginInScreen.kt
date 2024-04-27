package com.example.itmarathon.features.presentation.screens

import android.util.Log
import android.view.animation.OvershootInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itmarathon.R
import com.example.itmarathon.features.domain.util.Resource
import com.example.itmarathon.features.presentation.components.MarathonAppButton
import com.example.itmarathon.features.presentation.components.MarathonAppPasswordTextField
import com.example.itmarathon.features.presentation.components.MarathonAppTextField
import com.example.itmarathon.features.presentation.viewmodels.AuthViewModel
import com.example.itmarathon.ui.theme.darkGray
import com.example.itmarathon.ui.theme.evenLighterGray
import com.example.itmarathon.ui.theme.lightGray
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(
    navController: NavController,
){
    Column {
        SignInHeader()
        SignInMain(navController)
    }
}


@Composable
fun SignInHeader(){

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
        colors = CardDefaults.cardColors(
            Color.White
        ),
        shape = RectangleShape
    ) {
        val scale = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(3000L)
        }
        Row(modifier = Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.logoac),
                contentDescription = "logo")

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Facultatea de Automatica si Calculatoare",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = "LSAC",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun SignInMain(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
){
    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var visualState by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Card(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            Color.White
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MarathonAppTextField(
                text = email,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })email=it
                },
                label = "Enter Email",
                color = darkGray,
                textColor = evenLighterGray
            )

            MarathonAppPasswordTextField(
                modifier = Modifier.padding(top = 50.dp),
                text = password,
                onTextChange = {
                    if(it.all { char->
                            char.isDefined()
                        })password=it
                },
                label = "Enter password",
                color = darkGray,
                textColor = evenLighterGray,
                visualState = visualState
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Icon Password",
                    modifier = Modifier.clickable {
                        visualState = if(visualState) {
                            false
                        } else{
                            true
                        }
                    }
                )
            }

            MarathonAppButton(
                modifier = Modifier.padding(top = 30.dp),
                text = "Sign In",
                onButClick = {
                    if(password.length<8){
                        Toast.makeText(context,"Enter an 8 Letters Min. Password", Toast.LENGTH_SHORT).show() }
                    else if(email.isEmpty() ||password.isEmpty()){
                        Toast.makeText(context,"Fill Out All Data", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        authViewModel.signInWithEmailAndPassword(email, password)
                        navController.navigate("MenuScreen")
                        when(val signInResponse = authViewModel.loginInResponse){
                            is Resource.Success ->{
                                navController.navigate("MenuScreen")
                            }

                            is Resource.Failure ->{
                                Toast.makeText(context,"Error: ${signInResponse.ex}",
                                    Toast.LENGTH_SHORT).show()
                                Log.d("errorSignIn",signInResponse.ex.toString())
                            }

                            is Resource.Loading ->{
                                ProgressBar(context)
                            }
                        }
                    }
                },
                color = lightGray,
                textColor = Color.White
            )

            Text(text = "Don't have an account?",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clickable {
                        navController.navigate("SignUpScreen")
                    }
            )
        }
    }
}