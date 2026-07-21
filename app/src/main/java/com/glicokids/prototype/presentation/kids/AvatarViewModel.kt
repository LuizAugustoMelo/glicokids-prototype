package com.glicokids.prototype.presentation.kids

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glicokids.prototype.domain.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AvatarViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _currentIndex = MutableLiveData<Int>()
    val currentIndex: LiveData<Int> = _currentIndex

    private val avatarCount = 5

    init {
        _currentIndex.value = storageRepository.getInt("selected_avatar", 0)
    }

    fun nextAvatar() {
        val current = _currentIndex.value ?: 0
        _currentIndex.value = (current + 1) % avatarCount
    }

    fun previousAvatar() {
        val current = _currentIndex.value ?: 0
        _currentIndex.value = if (current == 0) avatarCount - 1 else current - 1
    }

    fun selectAvatar(index: Int) {
        storageRepository.saveInt("selected_avatar", index)
    }
}
