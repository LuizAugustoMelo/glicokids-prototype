package com.glicokids.prototype.presentation.parents

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.databinding.ActivityParentSecurityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParentSecurityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParentSecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentSecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Requisito Módulo 2: Recebimento de Extras via Intent
        val childName = intent.getStringExtra("CHILD_NAME") ?: "Pequeno Herói"
        binding.tvChildName.text = "Validando acesso para: $childName"

        binding.btnVerify.setOnClickListener {
            val pin = binding.etPin.text.toString()
            if (pin == "1234") { // Mock de PIN para o Protótipo
                Toast.makeText(this, "Acesso Concedido!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "PIN Incorreto. Tente novamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
