package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.puppies.ui.PuppyDetailsScreen
import com.example.androiddevchallenge.puppies.ui.PuppyListScreen

sealed class Route(val id: String) {
    object List : Route("list")
    object Details : Route("details") {
        const val ARG_PUPPY = "puppy"
    }
}

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.List.id) {
        composable(Route.List.id) {
            PuppyListScreen(onPuppySelected = { puppy ->
                navController.navigate(Route.Details.id, bundleOf(Route.Details.ARG_PUPPY to puppy))
            })
        }
        composable(Route.Details.id) {
            val args = navController.previousBackStackEntry?.arguments
            PuppyDetailsScreen(
                puppy = args?.getParcelable(Route.Details.ARG_PUPPY)!!,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}
