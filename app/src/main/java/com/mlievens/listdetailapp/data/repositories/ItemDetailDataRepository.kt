package com.mlievens.listdetailapp.data.repositories

import com.mlievens.listdetailapp.data.service.MagicItemsService
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.domain.repositories.ItemDetailRepository

class ItemDetailDataRepository(
    private val magicItemsService: MagicItemsService
) : ItemDetailRepository {
    override suspend fun getItemDetail(id: String): Result<ItemDetail> {
        return magicItemsService.getMagicItem(id)
    }
}