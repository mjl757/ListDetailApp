package com.mlievens.listdetailapp.ui.details

import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.ui.theme.ListDetailAppTheme
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `loading state shows Loading Indicator`() {
        composeTestRule.setContent {
            ListDetailAppTheme {
                DetailsScreen(viewState = DetailViewState.LoadingState, reloadDetails = {})
            }
        }

        composeTestRule.onNodeWithTag(DetailsScreenTestTags.LOADING_CONTENT).assertExists()
    }

    @Test
    fun `error state shows error content`() {
        composeTestRule.setContent {
            ListDetailAppTheme {
                DetailsScreen(viewState = DetailViewState.ErrorState, reloadDetails = {})
            }
        }
        composeTestRule.onNodeWithTag(DetailsScreenTestTags.LOADING_CONTENT).assertDoesNotExist()
        composeTestRule.onNodeWithTag(DetailsScreenTestTags.ERROR_CONTENT).assertExists()
    }



    @Test
    fun `Success state shows details content`() {
        val details = ItemDetail(
            name = "Test Item",
            description = "A magical item of testing",
            type = "Test Type",
            rarity = "Common",
            requiresAttunement = ""
        )
        composeTestRule.setContent {
            ListDetailAppTheme {
                DetailsScreen(viewState = DetailViewState.SuccessState(details), reloadDetails = {})
            }
        }

        composeTestRule.onNodeWithText("Test Item").assertExists()
        composeTestRule.onNodeWithText("Test Type, Common").assertExists()

        composeTestRule.onNodeWithTag(DetailsScreenTestTags.LOADING_CONTENT).assertDoesNotExist()
        composeTestRule.onNodeWithTag(DetailsScreenTestTags.ERROR_CONTENT).assertDoesNotExist()
    }
}