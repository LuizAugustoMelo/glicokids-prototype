package com.glicokids.prototype.presentation.kids

data class NewMealUiState(
    val insulinDose: Double? = null,
    val message: String? = null,
    val carbsError: String? = null,
    val glucoseError: String? = null,
    val isLoading: Boolean = false
)
