package com.glicokids.prototype.presentation.parents

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SecurityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SecurityViewModel

    @Before
    fun setup() {
        viewModel = SecurityViewModel()
    }

    @Test
    fun `when entering correct PIN, should grant access`() {
        viewModel.validatePin("1234")
        
        assertThat(viewModel.accessGranted.value).isTrue()
        assertThat(viewModel.errorMessage.value).isNull()
    }

    @Test
    fun `when entering wrong PIN, should show error`() {
        viewModel.validatePin("0000")
        
        assertThat(viewModel.accessGranted.value).isFalse()
        assertThat(viewModel.errorMessage.value).isEqualTo("PIN Incorreto. Tente novamente.")
    }

    @Test
    fun `when entering empty PIN, should show error`() {
        viewModel.validatePin("")
        
        assertThat(viewModel.errorMessage.value).isEqualTo("Digite o PIN")
    }

    @Test
    fun `when entering wrong PIN 3 times, should lock account`() {
        viewModel.validatePin("0000")
        viewModel.validatePin("0000")
        viewModel.validatePin("0000")
        
        assertThat(viewModel.isLocked.value).isTrue()
        assertThat(viewModel.errorMessage.value).contains("Bloqueado")
    }

    @Test
    fun `when account is locked, should not allow further attempts`() {
        // Force lock
        repeat(3) { viewModel.validatePin("0000") }
        
        // Try correct PIN while locked
        viewModel.validatePin("1234")
        
        assertThat(viewModel.accessGranted.value).isFalse()
    }
}
