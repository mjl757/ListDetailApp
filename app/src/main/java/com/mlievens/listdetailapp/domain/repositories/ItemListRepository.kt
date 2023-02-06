package com.mlievens.listdetailapp.domain.repositories

import com.mlievens.listdetailapp.domain.models.ListItemData

interface ItemListRepository {

    suspend fun getItemList(): Result<List<ListItemData>>
}
