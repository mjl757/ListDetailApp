package com.mlievens.listdetailapp.data.service

import com.mlievens.listdetailapp.data.mappers.toItemDetail
import com.mlievens.listdetailapp.data.mappers.toListItemData
import com.mlievens.listdetailapp.data.models.MagicItem
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.domain.models.ListItemData

class MagicItemsRestService(private val apiService: ApiService): MagicItemsService {

    private var magicItemCache: List<MagicItem>? = null

    override suspend fun getMagicItems(): Result<List<ListItemData>> {
        loadMagicItems()
        return magicItemCache?.let { magicItems ->
            Result.success(magicItems.map {
                it.toListItemData()
            })
        } ?: Result.failure(Throwable(message = "Unable to load Magic Items"))
    }

    override suspend fun getMagicItem(itemId: String): Result<ItemDetail> {
        loadMagicItems()
        return magicItemCache?.let { magicItems ->
            magicItems.firstOrNull { it.slug == itemId }?.let {
                Result.success(it.toItemDetail())
            } ?: Result.failure(Throwable(message = "Unable to find selected Magic Item"))
        } ?: Result.failure(Throwable(message = "Unable to load Magic Item"))
    }

    private suspend fun loadMagicItems() {
        if (magicItemCache == null) {
            val response = apiService.getItems()
            if (response.isSuccessful && response.body() != null) {
                magicItemCache = response.body()!!.results.sortedBy { it.name }
            }
        }
    }
}