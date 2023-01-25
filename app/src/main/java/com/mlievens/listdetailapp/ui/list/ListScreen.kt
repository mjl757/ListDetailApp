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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlievens.listdetailapp.domain.models.ListItemData
import com.mlievens.listdetailapp.R
import com.mlievens.listdetailapp.ui.composables.LoadingIndicator

@Composable
fun ListScreen(viewModel: ListViewModel, onItemSelected: (String, String) -> Unit) {
    val state by viewModel.itemState.collectAsState()
    ListScreen(
        viewState = state,
        onItemSelected = onItemSelected,
    )
}

@Composable
fun ListScreen(viewState: ListViewState, onItemSelected: (String, String) -> Unit) {
    when (viewState) {
        is ListViewState.LoadingState -> LoadingIndicator()
        is ListViewState.SuccessState -> ListScreenContent(
            itemList = viewState.itemList,
            onItemSelected = onItemSelected,
        )
        ListViewState.ErrorState -> TODO()
    }
}

@Composable
fun ListScreenContent(itemList: List<ListItemData>, onItemSelected: (String, String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
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
fun ListItem(itemData: ListItemData, onItemSelected: (String, String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onItemSelected(itemData.id, itemData.name) }
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

@Composable
fun ListScreenError() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.listScreenErrorMessage),
            color = Color.Red,
            fontSize = 15.sp
        )

    }

}