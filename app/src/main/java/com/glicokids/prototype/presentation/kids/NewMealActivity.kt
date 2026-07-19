package com.glicokids.prototype.presentation.kids

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.databinding.ActivityNewMealBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class NewMealActivity : AppCompatActivity() {

    private val TAG = "GlicoKids_Lifecycle"
    private lateinit var binding: ActivityNewMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "NewMealActivity - onCreate")
        binding = ActivityNewMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val childName = intent.getStringExtra("CHILD_NAME") ?: "Pequeno Herói"
        Log.d(TAG, "NewMealActivity - Iniciando missão para: $childName")

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnTakePhoto.setOnClickListener {
            // Requisito: A foto/IA é simulada no protótipo
            Toast.makeText(this, "Simulando captura de foto...", Toast.LENGTH_SHORT).show()
            binding.etCarbohydrates.setText("45") // Simulando valor detectado pela "IA"
        }

        binding.btnCalculateBolus.setOnClickListener {
            calculateBolus()
        }
    }

    private fun calculateBolus() {
        val carbsText = binding.etCarbohydrates.text.toString()
        val glucoseText = binding.etCurrentGlucose.text.toString()

        // Validação obrigatória do Módulo 3
        if (carbsText.isBlank()) {
            binding.etCarbohydrates.error = "Campo obrigatório"
            return
        }
        if (glucoseText.isBlank()) {
            binding.etCurrentGlucose.error = "Campo obrigatório"
            return
        }

        try {
            val carbs = carbsText.toDouble()
            val glucose = glucoseText.toInt()

            // Fórmula do Módulo 3: dose = (carbo / 15.0) + ((glicemia - 100) / 50.0)
            val foodBolus = carbs / 15.0
            val correctionBolus = if (glucose > 100) (glucose - 100) / 50.0 else 0.0
            
            val totalDose = foodBolus + correctionBolus
            
            // Regra: Arredondada PARA BAIXO em passos de 0,5 (estimativa conservadora)
            val roundedDose = (Math.floor(totalDose * 2.0) / 2.0)
            val finalDose = if (roundedDose < 0) 0.0 else roundedDose

            // Exibir resultado
            binding.llResult.visibility = View.VISIBLE
            binding.tvCalculationResult.text = String.format(Locale.getDefault(), "Dose sugerida: %.1f UI", finalDose)
            
            Log.d(TAG, "Cálculo realizado: Carbos=$carbs, Glicemia=$glucose -> Dose=$finalDose")

        } catch (e: Exception) {
            Log.e(TAG, "Erro na conversão de valores: ${e.message}")
            Toast.makeText(this, "Por favor, insira valores válidos", Toast.LENGTH_SHORT).show()
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
