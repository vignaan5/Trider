package com.gyanu.trider

sealed class Screen (val route:String) {
    object Home:Screen(route ="homme_screen")
    object Drop:Screen(route="selectDrop")
    object Drop2:Screen(route="selectDrop2")
    object Detail:Screen(route="detail_screen")
    object Detail2:Screen(route="detail_screen2")
    object Profile:Screen(route="profile_screen2")
}