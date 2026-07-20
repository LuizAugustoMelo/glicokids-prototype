package com.glicokids.prototype.presentation.kids

data class Medal(
    val id: Int,
    val name: String,
    val description: String,
    val rarity: String, // OURO, ESMERALDA, DIAMANTE
    val drawableRes: Int,
    val isLocked: Boolean = false
)
