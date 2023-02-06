package com.mlievens.listdetailapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mlievens.listdetailapp.ui.details.DetailsScreen
import com.mlievens.listdetailapp.ui.details.DetailsViewModel
import com.mlievens.listdetailapp.ui.list.ListScreen
import com.mlievens.listdetailapp.ui.list.ListViewModel

@Composable
fun ListDetailNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ListDetailDestinations.List.route,
        modifier = modifier,
    ) {
        composable(route = ListDetailDestinations.List.route) {
            val viewModel = hiltViewModel<ListViewModel>()
            ListScreen(
                viewModel = viewModel,
                onItemSelected = { itemId ->
                    navController.navigateToDetail(itemId)
                }
            )
        }
        composable(
            route = ListDetailDestinations.Details.routeWithArgs,
            arguments = ListDetailDestinations.Details.arguments
        ) {
            val viewModel = hiltViewModel<DetailsViewModel>()
            DetailsScreen(viewModel)
        }

    }

}


fun NavHostController.navigateToDetail(itemId: String) {
    this.navigate("${ListDetailDestinations.Details.route}/$itemId")
}

sealed class ListDetailDestinations(val route: String) {
    object List: ListDetailDestinations("list")
    object Details: ListDetailDestinations("detail") {

        const val itemIdArg = "itemId"
        val routeWithArgs = "$route/{$itemIdArg}"
        val arguments = listOf(
            navArgument(itemIdArg) { type = NavType.StringType },
        )
    }
}