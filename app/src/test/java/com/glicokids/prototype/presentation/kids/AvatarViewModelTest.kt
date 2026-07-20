package com.glicokids.prototype.presentation.kids

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.glicokids.prototype.domain.repository.StorageRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AvatarViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val storageRepository = mockk<StorageRepository>(relaxed = true)
    private lateinit var viewModel: AvatarViewModel

    @Before
    fun setup() {
        every { storageRepository.getInt("selected_avatar", 0) } returns 2
        viewModel = AvatarViewModel(storageRepository)
    }

    @Test
    fun `should load initial avatar from storage`() {
        assertThat(viewModel.currentIndex.value).isEqualTo(2)
    }

    @Test
    fun `when clicking next, should increment index and wrap around`() {
        // Start at 4 (last)
        every { storageRepository.getInt(any(), any()) } returns 4
        val vm = AvatarViewModel(storageRepository)
        
        vm.nextAvatar()
        assertThat(vm.currentIndex.value).isEqualTo(0)
    }

    @Test
    fun `when clicking previous, should decrement index and wrap around`() {
        // Start at 0
        every { storageRepository.getInt(any(), any()) } returns 0
        val vm = AvatarViewModel(storageRepository)
        
        vm.previousAvatar()
        assertThat(vm.currentIndex.value).isEqualTo(4)
    }

    @Test
    fun `when saving avatar, should call storage repository`() {
        viewModel.selectAvatar(3)
        
        verify { storageRepository.saveInt("selected_avatar", 3) }
    }
}
