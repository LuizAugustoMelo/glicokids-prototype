package com.glicokids.prototype.util

import android.content.Context
import android.content.Intent
import android.widget.Toast

object UIHelper {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun navigateTo(context: Context, destination: Class<*>) {
        val intent = Intent(context, destination)
        context.startActivity(intent)
    }
}
