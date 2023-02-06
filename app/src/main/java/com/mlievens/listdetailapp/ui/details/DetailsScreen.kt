package com.mlievens.listdetailapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.R
import com.mlievens.listdetailapp.ui.composables.ErrorScreen
import com.mlievens.listdetailapp.ui.composables.LoadingIndicator
import com.mlievens.listdetailapp.ui.theme.RedText
import dev.jeziellago.compose.markdowntext.MarkdownText

object DetailsScreenTestTags {
    const val ERROR_CONTENT = "error"
    const val LOADING_CONTENT = "loading"
}

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, itemId: String?) {
    viewModel.loadDetails(itemId)

    val state by viewModel.detailState.collectAsState()
    DetailsScreen(viewState = state, reloadDetails = viewModel::reloadDetails)
}

@Composable
fun DetailsScreen(viewState: DetailViewState, reloadDetails: () -> Unit) {
    when (viewState) {
        is DetailViewState.LoadingState -> LoadingIndicator(Modifier.semantics {
            testTag = DetailsScreenTestTags.LOADING_CONTENT
        })
        is DetailViewState.SuccessState -> DetailsScreenContent(detail = viewState.detail)
        is DetailViewState.ErrorState -> ErrorScreen(
            modifier = Modifier.semantics { testTag = DetailsScreenTestTags.ERROR_CONTENT },
            errorMessage = stringResource(id = R.string.detailsScreenErrorMessage),
            onRetryClicked = reloadDetails
        )
    }
}

@Composable
fun DetailsScreenContent(detail: ItemDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = detail.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = RedText,
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = detail.itemDetailsText,
            fontStyle = FontStyle.Italic,
        )

        MarkdownText(modifier = Modifier.padding(vertical = 10.dp), markdown = detail.description)

    }
}
