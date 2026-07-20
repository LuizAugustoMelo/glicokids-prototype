package com.glicokids.prototype.presentation.kids

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.databinding.ActivityNewMealBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class NewMealActivity : AppCompatActivity() {

    private val TAG = "GlicoKids_Lifecycle"
    private lateinit var binding: ActivityNewMealBinding
    private val viewModel: NewMealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "NewMealActivity - onCreate")
        binding = ActivityNewMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val childName = intent.getStringExtra("CHILD_NAME") ?: "Pequeno Herói"
        Log.d(TAG, "NewMealActivity - Iniciando missão para: $childName")

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnTakePhoto.setOnClickListener {
            Toast.makeText(this, "Simulando captura de foto...", Toast.LENGTH_SHORT).show()
            binding.etCarbohydrates.setText("45") 
        }

        binding.btnCalculateBolus.setOnClickListener {
            viewModel.calculate(
                binding.etCarbohydrates.text.toString(),
                binding.etCurrentGlucose.text.toString()
            )
        }
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { state ->
            // Update errors
            binding.etCarbohydrates.error = state.carbsError
            binding.etCurrentGlucose.error = state.glucoseError

            // Update Result
            if (state.insulinDose != null) {
                binding.llResult.visibility = View.VISIBLE
                binding.tvCalculationResult.text = String.format(Locale.getDefault(), "Dose sugerida: %.1f UI", state.insulinDose)
            } else {
                binding.llResult.visibility = View.GONE
            }

            // Show system messages
            state.message?.let {
                if (state.insulinDose == null) {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // Requisitos do Módulo 2: Logs de ciclo de vida
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "NewMealActivity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "NewMealActivity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "NewMealActivity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "NewMealActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "NewMealActivity - onDestroy")
    }
}
