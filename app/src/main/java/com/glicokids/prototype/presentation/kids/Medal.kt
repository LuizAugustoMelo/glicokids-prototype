package com.glicokids.prototype.presentation.kids

data class Medal(
    val id: Int,
    val name: String,
    val description: String,
    val colorRes: Int,
    val isLocked: Boolean = false
)
