package com.glicokids.prototype.util

import android.content.Context
import android.content.Intent
import android.widget.Toast

object UIHelper {
    
    enum class GlucoseStatus {
        NA_META, ATENCAO, FORA_DA_META
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun navigateTo(context: Context, destination: Class<*>) {
        val intent = Intent(context, destination)
        context.startActivity(intent)
    }

    fun getGlucoseStatus(value: Int, min: Int, max: Int): GlucoseStatus {
        return when {
            value < min || value > max -> GlucoseStatus.FORA_DA_META
            value in min..(min + 15) || value in (max - 15)..max -> GlucoseStatus.ATENCAO
            else -> GlucoseStatus.NA_META
        }
    }

    fun getStatusColor(status: GlucoseStatus): Int {
        return when (status) {
            GlucoseStatus.NA_META -> android.graphics.Color.parseColor("#00E5B0") // Teal
            GlucoseStatus.ATENCAO -> android.graphics.Color.parseColor("#FFD54F") // Gold
            GlucoseStatus.FORA_DA_META -> android.graphics.Color.parseColor("#C62828") // Danger
        }
    }
}
