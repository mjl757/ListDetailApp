package com.mlievens.listdetailapp.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.mlievens.listdetailapp.domain.models.ListItemData
import com.mlievens.listdetailapp.R
import com.mlievens.listdetailapp.ui.composables.ErrorScreen
import com.mlievens.listdetailapp.ui.composables.LoadingIndicator

object ListScreenTestTags {
    const val ERROR_CONTENT = "error"
    const val LOADING_CONTENT = "loading"
}
@Composable
fun ListScreen(viewModel: ListViewModel, onItemSelected: (String) -> Unit) {
    val state by viewModel.itemState.collectAsState()
    ListScreen(
        viewState = state,
        onItemSelected = onItemSelected,
        viewModel::loadItems,
    )
}

@Composable
fun ListScreen(
    viewState: ListScreenViewState,
    onItemSelected: (String) -> Unit,
    reloadItems: () -> Unit
) {
    when (viewState) {
        is ListScreenViewState.LoadingState -> LoadingIndicator(Modifier.semantics {
            testTag = ListScreenTestTags.LOADING_CONTENT
        })
        is ListScreenViewState.SuccessState -> ListScreenContent(
            itemList = viewState.itemList,
            onItemSelected = onItemSelected,
        )
        is ListScreenViewState.ErrorState -> ErrorScreen(
            modifier = Modifier.semantics { testTag = ListScreenTestTags.ERROR_CONTENT },
            errorMessage = stringResource(id = R.string.listScreenErrorMessage),
            onRetryClicked = reloadItems
        )
    }
}

@Composable
fun ListScreenContent(itemList: List<ListItemData>, onItemSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemList.forEach {
            ListItem(
                itemData = it,
                onItemSelected = onItemSelected,
            )
        }
    }
}

@Composable
fun ListItem(itemData: ListItemData, onItemSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onItemSelected(itemData.id) },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterStart),
                text = itemData.name,
            )
        }
    }
}
