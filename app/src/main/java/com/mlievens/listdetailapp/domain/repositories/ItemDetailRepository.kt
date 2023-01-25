package com.mlievens.listdetailapp.domain.repositories

import com.mlievens.listdetailapp.domain.models.ItemDetail

interface ItemDetailRepository {
    suspend fun getItemDetail(id: String): Result<ItemDetail>
}