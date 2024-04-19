package com.gyanu.trider.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.gyanu.trider.Screen
import com.gyanu.trider.components.TailoredMapwithPolyLine2
import com.gyanu.trider.presentation.MapViewModel

@Composable
fun SetupNavGraph(navController:NavHostController,vm:MapViewModel,flc: FusedLocationProviderClient){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route

    ){
        composable(
            route = Screen.Home.route
        ){

            HomeScreen(vm ,navController = navController, fusedLocationProviderClient = flc )
        }
        composable(
            route = Screen.Detail.route
        ){

               RideOptionsScreen(vm = vm,navController=navController)
        }
        composable(
            route = Screen.Drop.route
        ){

            SelectDropLoactionFromMap(vm = vm, navController = navController)
        }
        composable(
            route = Screen.Drop2.route
        ){

            SelectDropLoactionFromMap2(vm = vm, navController = navController)
        }
        composable(
            route = Screen.Detail2.route
        ){

            RideOptionsScreen2(vm = vm, navController = navController)
        }
        composable(
            route = Screen.Profile.route
        ){

           profileScreen(navController = navController)
        }
    }
}