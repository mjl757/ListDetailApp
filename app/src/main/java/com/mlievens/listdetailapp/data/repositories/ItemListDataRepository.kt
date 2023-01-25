package com.mlievens.listdetailapp.data.repositories

import com.mlievens.listdetailapp.data.service.MagicItemsService
import com.mlievens.listdetailapp.domain.models.ListItemData
import com.mlievens.listdetailapp.domain.repositories.ItemListRepository

class ItemListDataRepository(private val magicItemsService: MagicItemsService): ItemListRepository {

    override suspend fun getItemList(): Result<List<ListItemData>> {
        return magicItemsService.getMagicItems()
    }
}