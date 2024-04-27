package com.example.itmarathon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    private val authViewModel by viewModels<AuthViewModel>()
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
                val email = currentUserData?.email
                if (email != null) {
                    Log.d("userData",email)
                }
                if(currentUserSign)
                {
                    navigator.value="SignInScreen"
                }
                else{
                    navigator.value="MenuScreen"
                }
                if (email != null) {
                    authViewModel.getUserData(email)
                }
                val user = authViewModel.user
                Log.d("userData",user.value.email)

                NavHost(navController = navController, startDestination = navigator.value){
                    composable("SignUpScreen"){
                        SignUpScreen(navController)
                    }

                    composable("SignInScreen"){
                        SignInScreen(navController)
                    }

                    composable("MenuScreen"){
                        MenuScreen(user.value, navController)
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