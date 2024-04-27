package com.example.itmarathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.itmarathon.features.domain.models.Student
import com.example.itmarathon.features.presentation.screens.MenuScreen
import com.example.itmarathon.features.presentation.screens.SignInScreen
import com.example.itmarathon.features.presentation.screens.SignUpScreen
import com.example.itmarathon.features.presentation.viewmodels.AuthStateViewModel
import com.example.itmarathon.features.presentation.viewmodels.AuthViewModel
import com.example.itmarathon.ui.theme.ITMarathonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authStateViewModel by viewModels<AuthStateViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITMarathonTheme {
                val navController = rememberNavController()


                val navigator = remember{
                    mutableStateOf("")
                }
                val currentUserSign = authStateViewModel.getAuthStateLogin().collectAsState().value
                val currentUserData = authStateViewModel.getAuthStateData().collectAsState().value

                if(currentUserSign)
                {
                    navigator.value="SignInScreen"
                }
                else{
                    navigator.value="MenuScreen"
                }

                NavHost(navController = navController, startDestination = navigator.value){
                    composable("SignUpScreen"){
                        SignUpScreen(navController)
                    }

                    composable("SignInScreen"){
                        SignInScreen(navController)
                    }

                    composable("MenuScreen"){
                        MenuScreen(navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ITMarathonTheme {

    }
}