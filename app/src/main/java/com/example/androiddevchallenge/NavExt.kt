package com.example.androiddevchallenge

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

fun NavController.navigate(route: String, bundle: Bundle) {
    currentBackStackEntry?.arguments?.putAll(bundle)
    navigate(route)
}
