package com.mlievens.listdetailapp.data.service

import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.domain.models.ListItemData

interface MagicItemsService {
    suspend fun getMagicItems(): Result<List<ListItemData>>
    suspend fun getMagicItem(itemId: String): Result<ItemDetail>
}