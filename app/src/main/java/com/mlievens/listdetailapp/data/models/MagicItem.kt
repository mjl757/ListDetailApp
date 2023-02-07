package com.mlievens.listdetailapp.data.models

import com.squareup.moshi.Json

data class MagicItem(
    val slug: String,
    val name: String,
    val type: String,
    val desc: String,
    val rarity: String,
    @Json(name = "requires_attunement")
    val requiresAttunement: String,
)
