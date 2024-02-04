package com.blaze.core.ui.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.blaze.core.ui.CoreViewModel

val LocalNavigation = compositionLocalOf<NavHostController> {
    error("Unable to initialize nav host controller")
}

val LocalCore = compositionLocalOf<CoreViewModel> {
    error("Unable to create Core Model")
}