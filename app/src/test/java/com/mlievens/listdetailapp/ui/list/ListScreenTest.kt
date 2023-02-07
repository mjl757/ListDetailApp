package com.mlievens.listdetailapp.ui.list

import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.mlievens.listdetailapp.domain.models.ListItemData
import com.mlievens.listdetailapp.ui.theme.ListDetailAppTheme
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `loading state shows Loading Indicator`() {
        composeTestRule.setContent {
            ListDetailAppTheme {
                ListScreen(
                    viewState = ListScreenViewState.LoadingState,
                    onItemSelected = {},
                    reloadItems = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(ListScreenTestTags.LOADING_CONTENT).assertExists()
    }

    @Test
    fun `error state shows error content`() {
        composeTestRule.setContent {
            ListDetailAppTheme {
                ListScreen(
                    viewState = ListScreenViewState.ErrorState,
                    onItemSelected = {},
                    reloadItems = {}
                )
            }
        }
        composeTestRule.onNodeWithTag(ListScreenTestTags.LOADING_CONTENT).assertDoesNotExist()
        composeTestRule.onNodeWithTag(ListScreenTestTags.ERROR_CONTENT).assertExists()
    }


    @Test
    fun `Success state shows list content`() {
        val list = listOf(
            ListItemData(
                id = "1",
                name = "Test Item",
            ),
            ListItemData(
                id = "2",
                name = "Test Item 2",
            )
        )
        composeTestRule.setContent {
            ListDetailAppTheme {
                ListScreen(
                    viewState = ListScreenViewState.SuccessState(list),
                    onItemSelected = {},
                    reloadItems = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Test Item").assertExists()
        composeTestRule.onNodeWithText("Test Item 2").assertExists()

        composeTestRule.onNodeWithTag(ListScreenTestTags.LOADING_CONTENT).assertDoesNotExist()
        composeTestRule.onNodeWithTag(ListScreenTestTags.ERROR_CONTENT).assertDoesNotExist()
    }
}