package com.iagodavit.tikray.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
        ){
        composable(
            route = Screen.Home.route
        ){
            homeScreen(navController = navController)
        }
        composable(
            route = Screen.Login.route
        ){
            MainScreen()
        }
        composable(
            route = Screen.Register.route
        ){
            myApp()
        }
    }
}