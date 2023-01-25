package com.mlievens.listdetailapp.data.models

data class MagicItem(
    val slug: String,
    val name: String,
    val type: String,
    val desc: String,
    val rarity: String,
    val requires_attunement: String,
)
