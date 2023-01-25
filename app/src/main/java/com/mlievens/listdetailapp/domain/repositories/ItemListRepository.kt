package com.mlievens.listdetailapp.domain.repositories

import com.mlievens.listdetailapp.domain.models.ListItemData

interface ItemListRepository {

    suspend fun getItemList(): Result<List<ListItemData>>
}

class FakeItemListRepository() : ItemListRepository {
    override suspend fun getItemList(): Result<List<ListItemData>> {
        return Result.success(
            listOf(
                ListItemData("1", "Sword"),
                ListItemData("2", "Bow"),
                ListItemData("3", "Axe"),
                ListItemData("4", "Staff"),
                ListItemData("5", "Robe"),
            )
        )
    }

}