package com.glicokids.prototype.presentation.parents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor() : ViewModel() {

    private val _accessGranted = MutableLiveData(false)
    val accessGranted: LiveData<Boolean> = _accessGranted

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLocked = MutableLiveData(false)
    val isLocked: LiveData<Boolean> = _isLocked

    private var attemptCount = 0
    private val maxAttempts = 3
    private val correctPin = "1234" // Mocked PIN

    fun validatePin(pin: String) {
        if (_isLocked.value == true) {
            _errorMessage.value = "Bloqueado por segurança. Tente mais tarde."
            return
        }

        // Sanitization: Remove any non-numeric characters
        val cleanPin = pin.filter { it.isDigit() }

        if (cleanPin.isBlank()) {
            _errorMessage.value = "Digite o PIN"
            return
        }

        if (cleanPin == correctPin) {
            _errorMessage.value = null
            _accessGranted.value = true
            attemptCount = 0
        } else {
            attemptCount++
            if (attemptCount >= maxAttempts) {
                _isLocked.value = true
                _errorMessage.value = "Bloqueado após $maxAttempts tentativas."
            } else {
                val remaining = maxAttempts - attemptCount
                _errorMessage.value = "PIN Incorreto. $remaining tentativas restantes."
            }
            _accessGranted.value = false
        }
    }
}
