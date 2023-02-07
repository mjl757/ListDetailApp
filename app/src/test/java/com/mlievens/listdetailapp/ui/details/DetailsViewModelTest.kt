package com.mlievens.listdetailapp.ui.details

import androidx.lifecycle.SavedStateHandle
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.domain.repositories.ItemDetailRepository
import io.mockk.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val itemDetailRepository: ItemDetailRepository = mockk(relaxed = true)
    private val savedStateHandle = SavedStateHandle()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        savedStateHandle.set("itemId", "testId")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `initial detail state is loading`() {
        val testObject = DetailsViewModel(itemDetailRepository, savedStateHandle)
        assert(testObject.detailState.value == DetailViewState.LoadingState)
    }

    @Test
    fun `loadDetails calls getItemDetail`() = runTest {
        val itemDetail = ItemDetail(
            name = "item1",
            type = "type`",
            description = "description",
            rarity = "Very Rare",
            requiresAttunement = "Yes"
        )
        coEvery { itemDetailRepository.getItemDetail("testId") } returns Result.success(itemDetail)

        DetailsViewModel(itemDetailRepository, savedStateHandle)

        coVerify(exactly = 1) { itemDetailRepository.getItemDetail("testId") }
    }

}