package com.zygotecnologia.zygotv.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.zygotecnologia.zygotv.R

fun Fragment.navigateTo(direction: NavDirections) {
    findNavController().navigate(direction, navOptions)
}

fun Fragment.navigateTo(direction: NavDirections, args: NavOptions) {
    findNavController().navigate(direction, args)
}

private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithAnimations(destination: NavDirections) {
    this.navigate(destination, navOptions)
}
