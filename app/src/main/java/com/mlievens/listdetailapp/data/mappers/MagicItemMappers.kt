package com.mlievens.listdetailapp.data.mappers

import com.mlievens.listdetailapp.data.models.MagicItem
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.domain.models.ListItemData

fun MagicItem.toListItemData(): ListItemData = ListItemData(
    id = slug,
    name = name,
)

fun MagicItem.toItemDetail(): ItemDetail = ItemDetail(
    name = name,
    description = desc,
    type = type,
    rarity = rarity,
    requiresAttunement = requiresAttunement
)