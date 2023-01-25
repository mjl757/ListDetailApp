package com.mlievens.listdetailapp.domain.models

data class ItemDetail(
    val name: String,
    val description: String,
    val type: String,
    val rarity: String,
    val requiresAttunement: String?,
){
    val itemDetailsText: String
    get() {
        var result = "$type, $rarity"
        if (!requiresAttunement.isNullOrBlank()) {
            result += " ($requiresAttunement)"
        }
        return result
    }
}
