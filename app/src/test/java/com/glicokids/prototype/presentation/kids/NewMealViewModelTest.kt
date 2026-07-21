package com.glicokids.prototype.presentation.kids

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.glicokids.prototype.domain.model.BolusResult
import com.glicokids.prototype.domain.model.Result
import com.glicokids.prototype.domain.usecase.CalculateBolusUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewMealViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val calculateBolusUseCase = mockk<CalculateBolusUseCase>()
    private lateinit var viewModel: NewMealViewModel

    @Before
    fun setup() {
        viewModel = NewMealViewModel(calculateBolusUseCase)
    }

    @Test
    fun `when calculating with empty carbs, should show error`() {
        viewModel.calculate("", "110")
        
        val state = viewModel.uiState.value
        assertThat(state?.carbsError).isEqualTo("Campo obrigatório")
        assertThat(state?.insulinDose).isNull()
    }

    @Test
    fun `when calculating with empty glucose, should show error`() {
        viewModel.calculate("45", "")
        
        val state = viewModel.uiState.value
        assertThat(state?.glucoseError).isEqualTo("Campo obrigatório")
    }

    @Test
    fun `when calculation is successful, should update insulin dose`() {
        val mockResult = BolusResult(3.5, "Success")
        every { calculateBolusUseCase.execute(45.0, 110, 100, 50, 15) } returns Result.Success(mockResult)

        viewModel.calculate("45", "110")

        val state = viewModel.uiState.value
        assertThat(state?.insulinDose).isEqualTo(3.5)
        assertThat(state?.carbsError).isNull()
        assertThat(state?.glucoseError).isNull()
    }

    @Test
    fun `when calculation fails, should show error message`() {
        every { calculateBolusUseCase.execute(any(), any(), any(), any(), any()) } returns 
                Result.Failure(Exception(), "System Error")

        viewModel.calculate("45", "110")

        val state = viewModel.uiState.value
        assertThat(state?.message).isEqualTo("System Error")
        assertThat(state?.insulinDose).isNull()
    }
}
