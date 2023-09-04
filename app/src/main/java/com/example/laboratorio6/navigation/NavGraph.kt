package com.example.laboratorio6.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio6.pages.Gallery
import com.example.laboratorio6.pages.Login

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController(), defaultDestination:String) {
    NavHost(navController, startDestination = defaultDestination) {
        composable("login") {
            Login(navController)
        }
        composable("gallery"){
            Gallery(navController)
        }




    }
}
