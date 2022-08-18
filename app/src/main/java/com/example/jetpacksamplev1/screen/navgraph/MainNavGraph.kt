package com.example.jetpacksamplev1.screen.navgraph

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpacksamplev1.main.MainContent
import com.example.jetpacksamplev1.screen.category.CategoryViewModel
import com.example.jetpacksamplev1.screen.home.HomeViewModel
import com.example.jetpacksamplev1.screen.profile.ProfileViewModel

object MainDestinations {
    const val MAIN_ROUTE = "main"
    const val INGREDIENT_ROUTE = "ingredient"
}

@Composable
fun MainNavGraph(
    startDestination: String = MainDestinations.MAIN_ROUTE,
   // onLoginFragment: (String) -> Unit,
  //  onIngredientSearch: (String) -> Unit,
    onExplore: () -> Unit,
    onBack: (Int) -> Unit,
) {

        val homeViewModel:HomeViewModel= viewModel()
        val profileViewModel:ProfileViewModel= viewModel()
        val categoryViewModel:CategoryViewModel= viewModel()
        val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable((MainDestinations.MAIN_ROUTE)) {
            MainContent(
                viewModel =homeViewModel,
                categoryViewModel = categoryViewModel,
                profileViewModel=profileViewModel,
               // onLoginFragment = onLoginFragment,
                onExploreClicked = onExplore,
                //onDetails = onDetails
            )
        }
    }
}