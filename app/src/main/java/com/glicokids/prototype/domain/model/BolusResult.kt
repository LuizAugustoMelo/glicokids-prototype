package com.glicokids.prototype.domain.model

data class BolusResult(
    val insulinDose: Double,
    val message: String,
    val isSafetyAlert: Boolean = false
)
