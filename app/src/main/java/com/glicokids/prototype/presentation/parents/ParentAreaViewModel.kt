package com.glicokids.prototype.presentation.parents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glicokids.prototype.domain.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ParentAreaViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _targetRange = MutableLiveData<Pair<Int, Int>>()
    val targetRange: LiveData<Pair<Int, Int>> = _targetRange

    private val _validationError = MutableLiveData<String?>(null)
    val validationError: LiveData<String?> = _validationError

    init {
        loadRange()
    }

    private fun loadRange() {
        val min = storageRepository.getInt("range_min", 70)
        val max = storageRepository.getInt("range_max", 180)
        _targetRange.value = Pair(min, max)
    }

    fun updateTargetRange(min: Int, max: Int): Boolean {
        // Ajuste 3: Validação 40 <= min < max <= 300
        if (min < 40 || max > 300 || min >= max) {
            return false
        }

        storageRepository.saveInt("range_min", min)
        storageRepository.saveInt("range_max", max)
        _targetRange.value = Pair(min, max)
        return true
    }
}
