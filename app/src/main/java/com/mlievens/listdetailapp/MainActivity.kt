package com.mlievens.listdetailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mlievens.listdetailapp.ui.theme.ListDetailAppTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListDetailApp()
        }
    }

    @Composable
    fun ListDetailApp() {
        ListDetailAppTheme {
            val navController = rememberNavController()
            val magicItemTitle = stringResource(R.string.magicItem)
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStack?.destination
            val currentScreen: String = when (currentDestination?.route) {
                ListDetailDestinations.List.route -> stringResource(R.string.magicItems)
                ListDetailDestinations.Details.routeWithArgs -> magicItemTitle
                else -> ""
            }

            Scaffold(topBar = {
                TopAppBar(title = { Text(text = currentScreen) }, navigationIcon = {
                    if (currentScreen == magicItemTitle) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    }
                })
            }) { innerPadding ->
                ListDetailNavHost(
                    navController = navController, modifier = Modifier.padding(innerPadding)
                )
            }
        }

    }
}
