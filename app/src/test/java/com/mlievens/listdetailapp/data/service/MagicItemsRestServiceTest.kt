package com.mlievens.listdetailapp.data.service

import com.mlievens.listdetailapp.data.models.MagicItem
import com.mlievens.listdetailapp.data.models.MagicItemsResult
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.domain.models.ListItemData
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MagicItemsRestServiceTest {

    private val apiService: ApiService = mockk(relaxed = true)
    private val testObject = MagicItemsRestService(apiService)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getMagicItems returns a failure result if magic items fail to load`() = runTest {
        val responseBody = "failed".toResponseBody()
        coEvery { apiService.getItems() } returns Response.error(500, responseBody)

        val result = testObject.getMagicItems()

        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Unable to load Magic Items")
    }

    @Test
    fun `getMagicItem returns a failure result if magic items fail to load`() = runTest {
        val responseBody = "failed".toResponseBody()
        coEvery { apiService.getItems() } returns Response.error(500, responseBody)

        val result = testObject.getMagicItem("1")

        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Unable to load Magic Item")
    }

    @Test
    fun `getMagicItems returns a success result if magic items fail to load`() = runTest {
        val magicItemResponse = MagicItemsResult(
            results = listOf(
                MagicItem(
                    slug = "1",
                    name = "item1",
                    type = "type2",
                    desc = "Description",
                    rarity = "Rare",
                    requiresAttunement = "Yes"
                ),
                MagicItem(
                    slug = "2",
                    name = "item2",
                    type = "type2",
                    desc = "Different description",
                    rarity = "Very Rare",
                    requiresAttunement = "Yes"
                )
            )
        )
        coEvery { apiService.getItems() } returns Response.success(magicItemResponse)

        val result = testObject.getMagicItems()
        val expected: Result<List<ListItemData>> = Result.success(
            listOf(
                ListItemData(
                    id = "1",
                    name = "item1",
                ),
                ListItemData(
                    id = "2",
                    name = "item2",
                )
            )
        )

        assert(result == expected)
    }

    @Test
    fun `getMagicItem returns a success result if magic item is in the list`() = runTest {
        val magicItemResponse = MagicItemsResult(
            results = listOf(
                MagicItem(
                    slug = "1",
                    name = "item1",
                    type = "type2",
                    desc = "Description",
                    rarity = "Rare",
                    requiresAttunement = "Yes"
                ),
                MagicItem(
                    slug = "2",
                    name = "item2",
                    type = "type2",
                    desc = "Different description",
                    rarity = "Very Rare",
                    requiresAttunement = "Yes"
                )
            )
        )
        coEvery { apiService.getItems() } returns Response.success(magicItemResponse)

        val result = testObject.getMagicItem("2")
        val expected: Result<ItemDetail> =
            Result.success(
                ItemDetail(
                    name = "item2",
                    type = "type2",
                    description = "Different description",
                    rarity = "Very Rare",
                    requiresAttunement = "Yes"
                )
            )

        assert(result == expected)
    }

    @Test
    fun `getMagicItem returns a fail result if magic item id is not in the list`() = runTest {
        val magicItemResponse = MagicItemsResult(
            results = listOf(
                MagicItem(
                    slug = "1",
                    name = "item1",
                    type = "type2",
                    desc = "Description",
                    rarity = "Rare",
                    requiresAttunement = "Yes"
                ),
                MagicItem(
                    slug = "2",
                    name = "item2",
                    type = "type2",
                    desc = "Different description",
                    rarity = "Very Rare",
                    requiresAttunement = "Yes"
                )
            )
        )
        coEvery { apiService.getItems() } returns Response.success(magicItemResponse)

        val result = testObject.getMagicItem("3")

        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Unable to find selected Magic Item")
    }

}