package com.glicokids.prototype.presentation.parents

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.databinding.ActivityParentSecurityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParentSecurityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParentSecurityBinding
    private val viewModel: SecurityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentSecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val childName = intent.getStringExtra("CHILD_NAME") ?: "Pequeno Herói"
        binding.tvChildName.text = "Validando acesso para: $childName"

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.btnVerify.setOnClickListener {
            viewModel.validatePin(binding.etPin.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.accessGranted.observe(this) { granted ->
            if (granted) {
                Toast.makeText(this, "Acesso Concedido!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        viewModel.errorMessage.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
