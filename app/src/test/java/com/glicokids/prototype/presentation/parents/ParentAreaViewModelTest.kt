package com.glicokids.prototype.presentation.parents

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.glicokids.prototype.domain.repository.StorageRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ParentAreaViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val storageRepository = mockk<StorageRepository>(relaxed = true)
    private lateinit var viewModel: ParentAreaViewModel

    @Before
    fun setup() {
        every { storageRepository.getInt("range_min", 70) } returns 70
        every { storageRepository.getInt("range_max", 180) } returns 180
        viewModel = ParentAreaViewModel(storageRepository)
    }

    @Test
    fun `when updating range with valid values, should return true and save`() {
        val result = viewModel.updateTargetRange(80, 150)
        
        assertThat(result).isTrue()
        verify { storageRepository.saveInt("range_min", 80) }
        verify { storageRepository.saveInt("range_max", 150) }
    }

    @Test
    fun `when updating range with min higher than max, should return false`() {
        val result = viewModel.updateTargetRange(200, 100)
        assertThat(result).isFalse()
    }

    @Test
    fun `when updating range with extreme values, should return false`() {
        val result = viewModel.updateTargetRange(30, 310)
        assertThat(result).isFalse()
    }
}
