package com.mlievens.listdetailapp.ui.details

import com.mlievens.listdetailapp.domain.repositories.ItemDetailRepository
import io.mockk.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.advanceUntilIdle
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

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `initial detail state is loading`() {
        val testObject = DetailsViewModel(itemDetailRepository)
        assert(testObject.detailState.value == DetailViewState.LoadingState)
    }

    @Test
    fun `loadDetails calls getItemDetail`() = runTest {
        val testObject = DetailsViewModel(itemDetailRepository)
        testObject.loadDetails("test")
        coVerify(exactly = 1) { itemDetailRepository.getItemDetail("test") }
    }

}