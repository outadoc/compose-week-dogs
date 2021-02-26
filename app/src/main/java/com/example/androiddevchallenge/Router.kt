/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.puppies.ui.AboutScreen
import com.example.androiddevchallenge.puppies.ui.PuppyDetailsScreen
import com.example.androiddevchallenge.puppies.ui.PuppyListScreen

sealed class Route(val id: String) {
    object List : Route("list")
    object Details : Route("details") {
        const val ARG_PUPPY = "puppy"
    }
    object About : Route("about")
}

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.List.id) {
        composable(Route.List.id) {
            PuppyListScreen(
                onPuppySelected = { puppy ->
                    navController.navigate(Route.Details.id, bundleOf(Route.Details.ARG_PUPPY to puppy))
                },
                onAboutClicked = {
                    navController.navigate(Route.About.id)
                }
            )
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
        composable(Route.About.id) {
            AboutScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}
